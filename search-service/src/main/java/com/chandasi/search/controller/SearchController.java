package com.chandasi.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    // GET /api/search?q=Ram — Naam se dhundho
    @GetMapping
    public List<?> search(@RequestParam String q) {
        // Resident Service se sab residents lao
        Map[] residents = restTemplate.getForObject(
            "http://resident-service/api/residents",
            Map[].class
        );

        if (residents == null) return new ArrayList<>();

        // Filter karo — naam, gali, ya profession match karo
        return Arrays.stream(residents)
            .filter(r -> {
                String name       = String.valueOf(r.getOrDefault("name", ""));
                String street     = String.valueOf(r.getOrDefault("street", ""));
                String profession = String.valueOf(r.getOrDefault("profession", ""));
                String village    = String.valueOf(r.getOrDefault("village", ""));
                String query      = q.toLowerCase();

                return name.toLowerCase().contains(query)
                    || street.toLowerCase().contains(query)
                    || profession.toLowerCase().contains(query)
                    || village.toLowerCase().contains(query);
            })
            .collect(Collectors.toList());
    }
}
