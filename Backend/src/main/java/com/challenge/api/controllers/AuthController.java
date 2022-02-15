package com.challenge.api.controllers;

import com.challenge.api.dto.UserRequestDto;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.InvalidAuthenticationException;
import com.challenge.api.exceptions.NotFoundException;
import com.challenge.api.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AuthController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserRequestDto newUser) throws AlreadyExistsException {
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDto user) throws NotFoundException, InvalidAuthenticationException {
        userService.login(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
