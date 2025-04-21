package com.example.rasirom.controller;

import com.example.rasirom.model.Comment;
import com.example.rasirom.model.Task;
import com.example.rasirom.model.Responsabil;
import com.example.rasirom.repository.CommentRepository;
import com.example.rasirom.repository.TaskRepository;
import com.example.rasirom.repository.ResponsabilRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepo;
    private final TaskRepository taskRepo;
    private final ResponsabilRepository responsabilRepo;

    public CommentController(CommentRepository commentRepo, TaskRepository taskRepo, ResponsabilRepository responsabilRepo) {
        this.commentRepo = commentRepo;
        this.taskRepo = taskRepo;
        this.responsabilRepo = responsabilRepo;
    }

    @PostMapping
    public String addComment(@RequestBody Comment comment) {
        Optional<Task> taskOpt = taskRepo.findById(comment.getTask().getId());
        Optional<Responsabil> respOpt = responsabilRepo.findById(comment.getResponsabil().getId());

        if (taskOpt.isEmpty() || respOpt.isEmpty()) {
            return "Task sau Responsabil inexistent!";
        }

        Task task = taskOpt.get();
        Responsabil responsabil = respOpt.get();

        // Restricție: comment-ul poate fi adăugat doar de responsabilul actual
        if (!task.getResponsabil().getId().equals(responsabil.getId())) {
            return "Doar responsabilul asignat poate comenta!";
        }

        comment.setTask(task);
        comment.setResponsabil(responsabil);
        comment.setDate(LocalDate.now());

        commentRepo.save(comment);
        return "Comentariul a fost adăugat cu succes.";
    }
}

