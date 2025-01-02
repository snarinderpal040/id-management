package com.narinder.identity.configs;

import com.narinder.identity.entities.OAuth2AppUserDeatils;
import com.narinder.identity.models.FacebookUser;
import com.narinder.identity.models.AppOidcUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.narinder.identity.repositories.OAuth2AppUsersRepository;

import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final OAuth2AppUsersRepository repository;

    public AuthenticationSuccessHandlerImpl(OAuth2AppUsersRepository repository) {
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
                AppOidcUser appOidcUser = (AppOidcUser) oAuth2AuthenticationToken.getPrincipal();
                firstName = appOidcUser.getGivenName();
                lastName = appOidcUser.getFamilyName();
                email = appOidcUser.getEmail();
            }

            OAuth2AppUserDeatils oAuth2AppUserDeatils = new OAuth2AppUserDeatils();
            oAuth2AppUserDeatils.setSub(subject);
            oAuth2AppUserDeatils.setEmail(email);
            oAuth2AppUserDeatils.setFirstName(firstName);
            oAuth2AppUserDeatils.setLastName(lastName);

            repository.save(oAuth2AppUserDeatils);
        }
    }

}
