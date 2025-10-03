package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateTaskTitleTest {
    @Test
    void updatesTaskWithValidTitle(){
        // Common setup could be refactored with @BeforeEach, kept inline here for clarity.
        var repo = new InMemoryTaskRepository();
        var createUseCase = new CreateTask(repo);
        var updateUseCase = new UpdateTaskTitle(repo);

        createUseCase.execute("1", "Title");
        var updatedTask = updateUseCase.execute("1", "Updated Title");

        assertEquals("Updated Title", updatedTask.getTitle());
    }

    @Test
    void throwsOnEmptyTitle() {
        var repo = new InMemoryTaskRepository();
        var createUseCase = new CreateTask(repo);
        var updateUseCase = new UpdateTaskTitle(repo);

        createUseCase.execute("1", "Valid Title");

        assertThrows(IllegalArgumentException.class, () ->
                updateUseCase.execute("1", " "));
    }

    @Test
    void throwsOnNullTitle() {
        var repo = new InMemoryTaskRepository();
        var createUseCase = new CreateTask(repo);
        var updateUseCase = new UpdateTaskTitle(repo);

        createUseCase.execute("1", "Valid Title");

        assertThrows(IllegalArgumentException.class, () ->
                updateUseCase.execute("1", null));
    }

    @Test
    void throwsIfTaskNotFound() {
        var repo = new InMemoryTaskRepository();
        var updateUseCase = new UpdateTaskTitle(repo);

        assertThrows(IllegalArgumentException.class, () ->
                updateUseCase.execute("99", "Title."));
    }
}
