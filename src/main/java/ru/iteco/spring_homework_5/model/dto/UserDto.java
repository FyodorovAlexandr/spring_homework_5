package ru.iteco.spring_homework_5.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Builder
public class UserDto {

    private Integer id;
    private String name;
    @Email
    private String email;
    private AddressDto address;

}