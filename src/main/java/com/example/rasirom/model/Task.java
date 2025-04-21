package com.example.rasirom.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // Constructors
    public Task() {}
    public Task(String description) {
        this.description = description;
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getDescription() { return description; }
    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
}