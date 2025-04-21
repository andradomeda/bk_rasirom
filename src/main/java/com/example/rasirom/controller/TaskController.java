package com.example.rasirom.controller;

import com.example.rasirom.model.Task;
import com.example.rasirom.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


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
    @PatchMapping("/{id}")
    public Task patchTask(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task-ul cu id-ul " + id + " nu a fost găsit."
                ));

        updates.forEach((field, value) -> {
            switch (field) {
                case "titlu":
                    task.setTitlu((String) value);
                    break;
                case "descriere":
                    task.setDescriere((String) value);
                    break;
                case "dueDate":
                    // value vine ca String "YYYY-MM-DD"
                    task.setDueDate(LocalDate.parse((String) value));
                    break;
                default:
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Câmp necunoscut sau neupdatabil: " + field
                    );
            }
        });

        return taskRepo.save(task);
    }
}
