package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;

public class DeleteTask {
    private final TaskRepository repo;

    public DeleteTask(TaskRepository repo) {
        this.repo = repo;
    }

    public void execute(String id) {
        repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + id + " not found"));

        repo.delete(id);
    }
}
