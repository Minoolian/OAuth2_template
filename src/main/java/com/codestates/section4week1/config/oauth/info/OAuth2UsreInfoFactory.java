package com.codestates.section4week1.config.oauth.info;

import com.codestates.section4week1.config.auth.ProviderType;
import com.codestates.section4week1.config.oauth.info.impl.GithubOAuth2UserInfo;
import com.codestates.section4week1.config.oauth.info.impl.GoogleOAuth2UserInfo;
import com.codestates.section4week1.config.oauth.info.impl.KakaoOauth2UserInfo;

import java.util.Map;

public class OAuth2UsreInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case KAKAO: return new KakaoOauth2UserInfo(attributes);
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case GITHUB: return new GithubOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
