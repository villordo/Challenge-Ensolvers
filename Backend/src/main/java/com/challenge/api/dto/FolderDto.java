package com.challenge.api.dto;

import com.challenge.api.models.Folder;
import lombok.Data;

@Data
public class FolderDto {

    private Integer id;
    private String name;

    public FolderDto(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
    }
}
