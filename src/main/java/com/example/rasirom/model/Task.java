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

    // Identificatorul unic pentru fiecare task, generat automat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Titlul taskului, nu poate fi null, max 200 caractere
    @Column(nullable = false, length = 200)
    private String titlu;

    // Descrierea detaliată a taskului
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descriere;

    // Data limită pentru finalizarea taskului (poate fi null)
    private LocalDate dueDate;

    // Relația ManyToOne cu entitatea Responsabil – un task are un responsabil
    // JsonBackReference evită serializarea recursivă în JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsabil_id", nullable = false)
<<<<<<< HEAD
    @JsonBackReference
=======
    @JsonBackReference("task-responsabil")
>>>>>>> e37e565 (reparare bugs rute)
    private Responsabil responsabil;

    // Relație ManyToOne pentru taskul părinte, în cazul în care acest task este subtask
    // JsonIgnore evită serializarea părintelui pentru a preveni loop-uri
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
<<<<<<< HEAD
    @JsonIgnore
=======
    @JsonBackReference("task-subtask")
>>>>>>> e37e565 (reparare bugs rute)
    private Task parent;

    // Relație OneToMany pentru lista de subtasks care aparțin acestui task
    // JsonManagedReference este folosit pentru a permite serializarea corectă în JSON
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
<<<<<<< HEAD
    @JsonManagedReference
=======
    @JsonManagedReference("task-subtask")
>>>>>>> e37e565 (reparare bugs rute)
    private List<Task> subtasks = new ArrayList<>();

    // Comentariile asociate taskului – se șterg automat dacă taskul este șters
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
<<<<<<< HEAD
    @JsonManagedReference
=======
    @JsonManagedReference("task-comment")
>>>>>>> e37e565 (reparare bugs rute)
    private List<Comment> comments = new ArrayList<>();

    // Constructori
    public Task() {}

    public Task(String titlu, String descriere, LocalDate dueDate, Responsabil responsabil, Task parent) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.dueDate = dueDate;
        this.responsabil = responsabil;
        this.parent = parent;
    }

    // Getteri și setteri
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
