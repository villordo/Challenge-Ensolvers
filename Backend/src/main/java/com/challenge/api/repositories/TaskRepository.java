package com.challenge.api.repositories;

import com.challenge.api.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Optional<Task> findByDescription(String description);
}
