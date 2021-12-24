package ru.iteco.spring_homework_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.spring_homework_5.model.entity.AddressEntity;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    Optional<AddressEntity> findByCountryAndCityAndStreetAndHome(String country, String city, String street, String home);
}
