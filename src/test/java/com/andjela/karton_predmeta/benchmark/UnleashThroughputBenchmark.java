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

/**
 * Мерење ПРОПУСНОСТИ (throughput) под конкурентношћу.
 * Показује колико провера у секунди свих нити ЗАЈЕДНО ураде.
 * Ако throughput не расте са бројем нити -> евалуација се серијализује.
 *
 * НИВОИ КОНКУРЕНТНОСТИ: 10, 100, 500
 */

@BenchmarkMode(Mode.Throughput)                 // операција у секунди (не време по операцији)
@OutputTimeUnit(TimeUnit.SECONDS)               // ops/s
@State(Scope.Benchmark)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(2)
@Threads(500) 
public class UnleashThroughputBenchmark {
    
    private Unleash unleash;
    private UnleashContext adminContext;
    private UnleashContext timeContext;
 
    @Setup(Level.Trial)
    public void setup() {
        UnleashConfig config = UnleashConfig.builder()
                .appName("karton-predmeta")
                .instanceId("benchmark-throughput")
                .environment("development")
                .unleashAPI("http://localhost:4242/api")
                .apiKey("*:development.036796412f48c95080c7ec2b93dcc7637897cb12163fc6e40006be71")
                .synchronousFetchOnInitialisation(true)
                .disableMetrics()
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
    }
 
    @Benchmark
    public void jeftina_searchCourses(Blackhole bh) {
        bh.consume(unleash.isEnabled("search-courses"));
    }
 
    @Benchmark
    public void srednja_adminAccess(Blackhole bh) {
        bh.consume(unleash.isEnabled("admin-access", adminContext));
    }
 
    @Benchmark
    public void najskuplja_registrationOpen(Blackhole bh) {
        bh.consume(unleash.isEnabled("registration-open", timeContext));
    }
 
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(UnleashThroughputBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
    
}
