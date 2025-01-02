package com.narinder.identity.repositories;

import com.narinder.identity.entities.AppUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<AppUserDetails, String> {

}
