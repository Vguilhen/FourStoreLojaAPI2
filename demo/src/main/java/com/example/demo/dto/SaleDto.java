package com.example.demo.dto;

import com.example.demo.enums.PaymentMethodEnum;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SaleDto {

    private PaymentMethodEnum paymentMethod;
    private String cardNumber;

}
