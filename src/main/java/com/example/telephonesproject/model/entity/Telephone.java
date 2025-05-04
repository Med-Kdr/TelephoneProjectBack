package com.example.telephonesproject.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iphone;

    private String etatBatterie;

    @Column(unique = true)
    private String imei;

    private String stockage;

    private Long prix;
    private String nom;

    @Column(name = "date_creation", updatable = false)
    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "HH:mm dd/MM/yyyy")
    private LocalDateTime dateCreation;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }


    private String statut; // en_stock, vendu, etc.

}
