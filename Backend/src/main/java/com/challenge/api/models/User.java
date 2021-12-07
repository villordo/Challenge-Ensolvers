package com.challenge.api.models;

import com.challenge.api.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String password;

    @Column(unique = true)
    private String email;

    public static User buildUser(UserRequestDto userDto)   {

        return User.builder()
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }
}
