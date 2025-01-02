package com.narinder.identity.controllers;

import com.narinder.identity.models.RegistrationRequest;
import com.narinder.identity.models.RegistrationResponse;
import com.narinder.identity.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(registrationService.registerUser(registrationRequest));
    }

}
