/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.controller;

import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andjela
 */

@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    private final Unleash unleash;

    public FeatureController(Unleash unleash) {
        this.unleash = unleash;
    }

    @GetMapping("/admin-access")
    public boolean adminAccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = (auth != null) ? auth.getName() : "anonymous";
        boolean isAdmin = (auth != null) && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        UnleashContext context = UnleashContext.builder()
                .userId(username)
                .addProperty("role", isAdmin ? "ADMIN" : "STUDENT")
                .build();

        return unleash.isEnabled("admin-access", context);
    }
    
    @GetMapping("/dark-mode")
    public boolean darkMode() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : "anonymous";
        boolean isAdmin = (auth != null) && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        UnleashContext context = UnleashContext.builder()
                .userId(username)
                .addProperty("role", isAdmin ? "ADMIN" : "STUDENT")
                .build();

        return unleash.isEnabled("dark-mode-experiment", context);
    }
    
    @GetMapping("/search-courses")
    public boolean searchCourses() {
        return unleash.isEnabled("search-courses");
    }
    
    @GetMapping("/prijava-otvorena")
    public boolean prijavaOtvorena() {
        return unleash.isEnabled("prijava-otvorena");
    }
    
    @GetMapping("/student-list")
    public boolean studentList() {
        return unleash.isEnabled("student-list");
    }
    
    @GetMapping("/export-data")
    public boolean exportData() {
        return unleash.isEnabled("export-data");
    }
}
