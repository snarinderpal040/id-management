package com.narinder.tenantodemo.services;

import com.narinder.tenantodemo.entities.TenantoUserDetails;
import com.narinder.tenantodemo.models.RegistrationRequest;
import com.narinder.tenantodemo.models.RegistrationResponse;
import com.narinder.tenantodemo.repositories.CustomUserRepository;
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
        TenantoUserDetails save = customUserRepository.save(mapper(registrationRequest));
        return new RegistrationResponse("User saved " + save.getUsername());
    }

    private TenantoUserDetails mapper(RegistrationRequest registrationRequest) {
        TenantoUserDetails tenantoUserDetails = new TenantoUserDetails();
        tenantoUserDetails.setFirstName(registrationRequest.getFirstName());
        tenantoUserDetails.setLastName(registrationRequest.getLastName());
        tenantoUserDetails.setUsername(registrationRequest.getEmail());
        tenantoUserDetails.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return tenantoUserDetails;
    }

}
