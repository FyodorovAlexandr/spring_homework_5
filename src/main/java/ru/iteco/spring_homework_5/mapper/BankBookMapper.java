package ru.iteco.spring_homework_5.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.iteco.spring_homework_5.model.dto.BankBookDto;
import ru.iteco.spring_homework_5.model.entity.BankBookEntity;

@Mapper(componentModel = "spring")
public interface BankBookMapper {

    @Mapping(target = "currency", source = "currency.name")
    BankBookDto mapToDto(BankBookEntity bankBookEntity);
    @Mapping(target = "currency.name", source = "currency")
    BankBookEntity mapToEntity(BankBookDto bankBookDto);
}
