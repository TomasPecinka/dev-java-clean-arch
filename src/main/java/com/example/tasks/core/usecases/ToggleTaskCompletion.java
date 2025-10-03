package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;
import com.example.tasks.core.entities.Task;

public class ToggleTaskCompletion {
    private final TaskRepository repo;

    public ToggleTaskCompletion(TaskRepository repo) {
        this.repo = repo;
    }

    public Task execute(String id) {
        var task = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " not found"));

        task.toggleCompleted();
        repo.save(task);

        return task;
    }
}
