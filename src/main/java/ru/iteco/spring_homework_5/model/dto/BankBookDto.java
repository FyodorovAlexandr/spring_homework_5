package ru.iteco.spring_homework_5.model.dto;

import lombok.Builder;
import lombok.Data;
import ru.iteco.spring_homework_5.validation.Created;
import ru.iteco.spring_homework_5.validation.Currency;
import ru.iteco.spring_homework_5.validation.Updated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    @Null(groups = Created.class)
    @NotNull(groups = Updated.class)
    private Integer id;

    @NotNull
    private Integer userId;

    @NotBlank(message = "пустой!")
    private String number;

    @PositiveOrZero
    @NotNull
    private BigDecimal amount;

    @Currency
    @NotNull
    private String currency;
}
