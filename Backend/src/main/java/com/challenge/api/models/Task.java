package com.challenge.api.models;

import com.challenge.api.dto.TaskRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id_task")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Field description should not be null or empty")
    @Column(length = 50)
    private String description;

    private Boolean checked = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_folder", referencedColumnName = "id_folder")
    @JsonBackReference
    private Folder folder;

     public static Task buildTask(TaskRequestDto taskRequestDto){
         return Task.builder()
                 .description(taskRequestDto.getDescription())
                 .checked(false)
                 .build();
     }



}