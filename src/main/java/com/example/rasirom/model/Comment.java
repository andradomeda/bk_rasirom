package com.example.rasirom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // Nu serializăm relația inversă către task
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_task", nullable = false)
    @JsonIgnore
    private Task task;

    // Nu vrem să afișăm responsabilul
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsabil", nullable = false)
    @JsonIgnore
    private Responsabil responsabil;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    // Nu vrem să afișăm data
    @Column(nullable = false)
    private LocalDate date;

    // Constructori, Getters & Setters

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Responsabil getResponsabil() {
        return responsabil;
    }

    public void setResponsabil(Responsabil responsabil) {
        this.responsabil = responsabil;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
