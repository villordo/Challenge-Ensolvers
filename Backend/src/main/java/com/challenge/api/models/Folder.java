package com.challenge.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "folders")
public class Folder {

    @Id
    @Column(name = "id_folder")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "Field name should not be null or empty")
    @Column(length = 25)
    private String name;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<Task> tasks;

}