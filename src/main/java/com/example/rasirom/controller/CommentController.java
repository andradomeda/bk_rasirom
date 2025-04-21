package com.example.rasirom.controller;

import com.example.rasirom.model.Comment;
import com.example.rasirom.model.Task;
import com.example.rasirom.model.Responsabil;
import com.example.rasirom.repository.CommentRepository;
import com.example.rasirom.repository.TaskRepository;
import com.example.rasirom.repository.ResponsabilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Comment addComment(@RequestBody Comment comment) {
        Optional<Task> taskOpt = taskRepo.findById(comment.getTask().getId());
        Optional<Responsabil> respOpt = responsabilRepo.findById(comment.getResponsabil().getId());

        if (taskOpt.isEmpty() || respOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task sau Responsabil inexistent!");
        }

        Task task = taskOpt.get();
        Responsabil responsabil = respOpt.get();

        // Restricție: comment-ul poate fi adăugat doar de responsabilul actual
        if (!task.getResponsabil().getId().equals(responsabil.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Doar responsabilul asignat poate comenta!");
        }

        comment.setTask(task);
        comment.setResponsabil(responsabil);
        comment.setDate(LocalDate.now());

        return commentRepo.save(comment);
    }
}

