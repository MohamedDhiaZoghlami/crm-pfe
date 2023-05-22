package com.crm.pfe.controllers;

import com.crm.pfe.entities.Task;
import com.crm.pfe.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create/{idProject}")
    public Task createTask(@PathVariable Long id,@RequestBody Task task){
        return taskService.createTask(id,task);
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(Long id, Task task) {
        return taskService.updateTask(id,task);
    }

    @DeleteMapping("/delete/{id}")
    public String deletetask(Long id) {
        return taskService.deletetask(id);
    }
}
