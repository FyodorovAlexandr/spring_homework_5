package ru.iteco.spring_homework_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.spring_homework_5.model.entity.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    CurrencyEntity findByName(String name);
}
