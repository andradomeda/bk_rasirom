package com.example.rasirom.controller;

import com.example.rasirom.model.Responsabil;
import com.example.rasirom.repository.ResponsabilRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsabili")
public class ResponsabilController {

    private final ResponsabilRepository responsabilRepo;

    public ResponsabilController(ResponsabilRepository responsabilRepo) {
        this.responsabilRepo = responsabilRepo;
    }

    @GetMapping
    public List<Responsabil> getAllResponsabili() {
        return responsabilRepo.findAll();
    }

    @PostMapping
    public Responsabil createResponsabil(@RequestBody Responsabil responsabil) {
        return responsabilRepo.save(responsabil);
    }
}
