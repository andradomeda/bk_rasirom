package com.example.rasirom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titlu;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descriere;

    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsabil_id", nullable = false)
    @JsonBackReference  // Evită serializarea recursivă a responsabilului
    private Responsabil responsabil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore  // Evită serializarea recursivă a părinților taskurilor
    private Task parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference  // Evită loop-ul la serializare pentru subtasks
    private List<Task> subtasks = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Evită loop-ul la serializare pentru comments
    private List<Comment> comments = new ArrayList<>();
    // Constructori, Getters & Setters

    public Task() {}

    public Task(String titlu, String descriere, LocalDate dueDate, Responsabil responsabil, Task parent) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.dueDate = dueDate;
        this.responsabil = responsabil;
        this.parent = parent;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Responsabil getResponsabil() {
        return responsabil;
    }

    public void setResponsabil(Responsabil responsabil) {
        this.responsabil = responsabil;
    }

    public Task getParent() {
        return parent;
    }

    public void setParent(Task parent) {
        this.parent = parent;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
