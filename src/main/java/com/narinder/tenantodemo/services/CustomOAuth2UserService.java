package com.narinder.tenantodemo.services;

import com.narinder.tenantodemo.models.FacebookUser;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.setEmail(oAuth2User.getAttribute("email"));
        facebookUser.setName(oAuth2User.getName());
        facebookUser.setFullName(oAuth2User.getAttribute("name"));
        return facebookUser;
    }

}
