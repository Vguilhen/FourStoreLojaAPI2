package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private String sku;
    private int quantity;
    private String customerName;
    private String customerCpf;
}
