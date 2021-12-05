package com.challenge.api.dto;

import com.challenge.api.models.Folder;
import lombok.Data;

@Data
public class FolderDto {

    private String name;

    public FolderDto(Folder folder) {
        this.name = folder.getName();
    }
}
