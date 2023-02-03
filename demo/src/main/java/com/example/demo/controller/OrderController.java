package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{sku}")
    public Order findBySku(@PathVariable(value = "sku") String sku) {
        Order orderSku = orderService.findBySku(sku);
        return orderService.findBySku(sku);
    }

    @GetMapping("/order")
    public List<Order> orderList() {
        return orderRepository.findAll();
    }

    @PostMapping("/order")
    public Order saveOrder(@RequestBody OrderDto dto) {
        return orderService.saveOrder(dto);
    }

    @DeleteMapping("/order/{sku}")
    public void delete(@PathVariable String sku) {
        orderService.delete(sku);
    }

    @PutMapping("/order/{sku}")
    public Order update(@RequestBody OrderDto dto, @PathVariable String sku) {
        Order order = orderService.findBySku(sku);
        return orderService.update(order, dto);
    }
}
