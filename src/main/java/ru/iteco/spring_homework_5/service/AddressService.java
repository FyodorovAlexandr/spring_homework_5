package ru.iteco.spring_homework_5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.iteco.spring_homework_5.model.dto.AddressDto;
import ru.iteco.spring_homework_5.model.entity.AddressEntity;
import ru.iteco.spring_homework_5.model.entity.UserEntity;
import ru.iteco.spring_homework_5.repository.AddressRepository;

@Component
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public UserEntity getUserByAddress(AddressDto addressDto) {
        AddressEntity addressEntity = addressRepository.findByCountryAndCityAndStreetAndHome(addressDto.getCountry(), addressDto.getCity(),
                addressDto.getStreet(), addressDto.getHome())
                .orElseThrow(() -> new RuntimeException("Адрес не найден!"));
        return addressEntity.getUser();
    }
}
