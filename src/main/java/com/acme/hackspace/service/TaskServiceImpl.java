package com.acme.hackspace.service;

import java.util.Optional;

import com.acme.hackspace.exception.ResourceNotFoundException;
import com.acme.hackspace.model.Task;
import com.acme.hackspace.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task getTaskById(Long id) {

        return taskRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Task", "Id", id));
        
    }

    

    
}
