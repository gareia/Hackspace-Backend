package com.acme.hackspace.service;

import com.acme.hackspace.model.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task createTask(Task task);
    Page<Task> getAllTasks(Pageable pageable);
    Task getTaskById(Long id);
    Task updateTask(Long id, Task newTask);
    void deleteTask(Long id);
}
