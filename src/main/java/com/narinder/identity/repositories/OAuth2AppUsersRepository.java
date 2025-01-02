package com.narinder.identity.repositories;

import com.narinder.identity.entities.OAuth2AppUserDeatils;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2AppUsersRepository extends JpaRepository<OAuth2AppUserDeatils, String> {

}
