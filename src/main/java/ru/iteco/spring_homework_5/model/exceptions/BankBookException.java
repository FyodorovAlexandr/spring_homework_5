package ru.iteco.spring_homework_5.model.exceptions;

public class BankBookException extends RuntimeException{
    public BankBookException(String message) {
        super(message);
    }
}
