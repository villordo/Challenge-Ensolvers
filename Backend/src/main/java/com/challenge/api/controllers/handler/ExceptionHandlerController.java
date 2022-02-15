package com.challenge.api.controllers.handler;

import com.challenge.api.dto.ErrorDtoResponse;
import com.challenge.api.exceptions.AlreadyExistsException;
import com.challenge.api.exceptions.InvalidAuthenticationException;
import com.challenge.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<ErrorDtoResponse> handleInvalidAuthenticationException(InvalidAuthenticationException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("InvalidAuthenticationException")
                .build());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDtoResponse> handleAlreadyExistsException(AlreadyExistsException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("AlreadyExistsException")
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDtoResponse> handleNotFoundException(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("NotFoundException")
                .build());
    }

}
