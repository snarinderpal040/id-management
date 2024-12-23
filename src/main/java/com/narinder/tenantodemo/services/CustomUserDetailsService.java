package com.narinder.tenantodemo.services;

import com.narinder.tenantodemo.entities.TenantoUserDetails;
import com.narinder.tenantodemo.repositories.CustomUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TenantoUserDetails> byId = customUserRepository.findById(username);
        if(byId.isPresent()) {
            return byId.get();
        }
        throw new UsernameNotFoundException(username);
    }
}
