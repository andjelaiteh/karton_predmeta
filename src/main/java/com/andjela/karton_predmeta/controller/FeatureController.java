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
    
    @GetMapping("/registration-open")
    public boolean prijavaOtvorena() {
        return unleash.isEnabled("registration-open");
    }
    
    @GetMapping("/student-list")
    public boolean studentList() {
        return unleash.isEnabled("student-list");
    }
    
    @GetMapping("/export-data")
    public boolean exportData() {
        return unleash.isEnabled("export-data");
    }
    
    @GetMapping("/baseline")
    public boolean baseline() {
        return true;   // bez unleash.isEnabled() — čist HTTP zahtev
    }
    
    // ===== H4: merenje broja provera po zahtevu =====

    private UnleashContext kontekstUloge() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (auth != null) ? auth.getName() : "anonymous";
        boolean isAdmin = (auth != null) && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return UnleashContext.builder()
                .userId(username)
                .addProperty("role", isAdmin ? "ADMIN" : "STUDENT")
                .build();
    }

    @GetMapping("/multi3")
    public boolean multi3() {
        UnleashContext ctx = kontekstUloge();
        boolean r = false;
        r ^= unleash.isEnabled("strong-password");
        r ^= unleash.isEnabled("admin-access", ctx);
        r ^= unleash.isEnabled("search-courses");
        return r;
    }

    @GetMapping("/multi5")
    public boolean multi5() {
        UnleashContext ctx = kontekstUloge();
        boolean r = false;
        r ^= unleash.isEnabled("strong-password");
        r ^= unleash.isEnabled("admin-access", ctx);
        r ^= unleash.isEnabled("search-courses");
        r ^= unleash.isEnabled("registration-open");
        r ^= unleash.isEnabled("student-list");
        return r;
    }

    @GetMapping("/multi7")
    public boolean multi7() {
        UnleashContext ctx = kontekstUloge();
        boolean r = false;
        r ^= unleash.isEnabled("strong-password");
        r ^= unleash.isEnabled("admin-access", ctx);
        r ^= unleash.isEnabled("search-courses");
        r ^= unleash.isEnabled("registration-open");
        r ^= unleash.isEnabled("student-list");
        r ^= unleash.isEnabled("dark-mode-experiment", ctx);
        r ^= unleash.isEnabled("export-data");
        return r;
    }
}
