/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andjela.karton_predmeta.benchmark;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import io.getunleash.util.UnleashConfig;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
 
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Andjela
 */


@BenchmarkMode(Mode.AverageTime)          // prosecno vreme po poyivu
@OutputTimeUnit(TimeUnit.NANOSECONDS)     // rezultat u nanosekundama
@State(Scope.Benchmark)                   // jedna deljena instanca ya sve niti
@Warmup(iterations = 10, time = 1)         // 5 warmup iteracija
@Measurement(iterations = 10, time = 1)    // 5 mernih iteracija
@Fork(2)                                  // 1 forkovan JVM (podigni na 2-3 za finalno)
public class UnleashBenchmark {
 
    private Unleash unleash;
    private UnleashContext adminContext;
    private UnleashContext timeContext;
    private UnleashContext studentContext;
 
    @Setup(Level.Trial)
    public void setup() {
        UnleashConfig config = UnleashConfig.builder()
                .appName("karton-predmeta")
                .instanceId("benchmark-instance")
                .environment("development")
                .unleashAPI("http://localhost:4242/api")
                .apiKey("*:development.036796412f48c95080c7ec2b93dcc7637897cb12163fc6e40006be71")
                .synchronousFetchOnInitialisation(true)  // povuce yastavice pre nego sto pocne
                .build();
 
        unleash = new DefaultUnleash(config);
 
        adminContext = UnleashContext.builder()
                .userId("bench-user")
                .addProperty("role", "ADMIN")
                .build();
        
         timeContext = UnleashContext.builder()
                .userId("bench-user")
                .currentTime(java.time.ZonedDateTime.now())
                .build();
         
         studentContext = UnleashContext.builder()
                .userId("bench-user")
                .addProperty("role", "STUDENT")
                .build();
    }
 
    // === BASELINE: obican if, bez Unleash-а ===
    @Benchmark
    public void baseline(Blackhole bh) {
        boolean result = (System.nanoTime() % 2 == 0);  // trivijalna provera
        bh.consume(result);
    }
 
    // === prost STANDARD: search-courses (bez konteksta, bez ogranicenjа) ===
    @Benchmark
    public void prostStandardSearchCourses(Blackhole bh) {
        bh.consume(unleash.isEnabled("search-courses"));
    }
    
    // === prost ROLLOUT: export-data (bez k i o) ===
    @Benchmark
    public void prostRolloutExportData(Blackhole bh) {
        bh.consume(unleash.isEnabled("export-data"));
    }
    
     // === prost STANDARD: strong-password ===
    @Benchmark
    public void prostStandardPassword(Blackhole bh) {
        bh.consume(unleash.isEnabled("strong-password"));
    }

    
    // === prost ROLLOUT: student-list (isto kao search-courses) ===
    @Benchmark
    public void prostRolloutStudentList(Blackhole bh) {
        bh.consume(unleash.isEnabled("student-list"));
    }

    // === STANDARD + kontekst: admin-access (role == ADMIN) ===
    @Benchmark
    public void standardKontekstAdminAccess(Blackhole bh) {
        bh.consume(unleash.isEnabled("admin-access", adminContext));
    }
    
    // === STANDARD + vremenski kontekst: registration-open (currentTime опсег) ===
    @Benchmark
    public void standardVremeRegistrationOpen(Blackhole bh) {
        bh.consume(unleash.isEnabled("registration-open", timeContext));
    }
 
    // === ROLLOUT + КОНТЕКСТ: dark-mode-experiment (50% + role) ===
   
    // === DARK-MODE, constraint prolayi(STUDENT)puna cena rollout+constraint ===
    @Benchmark
    public void darkModeStudentProlazi(Blackhole bh) {
        bh.consume(unleash.isEnabled("dark-mode-experiment", studentContext));
    }

    // === DARK-MODE, constraint PADA (ADMIN)  jeftino ===
    @Benchmark
    public void darkModeAdminPada(Blackhole bh) {
        bh.consume(unleash.isEnabled("dark-mode-experiment", adminContext));
    }
    
    
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(UnleashBenchmark.class.getSimpleName())
                .addProfiler("gc")
                .build();
        new Runner(opt).run();
    }
}
