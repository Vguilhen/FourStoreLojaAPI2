package com.example.demo.models;

import com.example.demo.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
public class Sale implements Serializable {

    @Serial
    private static final long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private Double total;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;
    private String sku;
    private String customerName;
    private String customerCpf;
    private String cardNumber;
}
