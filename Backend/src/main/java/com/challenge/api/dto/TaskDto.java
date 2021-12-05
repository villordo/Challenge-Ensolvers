package com.challenge.api.dto;

import com.challenge.api.models.Task;
import lombok.Data;

@Data
public class TaskDto {

    private String description;

    public TaskDto(Task task) {
        this.description = task.getDescription();
    }
}
