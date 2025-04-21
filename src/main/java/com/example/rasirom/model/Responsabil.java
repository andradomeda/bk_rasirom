package com.example.rasirom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsabil")
public class Responsabil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "responsabil", cascade = CascadeType.ALL)
    @JsonManagedReference  // Evită loop-ul la serializare
    private List<Task> taskuri = new ArrayList<>();

    // Constructori, Getters & Setters

    public Responsabil() {}

    public Responsabil(String nume, String email, List<Task> taskuri) {
        this.nume = nume;
        this.email = email;
        this.taskuri = taskuri;
    }

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
