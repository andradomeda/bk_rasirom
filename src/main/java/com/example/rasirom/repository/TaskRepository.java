package com.example.rasirom.repository;

import com.example.rasirom.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // 1. Filtrare după dueDate
    List<Task> findByDueDate(LocalDate dueDate);
    // 2. Filtrare după responsabil
    List<Task> findByResponsabil_Id(Long responsabilId);
}