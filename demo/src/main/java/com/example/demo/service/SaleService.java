package com.example.demo.service;

import com.example.demo.dto.SaleDto;
import com.example.demo.enums.PaymentMethodEnum;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.Sale;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.example.demo.service.CardValidation.checkCardBrand;
import static com.example.demo.service.CardValidation.checkExpiration;

@Service
public class SaleService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public SaleService(OrderRepository orderRepository, ProductRepository productRepository, SaleRepository saleRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public Sale purchase(SaleDto dto) {

        List<Order> orderList = orderRepository.findAll();

        for (Order orderInList : orderList) {
            Sale sale = new Sale();
            sale.setQuantity(orderInList.getQuantity());
            sale.setTotal(orderInList.getTotal());
            sale.setTotal(orderInList.getTotal());
            sale.setSku(orderInList.getSku());
            sale.setDate(LocalDateTime.now());
            sale.setPaymentMethod(dto.getPaymentMethod());//colocar dentro de um if
            sale.setCardNumber(dto.getCardNumber());
            sale.setCustomerName(orderInList.getCustomerName());
            sale.setCustomerCpf(orderInList.getCustomerCpf());

            if (dto.getCardNumber().length() >= 13 && dto.getCardNumber().length() <= 16) {
                String cardBrand = "";
                String cardNumber = dto.getCardNumber();
                boolean flag = checkExpiration(cardNumber);
                if (flag) {
                    cardBrand = checkCardBrand(cardNumber.substring(0, 1),
                            cardNumber.substring(0, 2));
                    System.out.println("Cart??o " + cardBrand + " V??lido, N??mero:" + cardNumber + "\n");
                    saleRepository.save(sale);
                    Optional<Product> productOptional = productRepository.findBySku(orderInList.getSku());

                    Product product;
                    product = productOptional.get();

                    int currentQuantityInStock = product.getQuantity() - orderInList.getQuantity();

                    product.setQuantity(currentQuantityInStock);
                    productRepository.save(product);
                    if (currentQuantityInStock == 0) productRepository.delete(product);

                    orderRepository.deleteBySku(orderInList.getSku());
                    saleRepository.save(sale);
                } else {
                    throw new RuntimeException("Cart??o Inv??lido");
                }
            } else {
                throw new RuntimeException("N??mero de cart??o inv??lido");
            }
        }
        return null;
    }
}
