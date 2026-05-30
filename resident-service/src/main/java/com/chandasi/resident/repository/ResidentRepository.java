package com.chandasi.resident.repository;

import com.chandasi.resident.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// JpaRepository — ye automatically CRUD operations deta hai
// Matlab Create, Read, Update, Delete sab kuch free mein!
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    // Naam se search karo (case insensitive)
    List<Resident> findByNameContainingIgnoreCase(String name);

    // Village se search karo
    List<Resident> findByVillage(String village);

    // Ghar number se dhundho
    List<Resident> findByHouseNumber(String houseNumber);
}
