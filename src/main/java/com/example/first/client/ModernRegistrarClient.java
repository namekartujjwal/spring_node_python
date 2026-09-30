package com.example.first.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ModernRegistrarClient {
    private final RestClient restClient;

    public ModernRegistrarClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Cacheable(value = "registrarData", key = "#id")
    public String fetchRegistrarDataCached(String id) {
        return restClient.get()
                .uri("https://api.example.com/registrar/{id}", id)
                .retrieve()
                .body(String.class);
    }
}
