package com.example.rasirom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {

    // ID-ul unic al comentariului, generat automat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // Relația cu taskul asociat comentariului (obligatorie)
    // @JsonIgnore previne serializarea recursivă în JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_task", nullable = false)
    @JsonIgnore
    private Task task;

    // Relația cu responsabilul care a făcut comentariul (obligatorie)
    // @JsonIgnore pentru a nu expune datele responsabilului în JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsabil", nullable = false)
    @JsonIgnore
    private Responsabil responsabil;

    // Conținutul efectiv al comentariului (obligatoriu)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    // Data la care a fost adăugat comentariul
    @Column(nullable = false)
    private LocalDate date;

    // Constructori, Getteri și Setteri
    public Comment(Task task, Responsabil responsabil, String text, LocalDate date) {
        this.task = task;
        this.responsabil = responsabil;
        this.text = text;
        this.date = date;
    }

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
