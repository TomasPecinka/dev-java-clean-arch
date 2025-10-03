package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListAllTasksTest {
    @Test
    void listsAllCreatedTasks() {
        var repo = new InMemoryTaskRepository();
        var createUseCase = new CreateTask(repo);
        var listAllUseCase = new ListAllTasks(repo);

        createUseCase.execute("1", "Task 1");
        createUseCase.execute("2", "Task 2");

        var tasks = listAllUseCase.execute();

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    void returnsEmptyListIfNoTasksExist() {
        var repo = new InMemoryTaskRepository();
        var listAllUseCase = new ListAllTasks(repo);

        assertTrue(listAllUseCase.execute().isEmpty());
    }
}
