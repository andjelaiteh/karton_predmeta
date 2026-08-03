/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.config;

import com.andjela.karton_predmeta.service.KorisnikService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author Andjela
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     private final KorisnikService korisnikService;

    public SecurityConfig(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(korisnikService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
    .requestMatchers("/login.html", "/login").permitAll()
    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
    .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/predmet/**").permitAll()
    .requestMatchers("/admin.html").hasRole("ADMIN")
    .requestMatchers("/student.html").hasRole("STUDENT")
    .anyRequest().authenticated()
)
        .formLogin(form -> form
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
            .successHandler(successHandler())
            .failureUrl("/login.html?error")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login.html")
            .permitAll()
        );
    return http.build();
}

@Bean
public AuthenticationSuccessHandler successHandler() {
    return (request, response, authentication) -> {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            response.sendRedirect("/admin.html");
        } else {
            response.sendRedirect("/student.html");
        }
    };
}
}
