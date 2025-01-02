package com.narinder.identity.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class FacebookUser implements OAuth2User {

    private String fullName, email, id;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getName() {
        return this.id;
    }

    public void setName(String id) {
        this.id = id;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName(){
        return this.fullName.split(" ")[0];
    }

    public String getLastName(){
        return this.fullName.split(" ")[1];
    }

}
