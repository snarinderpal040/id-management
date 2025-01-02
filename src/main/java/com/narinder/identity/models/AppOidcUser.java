package com.narinder.identity.models;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class AppOidcUser extends DefaultOidcUser {

    public AppOidcUser(OidcUser oidcUser) {
        super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
    }

}
