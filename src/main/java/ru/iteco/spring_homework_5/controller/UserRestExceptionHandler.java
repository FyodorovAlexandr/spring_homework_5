package ru.iteco.spring_homework_5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.spring_homework_5.model.dto.ErrorDto;
import ru.iteco.spring_homework_5.model.exceptions.UserNotFoundException;

@RestControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto("NOT_FOUND", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

}
