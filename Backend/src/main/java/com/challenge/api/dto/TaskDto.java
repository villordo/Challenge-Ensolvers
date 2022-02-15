package com.challenge.api.dto;

import com.challenge.api.models.Task;
import lombok.Data;

@Data
public class TaskDto {

    private Integer id;
    private String description;
    private Boolean checked;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.checked = task.getChecked();
    }
}
