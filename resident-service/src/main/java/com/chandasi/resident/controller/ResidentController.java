package com.chandasi.resident.controller;

import com.chandasi.resident.model.Resident;
import com.chandasi.resident.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/residents")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ResidentController {

    @Autowired
    private ResidentRepository repo;

    @GetMapping
    public List<Resident> getAll() { return repo.findAll(); }

    @GetMapping("/village/{village}")
    public List<Resident> getByVillage(@PathVariable String village) { return repo.findByVillage(village); }

    @GetMapping("/search")
    public List<Resident> search(@RequestParam String q) { return repo.findByNameContainingIgnoreCase(q); }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Resident r) { return ResponseEntity.ok(repo.save(r)); }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Resident r) {
        return repo.findById(id).map(existing -> { r.setId(id); return ResponseEntity.ok(repo.save(r)); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) { repo.deleteById(id); return ResponseEntity.ok(Map.of("message", "Deleted!")); }
}
