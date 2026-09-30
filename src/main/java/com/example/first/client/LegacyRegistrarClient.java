package com.example.first.client;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class LegacyRegistrarClient {
    private final RestTemplate restTemplate;

    public LegacyRegistrarClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    @Retryable(retryFor = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public String fetchRegistrarData(String id) {
        String url = "https://api.example.com/registrar/" + id;
        return restTemplate.getForObject(url, String.class);
    }
}
