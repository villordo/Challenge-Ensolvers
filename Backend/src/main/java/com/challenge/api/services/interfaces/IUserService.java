package com.challenge.api.services.interfaces;

import com.challenge.api.dto.UserRequestDto;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.InvalidAuthenticationException;
import com.challenge.api.exceptions.NotFoundException;
import com.challenge.api.models.User;

public interface IUserService {
    User save(UserRequestDto user) throws AlreadyExistsException;
    User login(UserRequestDto user) throws NotFoundException, InvalidAuthenticationException;
}
