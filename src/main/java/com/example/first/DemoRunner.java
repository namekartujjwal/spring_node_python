package com.example.first;

import com.example.first.client.ModernRegistrarClient;
import com.example.first.client.LegacyRegistrarClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {

    private final ModernRegistrarClient modernClient;
    private final LegacyRegistrarClient legacyClient;

    public DemoRunner(ModernRegistrarClient modernClient, LegacyRegistrarClient legacyClient) {
        this.modernClient = modernClient;
        this.legacyClient = legacyClient;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== TESTING CACHE ===");
        try {
            System.out.println("First call (hits network): " + modernClient.fetchRegistrarDataCached("123"));
            System.out.println(
                    "Second call (hits cache, should be instant): " + modernClient.fetchRegistrarDataCached("123"));
        } catch (Exception e) {
            System.out.println("Network call failed as expected: " + e.getMessage());
        }

        System.out.println("\n=== TESTING RETRY ===");
        try {
            // This will fail and retry 3 times because api.example.com/registrar doesn't
            // exist
            legacyClient.fetchRegistrarData("999");
        } catch (Exception e) {
            System.out.println("Retry exhausted: " + e.getMessage());
        }
    }
}