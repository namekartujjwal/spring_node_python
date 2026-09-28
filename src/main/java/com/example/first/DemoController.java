package com.example.first;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")

public class DemoController {
    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Spring Boot Backend");
        return response;
    }

    @GetMapping("/api/data")
    public Map<String, Object> getData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 101);
        data.put("title", "Spring Boot Service Payload");
        data.put("timestamp", System.currentTimeMillis());
        return data;
    }

    @PostMapping("api/process")
    public Map<String, Object> processData(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("receivedData", payload);
        response.put("processedByThread", Thread.currentThread().getName());
        return response;
    }

}
