package com.chandasi.resident.model;

import jakarta.persistence.*;
import lombok.*;

// @Entity — ye class ek database table hai
// @Table  — table ka naam "residents" hoga
// @Data   — Lombok automatically getter/setter banayega
@Entity
@Table(name = "residents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    // @Id — ye primary key hai (unique ID)
    // @GeneratedValue — ID automatically generate hogi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Naam
    private String houseNumber; // Ghar number
    private String street;      // Gali
    private String village;     // Gaon
    private String phone;       // Phone
    private String profession;  // Pehsa
    private String avatar;      // Emoji
    private Double latitude;    // Map location
    private Double longitude;   // Map location
}
