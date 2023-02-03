package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order saveOrder(OrderDto dto) {

        Optional<Product> findBySku = productRepository.findBySku(dto.getSku());
        findBySku.ifPresent(product -> {

            boolean localVar = true;
            while (localVar) {
                int quantityInStock = product.getQuantity();
                int requestedQuantity = dto.getQuantity();
                Double total;
                try {
                    if (quantityInStock < requestedQuantity || requestedQuantity < 0) {
                        throw new RuntimeException("Você está tentando adicionar " + requestedQuantity + "no carrinho, "
                                + "porém há apenas " + quantityInStock + "no estoue");
                    } else {
                        total = requestedQuantity * product.getPrice();
                        localVar = false;
                    }
                } catch (InputMismatchException e) {
                    throw new InputMismatchException(e.getMessage());
                }
                Order order = new Order();
                order.setSku(dto.getSku());
                order.setQuantity(requestedQuantity);
                order.setTotal(total);
                order.setDate(LocalDateTime.now());
                order.setCustomerName(dto.getCustomerName());

                if (dto.getCustomerCpf().isEmpty()) {
                    order.setCustomerCpf("00000000000");
                } else {
                    if (CpfValidation.isCPF(dto.getCustomerCpf())) {
                        order.setCustomerCpf(dto.getCustomerCpf());
                    } else {
                        throw new RuntimeException("CPF inválido");
                    }

                }
                orderRepository.save(order);
            }
        });
        return null;
    }

    public Order findBySku(String sku) {
        Optional<Order> orderSku = orderRepository.findBySku(sku);

        if (orderSku.isPresent()) {
            return orderSku.get();
        }
        throw new RuntimeException("sku-inexistente");
    }

    public void delete(String sku) {

        Optional<Order> orderSku = orderRepository.findBySku(sku);

            if (orderSku.isPresent()) {
                orderRepository.deleteBySku(sku);
            } else throw new RuntimeException("sku-inválido ou inexistente");
    }

    public Order update(Order order, OrderDto dto) {
        Optional<Product> findBySku = productRepository.findBySku(dto.getSku());
        findBySku.ifPresent(product -> {

            boolean localVar = true;
            while (localVar) {
                int quantityInStock = product.getQuantity();
                int requestedQuantity = dto.getQuantity();
                Double total;
                try {
                    if (quantityInStock < requestedQuantity || requestedQuantity < 0) {
                        throw new RuntimeException("Você está tentando adicionar " + requestedQuantity + "no carrinho, "
                                + "porém há apenas " + quantityInStock + "no estoue");
                    } else {
                        total = requestedQuantity * product.getPrice();
                        localVar = false;
                    }
                } catch (InputMismatchException e) {
                    throw new InputMismatchException(e.getMessage());
                }

                order.setSku(dto.getSku());
                order.setQuantity(requestedQuantity);
                order.setTotal(total);
                order.setDate(LocalDateTime.now());
                order.setCustomerName(dto.getCustomerName());

                if (dto.getCustomerCpf().isEmpty()) {
                    order.setCustomerCpf("00000000000");
                } else {
                    if (CpfValidation.isCPF(dto.getCustomerCpf())) {
                        order.setCustomerCpf(dto.getCustomerCpf());
                    } else {
                        throw new RuntimeException("CPF inválido");
                    }

                }
                orderRepository.save(order);
            }
        });
        return null;
    }
}
