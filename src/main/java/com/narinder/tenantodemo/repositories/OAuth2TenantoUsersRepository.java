package com.narinder.tenantodemo.repositories;

import com.narinder.tenantodemo.entities.OAuth2TenantoUserDeatils;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2TenantoUsersRepository extends JpaRepository<OAuth2TenantoUserDeatils, String> {

}
