package com.example.rasirom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsabil")
public class Responsabil {

    // Identificatorul unic al responsabilului, generat automat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Numele responsabilului (obligatoriu)
    @Column(nullable = false)
    private String nume;

    // Adresa de email a responsabilului (obligatorie și unică)
    @Column(nullable = false, unique = true)
    private String email;

    // Lista de taskuri asociate responsabilului
    // JsonManagedReference previne bucla infinită la serializare
    @OneToMany(mappedBy = "responsabil", cascade = CascadeType.ALL)
    private List<Task> taskuri = new ArrayList<>();

    // Constructori
    public Responsabil() {}

    // Getteri și setteri
    public Long getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getTaskuri() {
        return taskuri;
    }

    public void setTaskuri(List<Task> taskuri) {
        this.taskuri = taskuri;
    }
}
