package com.narinder.tenantodemo.models;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class TenantoOidcUser extends DefaultOidcUser {

    public TenantoOidcUser(OidcUser oidcUser) {
        super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
    }

}
