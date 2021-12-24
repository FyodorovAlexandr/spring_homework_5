package ru.iteco.spring_homework_5.service;

import org.springframework.http.ResponseEntity;
import ru.iteco.spring_homework_5.model.dto.BankBookDto;

import java.util.List;

public interface BankBookService {
    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto findById(Integer bankBookId);
    BankBookDto createBankBook(BankBookDto bankBookDto);
    BankBookDto updateBankBook(BankBookDto bankBookDto);
    ResponseEntity<String> deleteBankBookById(Integer bankBookId);
    ResponseEntity<String> deleteBankBookByUserId(Integer userId);
}
