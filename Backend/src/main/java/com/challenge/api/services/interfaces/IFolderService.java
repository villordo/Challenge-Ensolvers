package com.challenge.api.services.interfaces;

import com.challenge.api.models.Folder;
import com.challenge.exceptions.AlreadyExistsException;
import com.challenge.exceptions.NotFoundException;

import java.util.List;

public interface IFolderService {
    List<Folder> getAll();
    Folder getById(Integer idFolder) throws NotFoundException;
    Folder addFolder(Folder folder) throws AlreadyExistsException;
    void removeFolder(Integer idFolder) throws NotFoundException;
    void updateFolder(Folder updatedFolder) throws NotFoundException;
}
