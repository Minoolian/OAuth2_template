package com.codestates.section4week1.config.oauth;

import com.codestates.section4week1.config.auth.PrincipalDetails;
import com.codestates.section4week1.config.auth.ProviderType;
import com.codestates.section4week1.config.auth.RoleType;
import com.codestates.section4week1.config.oauth.exception.OAuthProviderMissMatchException;
import com.codestates.section4week1.config.oauth.info.OAuth2UserInfo;
import com.codestates.section4week1.config.oauth.info.OAuth2UsreInfoFactory;
import com.codestates.section4week1.model.Member;
import com.codestates.section4week1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return this.process(userRequest, oAuth2User);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UsreInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        Member savedUser = memberRepository.findByUserId(userInfo.getId());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with" + providerType + "account. please use your "+savedUser.getProviderType() + " account to login"
                );
            }

            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return PrincipalDetails.create(savedUser, user.getAttributes());
    }

    private Member createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        Member user = new Member(userInfo.getId(), userInfo.getName(), userInfo.getEmail(), "ROLE_USER", providerType, RoleType.USER);

        return memberRepository.saveAndFlush(user);
    }

    private Member updateUser(Member member, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !member.getUsername().equals(userInfo.getName())) {
            member.setUsername(userInfo.getName());
        }

        return member;
    }
}
