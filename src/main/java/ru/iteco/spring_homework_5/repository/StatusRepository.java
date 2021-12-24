package ru.iteco.spring_homework_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.spring_homework_5.model.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    StatusEntity findByName(String name);
}