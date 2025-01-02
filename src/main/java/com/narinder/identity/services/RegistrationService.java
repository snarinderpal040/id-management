package com.narinder.identity.services;

import com.narinder.identity.entities.AppUserDetails;
import com.narinder.identity.models.RegistrationRequest;
import com.narinder.identity.models.RegistrationResponse;
import com.narinder.identity.repositories.CustomUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final CustomUserRepository customUserRepository;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(CustomUserRepository customUserRepository, PasswordEncoder passwordEncoder) {
        this.customUserRepository = customUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        AppUserDetails save = customUserRepository.save(mapper(registrationRequest));
        return new RegistrationResponse("User saved " + save.getUsername());
    }

    private AppUserDetails mapper(RegistrationRequest registrationRequest) {
        AppUserDetails appUserDetails = new AppUserDetails();
        appUserDetails.setFirstName(registrationRequest.getFirstName());
        appUserDetails.setLastName(registrationRequest.getLastName());
        appUserDetails.setUsername(registrationRequest.getEmail());
        appUserDetails.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return appUserDetails;
    }

}
