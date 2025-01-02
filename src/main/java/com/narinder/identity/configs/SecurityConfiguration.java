package com.narinder.identity.configs;

import com.narinder.identity.services.CustomOAuth2UserService;
import com.narinder.identity.services.AppOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            CustomOAuth2UserService customOAuth2UserService) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/register", "/login", "/error")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/myInfo", true))
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(oAuth2Customizer -> oAuth2Customizer
                        .successHandler(this.authenticationSuccessHandler)
                        .defaultSuccessUrl("/myInfo", true)
                        .userInfoEndpoint(config -> config
                                        .userService(customOAuth2UserService)
                                        .oidcUserService(new AppOidcUserService())
                        )
                ).csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}
