package com.crm.pfe.services;

import com.crm.pfe.entities.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Long id,Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task updateTask(Long id, Task task);

    String deletetask(Long id);
}
