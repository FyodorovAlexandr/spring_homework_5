package ru.iteco.spring_homework_5.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.spring_homework_5.model.dto.BankBookDto;
import ru.iteco.spring_homework_5.model.dto.Status;
import ru.iteco.spring_homework_5.model.dto.UserDto;
import ru.iteco.spring_homework_5.model.entity.BankBookEntity;
import ru.iteco.spring_homework_5.model.entity.TransactionEntity;
import ru.iteco.spring_homework_5.model.exceptions.BankBookNotFoundException;
import ru.iteco.spring_homework_5.model.exceptions.CurrencyNotEqualsException;
import ru.iteco.spring_homework_5.repository.BankBookRepository;
import ru.iteco.spring_homework_5.repository.StatusRepository;
import ru.iteco.spring_homework_5.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class TransactionServiceImpl implements TransactionService {

    private final BankBookRepository bankBookRepository;
    private final BankBookService bankBookService;
    private final UserService userService;
    private final StatusRepository statusRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Boolean transferBetweenBankBook(BankBookDto sourceBankBook, BankBookDto targetBankBook, BigDecimal amount) {
        BankBookEntity source = bankBookRepository.findById(sourceBankBook.getId()).orElseThrow(() -> new BankBookNotFoundException("Счет не найден"));
        BankBookEntity target = bankBookRepository.findById(targetBankBook.getId()).orElseThrow(() -> new BankBookNotFoundException("Счет не найден"));
        if (!source.getCurrency().equals(target.getCurrency())) {
            throw new CurrencyNotEqualsException("Счета не совпадают!");
        }

        bankBookRepository.findById(sourceBankBook.getId());

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setSourceBankBook(source);
        transactionEntity.setTargetBankBook(target);
        transactionEntity.setAmount(amount);
        transactionEntity.setInitiationDate(LocalDateTime.now());
        transactionEntity.setStatus(statusRepository.findByName(Status.PROCESSING.getStatus()));

        if (source.getAmount().compareTo(amount) < 0) {
            transactionEntity.setStatus(statusRepository.findByName(Status.DECLINED.getStatus()));
            transactionEntity.setCompletionDate(LocalDateTime.now());
            transactionRepository.save(transactionEntity);
            return false;
        }

        source.setAmount(source.getAmount().subtract(amount));
        target.setAmount(target.getAmount().add(amount));

        bankBookRepository.save(source);
        bankBookRepository.save(target);

        transactionEntity.setStatus(statusRepository.findByName(Status.SUCCESSFUL.getStatus()));
        transactionEntity.setCompletionDate(LocalDateTime.now());
        transactionRepository.save(transactionEntity);
        return true;
    }

    @Override
    @Transactional
    public Boolean transferBetweenUser(UserDto sourceUser, UserDto targetUser, BigDecimal amount, String currency) {
        List<BankBookDto> sourceBankBooks = bankBookService.findByUserId(sourceUser.getId());
        List<BankBookDto> targetBankBooks = bankBookService.findByUserId(targetUser.getId());
        BankBookDto source = sourceBankBooks.stream()
                .filter(bankBookDto -> bankBookDto.getCurrency().equals(currency))
                .findAny()
                .orElseThrow();
        BankBookDto target = targetBankBooks.stream()
                .filter(bankBookDto -> bankBookDto.getCurrency().equals(currency))
                .findAny()
                .orElseThrow();
        return transferBetweenBankBook(source, target, amount);
    }
}
