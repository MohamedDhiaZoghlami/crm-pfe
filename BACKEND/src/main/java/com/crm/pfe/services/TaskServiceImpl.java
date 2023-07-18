package com.crm.pfe.services;

import com.crm.pfe.entities.Project;
import com.crm.pfe.entities.Task;
import com.crm.pfe.repository.ProjectRepository;
import com.crm.pfe.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    public final TaskRepository taskRepository;
    public final ProjectRepository projectRepository;

    @Override
    public Task createTask(Long id, Task task) {
        Project project = projectRepository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));
        task.setProject(project);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()->new RuntimeException("task not found"));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        return taskRepository.findById(id).map(t->{
            t.setName(task.getName());
            t.setDescription(task.getDescription());
            t.setStatus(task.getStatus());
            t.setStart_date(task.getStart_date());
            t.setEnd_date(task.getEnd_date());
            return taskRepository.save(t);
        }).orElseThrow(()->new RuntimeException("task not found"));
    }

    @Override
    public String deletetask(Long id) {
        taskRepository.deleteById(id);
        return "Task deleted with success.";
    }
}
