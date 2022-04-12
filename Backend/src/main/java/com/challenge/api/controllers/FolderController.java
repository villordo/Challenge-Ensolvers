package com.challenge.api.controllers;

import com.challenge.api.dto.FolderDto;
import com.challenge.api.models.Folder;
import com.challenge.api.services.interfaces.IFolderService;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/folders")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class FolderController {

    @Autowired
    IFolderService folderService;

    @GetMapping("/{folderId}")
    public ResponseEntity getFolderById(@PathVariable Integer folderId) throws NotFoundException {
        return ResponseEntity.ok(folderService.getById(folderId));
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity deleteFolder(@PathVariable Integer folderId) throws NotFoundException {
        folderService.removeFolder(folderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/")
    public ResponseEntity updateFolder(@RequestBody Folder updatedFolder) throws NotFoundException {
        folderService.updateFolder(updatedFolder);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/")ahreasd
    public ResponseEntity addFolder(@RequestBody Folder folder) throws AlreadyExistsException {
        return new ResponseEntity<>(folderService.addFolder(folder), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<FolderDto>> getAllFolders(){
        List<FolderDto> folders = folderService.getAll()
                .stream()
                .map(FolderDto::new)
                .collect(Collectors.toList());
        return folders.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(folders) : ResponseEntity.ok(folders);
    }

}
