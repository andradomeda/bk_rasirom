package com.example.rasirom.controller;

import com.example.rasirom.model.Responsabil;
import com.example.rasirom.repository.ResponsabilRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsabili") // Prefix pentru toate rutele
public class ResponsabilController {

    private final ResponsabilRepository responsabilRepo;

    // Constructor pentru repository-ul de responsabili
    public ResponsabilController(ResponsabilRepository responsabilRepo) {
        this.responsabilRepo = responsabilRepo;
    }

   //returneaza toti responsabilii
    @GetMapping
    public List<Responsabil> getAllResponsabili() {
        return responsabilRepo.findAll();
    }

    //adauga un responsabil
    @PostMapping
    public Responsabil createResponsabil(@RequestBody Responsabil responsabil) {
        return responsabilRepo.save(responsabil);
    }
}
