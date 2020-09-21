package com.acme.hackspace.controller;

import javax.validation.Valid;

import com.acme.hackspace.model.Task;
import com.acme.hackspace.resource.SaveTaskResource;
import com.acme.hackspace.resource.TaskResource;
import com.acme.hackspace.service.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResource> createTask(@Valid @RequestBody SaveTaskResource resource){
        TaskResource taskResource=convertToResource(taskService.createTask(convertToEntity(resource)));
        return new ResponseEntity<TaskResource>(taskResource, HttpStatus.CREATED);
    }

    private Task convertToEntity(SaveTaskResource resource){
        return mapper.map(resource, Task.class);
    }
    private TaskResource convertToResource(Task entity){
        return mapper.map(entity, TaskResource.class);
    }
}