package com.example.rasirom.repository;

import com.example.rasirom.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query("SELECT t FROM Task t WHERE " +
            "(:titlu IS NULL OR t.titlu LIKE %:titlu%) AND " +
            "(:descriere IS NULL OR t.descriere LIKE %:descriere%) AND " +
            "(:dueDate IS NULL OR t.dueDate = :dueDate) AND " +
            "(:responsabilId IS NULL OR t.responsabil.id = :responsabilId)")
    List<Task> searchTasks(
            @Param("titlu") String titlu,
            @Param("descriere") String descriere,
            @Param("dueDate") LocalDate dueDate,
            @Param("responsabilId") Long responsabilId
    );
}

