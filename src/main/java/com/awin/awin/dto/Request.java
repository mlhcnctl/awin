package com.awin.awin.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Request {
    @NotEmpty(message = "The sourceCurrency can not be empty!")
    @Length(min = 3, max = 3)
    private String sourceCurrency;
    @NotEmpty(message = "The targetCurrency can not be empty!")
    private String targetCurrency;
    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull
    private double amount;
}
