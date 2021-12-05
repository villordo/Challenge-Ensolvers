package com.challenge.api.services.interfaces;

import com.challenge.api.models.Task;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.NotFoundException;

import java.util.List;

public interface ITaskService {
    List<Task> getAll();
    Task getById(Integer idTask) throws NotFoundException;
    Task addTask(Task task) throws AlreadyExistsException;
    void removeTask(Integer idTask) throws NotFoundException;
    void updateTask(Task updatedTask) throws NotFoundException;
}
