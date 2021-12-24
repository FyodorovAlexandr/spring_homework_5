package ru.iteco.spring_homework_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.spring_homework_5.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
