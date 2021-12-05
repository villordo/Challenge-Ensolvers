package com.challenge.api.services;

import com.challenge.api.models.Folder;
import com.challenge.api.repositories.FolderRepository;
import com.challenge.api.services.interfaces.IFolderService;
import com.challenge.exceptions.AlreadyExistsException;
import com.challenge.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements IFolderService {

    @Autowired
    private FolderRepository folderRepository;


    @Override
    public List<Folder> getAll() {
        return folderRepository.findAll();
    }

    @Override
    public Folder getById(Integer idFolder) throws NotFoundException {
        return folderRepository.findById(idFolder)
                .orElseThrow(() -> new NotFoundException("Folder doesn't exists."));
    }

    @Override
    public Folder addFolder(Folder folder) throws AlreadyExistsException {
        if(folderRepository.findByName(folder.getName()).isPresent()){
            throw new AlreadyExistsException("The folder name already exists.");
        }
        return folderRepository.save(folder);
    }

    @Override
    public void removeFolder(Integer idFolder) throws NotFoundException {
        Folder toRemove = folderRepository.findById(idFolder)
                .orElseThrow(() -> new NotFoundException("Folder doesn't exist."));
        folderRepository.delete(toRemove);
    }

    @Override
    public void updateFolder(Folder updatedFolder) throws NotFoundException {
        folderRepository.findById(updatedFolder.getId())
                .orElseThrow(() -> new NotFoundException("Folder doesn't exist."));
        folderRepository.save(updatedFolder);
    }
}
