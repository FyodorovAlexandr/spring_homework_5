package ru.iteco.spring_homework_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.spring_homework_5.model.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
