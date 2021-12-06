package com.challenge.api.services;

import com.challenge.api.dto.TaskRequestDto;
import com.challenge.api.models.Task;
import com.challenge.api.repositories.FolderRepository;
import com.challenge.api.repositories.TaskRepository;
import com.challenge.api.services.interfaces.ITaskService;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    FolderRepository folderRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllByFolderId(Integer idFolder) throws NotFoundException {
        folderRepository.findById(idFolder)
                .orElseThrow(() -> new NotFoundException("Folder doesn't exists."));
        return taskRepository.findAllByFolderId(idFolder);
    }

    @Override
    public Task getById(Integer idTask) throws NotFoundException {
        return taskRepository.findById(idTask)
                .orElseThrow(() -> new NotFoundException("Task doesn't exists."));
    }

    @Override
    public Task addTask(Task task) throws AlreadyExistsException {
        if(taskRepository.findByDescription(task.getDescription()).isPresent()){
            throw new AlreadyExistsException("The task description already exists.");
        }
        //Task toSave =  Task.buildTask(task);
        return taskRepository.save(task);
    }

    @Override
    public void removeTask(Integer idTask) throws NotFoundException {
        Task toRemove = taskRepository.findById(idTask)
                .orElseThrow(() -> new NotFoundException("Task doesn't exist."));
        taskRepository.delete(toRemove);
    }

    @Override
    public void updateTask(Task updatedTask) throws NotFoundException {
        taskRepository.findById(updatedTask.getId())
                .orElseThrow(() -> new NotFoundException("Task doesn't exist."));
        taskRepository.save(updatedTask);
    }
}
