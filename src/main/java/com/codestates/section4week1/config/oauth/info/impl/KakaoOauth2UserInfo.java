package com.codestates.section4week1.config.oauth.info.impl;

import com.codestates.section4week1.config.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class KakaoOauth2UserInfo extends OAuth2UserInfo {

    public KakaoOauth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        if (properties == null) {
            return null;
        }

        return (String) properties.get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("account_email");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("properties");

        if (response == null) {
            return null;
        }

        return (String) response.get("thumbnail_image");
    }
}
