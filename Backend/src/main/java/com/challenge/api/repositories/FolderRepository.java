package com.challenge.api.repositories;


import com.challenge.api.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Integer> {
    Optional<Folder> findByName(String name);

}