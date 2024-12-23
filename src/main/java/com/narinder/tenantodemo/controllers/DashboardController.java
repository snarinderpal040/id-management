package com.narinder.tenantodemo.controllers;

import com.narinder.tenantodemo.entities.TenantoUserDetails;
import com.narinder.tenantodemo.models.FacebookUser;
import com.narinder.tenantodemo.models.TenantoOidcUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping(path = "/dashboard")
    public ResponseEntity<String> dashboard(@RequestParam(name = "username") String username) {
        return ResponseEntity.ok(String.format("Welcome %s !!!", username));
    }

    @GetMapping(path = "/myInfo")
    public ResponseEntity<String> myInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if(principal instanceof TenantoOidcUser tenantoOidcUser) {
            username = tenantoOidcUser.getGivenName();
        }
        if(principal instanceof FacebookUser facebookUser) {
            username = facebookUser.getFirstName();
        }
        return ResponseEntity.ok(String.format("Welcome %s !!!", username));
    }

}
