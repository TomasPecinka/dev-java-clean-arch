package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;
import com.example.tasks.core.entities.Task;

public class UpdateTaskTitle {
    private final TaskRepository repo;

    public UpdateTaskTitle(TaskRepository repo) {
        this.repo = repo;
    }

    public Task execute(String id, String newTitle) {
        // id validation could be added here if needed (check for null, empty, uuid format etc.)

        if (newTitle == null || newTitle.trim().isEmpty())
            throw new IllegalArgumentException("New title cannot be null or empty");

        var task = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " not found"));

        task.setTitle(newTitle);
        repo.save(task);

        return task;
    }
}
