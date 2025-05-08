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
@RequestMapping("/tasks") // Prefix comun pentru toate rutele
public class TaskController {

    private final TaskRepository taskRepo;
    // Constructor pentru repository-ul de Task-uri
    public TaskController(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }
    //returneaza toate taskurile
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    //adauga un task
    @PostMapping(consumes = "application/json")

    public Task addTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    //adauga subtask
    @PostMapping("/{id}/subtasks")
    public String addSubtask(@PathVariable Long id, @RequestBody Task subtask) {
        Optional<Task> taskOpt = taskRepo.findById(id);

        if (taskOpt.isEmpty()) {
            return "Task-ul nu a fost găsit!";
        }

        Task parentTask = taskOpt.get();
        subtask.setParent(parentTask);
        taskRepo.save(subtask);

        return "Subtask-ul a fost adăugat cu succes!";
    }


    // Actualizează parțial câmpurile unui task (PATCH).
    //Suportă modificarea titlului, descrierii și datei limită (dueDate).
    @PatchMapping("/{id}")
    public Task patchTask(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task-ul cu id-ul " + id + " nu a fost găsit."
                ));

        // Iterăm prin câmpurile trimise și le actualizăm
        updates.forEach((field, value) -> {
            switch (field) {
                case "titlu":
                    task.setTitlu((String) value);
                    break;
                case "descriere":
                    task.setDescriere((String) value);
                    break;
                case "dueDate":
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

    //returneaza task in functie de id
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Task-ul cu id-ul " + id + " nu a fost găsit."
                ));
    }

    @GetMapping("/search")
    public List<Task> searchTasks(
            @RequestParam(required = false) String titlu,
            @RequestParam(required = false) String descriere,
            @RequestParam(required = false) String dueDate,
            @RequestParam(required = false) Long responsabilId
    ) {
        LocalDate parsedDate = null;
        if (dueDate != null && !dueDate.isBlank()) {
            parsedDate = LocalDate.parse(dueDate);
        }
        return taskRepo.searchTasks(titlu, descriere, parsedDate, responsabilId);
    }

}