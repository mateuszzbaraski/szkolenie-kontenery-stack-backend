package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppController {

    // Hardcoded data
    private final List<String> messages = List.of(
            "Hello, World!",
            "Welcome to Spring Boot",
            "This is a demo application",
            "Happy coding!"
    );

    private final Map<String, String> products = new HashMap<>() {{
        put("1", "Laptop");
        put("2", "Smartphone");
        put("3", "Headphones");
        put("4", "Monitor");
    }};

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello from Spring Boot!",
                "status", "success"
        );
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return messages;
    }

    @GetMapping("/products")
    public Map<String, String> getProducts() {
        return products;
    }

    @GetMapping("/products/{id}")
    public Map<String, Object> getProduct(@PathVariable String id) {
        if (products.containsKey(id)) {
            return Map.of(
                    "id", id,
                    "name", products.get(id),
                    "found", true
            );
        }
        return Map.of(
                "message", "Product not found",
                "found", false
        );
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return Map.of(
                "name", "Demo Application",
                "version", "1.0.0",
                "description", "A simple Spring Boot API",
                "endpoints", List.of("/api/hello", "/api/messages", "/api/products", "/api/products/{id}", "/api/info")
        );
    }
}
