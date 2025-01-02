package com.narinder.identity.controllers;

import com.narinder.identity.models.FacebookUser;
import com.narinder.identity.models.AppOidcUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if(principal instanceof AppOidcUser appOidcUser) {
            username = appOidcUser.getGivenName();
        }
        if(principal instanceof FacebookUser facebookUser) {
            username = facebookUser.getFirstName();
        }
        return ResponseEntity.ok(String.format("Welcome %s !!!", username));
    }

}
