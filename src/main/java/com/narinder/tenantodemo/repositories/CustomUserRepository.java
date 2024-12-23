package com.narinder.tenantodemo.repositories;

import com.narinder.tenantodemo.entities.TenantoUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<TenantoUserDetails, String> {

}
