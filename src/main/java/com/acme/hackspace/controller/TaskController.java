package com.acme.hackspace.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.acme.hackspace.model.Task;
import com.acme.hackspace.resource.CreateTaskDto;
import com.acme.hackspace.resource.TaskResource;
import com.acme.hackspace.resource.UpdateTaskDto;
import com.acme.hackspace.service.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
@Tag(name="Tasks", description="Tasks API")
public class TaskController {
    
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TaskService taskService;

    @Operation(
        summary = "Create task",
        description = "Creates a task with given data",
        tags = "tasks"
    )
    @ApiResponses(
        value = {@ApiResponse( responseCode = "201",
                                description = "Task created",
                                content = @Content(
                                    schema = @Schema(implementation = TaskResource.class)
                                )
                            )
                }
    )
    @PostMapping
    public ResponseEntity<TaskResource> createTask(@Valid @RequestBody CreateTaskDto createDto){
        TaskResource taskResource = convertToResource(taskService.createTask(convertCreateDtoToEntity(createDto)));
        return new ResponseEntity<TaskResource>(taskResource, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Find all tasks",
        description = "Find all tasks in the database",
        tags = "tasks"
    )
    @ApiResponses(
        value = {@ApiResponse( responseCode = "200",
                                description = "Successful operation",
                                content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = TaskResource.class))
                                )
                            )
                }
    )
    @GetMapping //paginator shouldnt have sort [sort...], tomcat url doesnt accept braces
    public Page<TaskResource> getAllTasks(Pageable pageable){
        Page<Task> tasks = taskService.getAllTasks(pageable);
        List<TaskResource> resources = tasks.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(
        summary = "Find task by id",
        description = "Find task by integer id",
        tags = "tasks"
    )
    @ApiResponses(
        value = {@ApiResponse( responseCode = "200",
                                description = "Successful operation",
                                content = @Content(
                                    schema = @Schema(implementation = TaskResource.class)
                                )
                            )/*,
                @ApiResponse(   responseCode = "404",
                                description = "Task not found",
                                content = @Content(
                                    schema = @Schema(implementation = )
                                )
                            )*/
                }
    )
    @GetMapping("/{id}")
    public TaskResource getTaskById(@PathVariable Long id){
        return convertToResource(taskService.getTaskById(id));
    }

    @Operation(
        summary = "Update task",
        description = "Update task by id and given data",
        tags = "tasks"
    )
    @ApiResponses(
        value = {@ApiResponse( responseCode = "200",
                                description = "Successful operation",
                                content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = TaskResource.class))
                                )
                            )
                }
    )
    @PutMapping("/{id}")
    public TaskResource updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDto updateDto){
        return convertToResource(taskService.updateTask(id, convertUpdateDtoToEntity(updateDto)));
    }

    @Operation(
        summary = "Delete task",
        description = "Delete task by id",
        tags = "tasks"
    )
    @ApiResponses(
        value = {@ApiResponse( responseCode = "200",
                                description = "Successful operation",
                                content = @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = TaskResource.class))
                                )
                            )
                }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    

    private TaskResource convertToResource(Task entity){
        return mapper.map(entity, TaskResource.class);
    }
    private Task convertUpdateDtoToEntity(UpdateTaskDto updateDto){
        return mapper.map(updateDto, Task.class);
    }
    private Task convertCreateDtoToEntity(CreateTaskDto createDto){
        return mapper.map(createDto, Task.class);
    }

}