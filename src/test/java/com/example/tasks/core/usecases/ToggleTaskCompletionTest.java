package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToggleTaskCompletionTest {
    @Test
    void togglesTaskCompletion() {
        var repo = new InMemoryTaskRepository();
        var createUseCase = new CreateTask(repo);
        var toggleUseCase = new ToggleTaskCompletion(repo);

        createUseCase.execute("1", "My Task");
        assertFalse(repo.findById("1").orElseThrow().isCompleted());

        var updated = toggleUseCase.execute("1");
        assertTrue(updated.isCompleted());

        toggleUseCase.execute("1");
        assertFalse(repo.findById("1").orElseThrow().isCompleted());
    }

    @Test
    void throwsIfTaskNotFound() {
        var repo = new InMemoryTaskRepository();
        var toggleUseCase = new ToggleTaskCompletion(repo);

        assertThrows(IllegalArgumentException.class, () ->
                toggleUseCase.execute("99"));
    }
}
