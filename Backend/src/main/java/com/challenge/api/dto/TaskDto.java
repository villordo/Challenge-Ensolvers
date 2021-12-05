package com.challenge.api.dto;

import com.challenge.api.models.Task;
import lombok.Data;

@Data
public class TaskDto {

    private String description;
    private Boolean checked;

    public TaskDto(Task task) {
        this.description = task.getDescription();
        this.checked = task.getChecked();
    }
}
