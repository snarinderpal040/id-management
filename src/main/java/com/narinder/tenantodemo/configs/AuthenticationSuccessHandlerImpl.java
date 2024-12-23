package com.narinder.tenantodemo.configs;

import com.narinder.tenantodemo.entities.OAuth2TenantoUserDeatils;
import com.narinder.tenantodemo.models.FacebookUser;
import com.narinder.tenantodemo.models.TenantoOidcUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.narinder.tenantodemo.repositories.OAuth2TenantoUsersRepository;

import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final OAuth2TenantoUsersRepository repository;

    public AuthenticationSuccessHandlerImpl(OAuth2TenantoUsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
    ) throws IOException, ServletException {
        if(authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            //unique subject for all the OAuth2Types
            String subject = oAuth2AuthenticationToken.getPrincipal().getName();

            if(repository.findById(subject).isPresent()) {
                return;
            }

            String firstName = "", lastName = "", email = "";

            if(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("facebook")){
                FacebookUser facebookUser = (FacebookUser) oAuth2AuthenticationToken.getPrincipal();
                firstName = facebookUser.getFirstName();
                lastName = facebookUser.getLastName();
                email = facebookUser.getEmail();
            }

            if(oAuth2AuthenticationToken.getPrincipal() instanceof OidcUser) {
                TenantoOidcUser tenantoOidcUser = (TenantoOidcUser) oAuth2AuthenticationToken.getPrincipal();
                firstName = tenantoOidcUser.getGivenName();
                lastName = tenantoOidcUser.getFamilyName();
                email = tenantoOidcUser.getEmail();
            }

            OAuth2TenantoUserDeatils oAuth2TenantoUserDeatils = new OAuth2TenantoUserDeatils();
            oAuth2TenantoUserDeatils.setSub(subject);
            oAuth2TenantoUserDeatils.setEmail(email);
            oAuth2TenantoUserDeatils.setFirstName(firstName);
            oAuth2TenantoUserDeatils.setLastName(lastName);

            repository.save(oAuth2TenantoUserDeatils);
        }
    }

}
