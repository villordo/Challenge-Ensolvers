package com.challenge.api.services;

import com.challenge.api.dto.UserRequestDto;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.InvalidAuthenticationException;
import com.challenge.api.exceptions.NotFoundException;
import com.challenge.api.models.User;
import com.challenge.api.repositories.UserRepository;
import com.challenge.api.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(UserRequestDto user) throws AlreadyExistsException {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new AlreadyExistsException("This user already exists");

        return userRepository.save(User.buildUser(user));
    }

    @Override
    public User login(UserRequestDto user) throws NotFoundException, InvalidAuthenticationException {
        Optional<User> toCheck = userRepository.findByEmail(user.getEmail());
        if(toCheck.isEmpty())
            throw new NotFoundException("User not found.");

        if(!(toCheck.get().getPassword().equals(user.getPassword()))){
            throw new InvalidAuthenticationException("Password incorrect.");
        }
        return User.buildUser(user);
    }
}
