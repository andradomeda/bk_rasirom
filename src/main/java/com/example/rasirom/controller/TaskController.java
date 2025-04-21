package com.example.rasirom.controller;

import com.example.rasirom.model.Task;
import com.example.rasirom.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepo;

    public TaskController(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    // Endpoint pentru adăugarea unui subtask la un task existent
    @PostMapping("/{id}/subtasks")
    public String addSubtask(@PathVariable Long id, @RequestBody Task subtask) {
        // Căutăm task-ul cu id-ul dat
        Optional<Task> taskOpt = taskRepo.findById(id);

        if (taskOpt.isEmpty()) {
            return "Task-ul nu a fost găsit!";
        }

        Task parentTask = taskOpt.get();

        // Asociem subtask-ul la task-ul părinte
        subtask.setParent(parentTask);

        // Salvăm subtask-ul
        taskRepo.save(subtask);

        return "Subtask-ul a fost adăugat cu succes!";
    }
}
