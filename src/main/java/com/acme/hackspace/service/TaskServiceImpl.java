package com.acme.hackspace.service;

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

    @Override
    public Task updateTask(Long id, Task newTask) {
        System.out.println(newTask.getName()+' '+newTask.getCompleted());
        Task task = getTaskById(id);
        
        //if not null
        if(task.getCompleted() == false){
            if(newTask.getName() != task.getName())
                task.setName(newTask.getName());
            if(newTask.getCompleted() == true)
                task.setCompleted(true);
            System.out.println(task.getId()+' '+task.getName()+' '+task.getCompleted()+' '+task.getCreatedAt());
            return taskRepository.save(task);
        }
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        //if not null
        taskRepository.delete(task);
    }


    

    
}
