package com.septeam.metatraining.security.command.domain.provider;

import java.util.Map;

public class Google extends OAuth2UserInfo{
    public Google(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProvider() {
        return "GOOGLE";
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
