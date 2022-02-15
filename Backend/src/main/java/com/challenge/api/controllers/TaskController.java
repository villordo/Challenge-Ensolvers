package com.challenge.api.controllers;

import com.challenge.api.dto.TaskDto;
import com.challenge.api.dto.TaskRequestDto;
import com.challenge.api.models.Task;
import com.challenge.api.services.interfaces.ITaskService;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*", methods= {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class TaskController {

    @Autowired
    ITaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity getTaskById(@PathVariable Integer taskId) throws NotFoundException {
        return ResponseEntity.ok(taskService.getById(taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Integer taskId) throws NotFoundException {
        taskService.removeTask(taskId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/")
    public ResponseEntity updateTask(@RequestBody Task updatedTask) throws NotFoundException {
        taskService.updateTask(updatedTask);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/")
    public ResponseEntity addTask(@RequestBody Task task) throws AlreadyExistsException {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllFolders(){
        List<TaskDto> tasks = taskService.getAll()
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return tasks.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(tasks) : ResponseEntity.ok(tasks);
    }

    @RequestMapping(params="folder", method = RequestMethod.GET)
    public ResponseEntity<List<TaskDto>> getTasksByFolder(@RequestParam(value = "folder") Integer folder) throws NotFoundException {
        List<TaskDto> tasks = taskService.getAllByFolderId(folder)
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
        return tasks.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(tasks) : ResponseEntity.ok(tasks);
    }
}
