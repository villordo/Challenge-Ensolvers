package com.challenge.api.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
}
