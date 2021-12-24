package ru.iteco.spring_homework_5.service;

import ru.iteco.spring_homework_5.model.dto.BankBookDto;
import ru.iteco.spring_homework_5.model.dto.UserDto;

import java.math.BigDecimal;

public interface TransactionService {

    Boolean transferBetweenBankBook(BankBookDto sourceBankBook, BankBookDto targetBankBook, BigDecimal amount);
    Boolean transferBetweenUser(UserDto sourceUser, UserDto targetUser, BigDecimal amount, String currency);
}
