package com.example.first;

import com.example.first.service.BudgetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class ConcurrencyRunner implements CommandLineRunner {

    private final BudgetService budgetService;

    public ConcurrencyRunner(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @Override
    public void run(String... args) throws InterruptedException {
        System.out.println("\n=== TESTING SHARED BUDGET RACE CONDITION ===");
        budgetService.reset();

        ExecutorService executor = Executors.newFixedThreadPool(100);

        Instant start = Instant.now();

        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> budgetService.spend(1.0));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        Instant end = Instant.now();

        System.out.println("Expected Balance: 0.0");
        System.out.println("Actual Balance: " + budgetService.getBalance());
        System.out.println("Time taken: " + Duration.between(start, end).toMillis() + "ms\n");
    }
}