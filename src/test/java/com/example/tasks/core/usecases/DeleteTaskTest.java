package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskTest {
    @Test
    void deletesExistingTask() {
        var repo = new InMemoryTaskRepository();
        var createUseCase = new CreateTask(repo);
        var deleteUseCase = new DeleteTask(repo);

        createUseCase.execute("1", "Task to delete");
        deleteUseCase.execute("1");

        assertTrue(repo.findById("1").isEmpty());
    }

    @Test
    void throwsIfTaskNotFound() {
        var repo = new InMemoryTaskRepository();
        var deleteUseCase = new DeleteTask(repo);

        assertThrows(IllegalArgumentException.class, () ->
                deleteUseCase.execute("99"));
    }
}