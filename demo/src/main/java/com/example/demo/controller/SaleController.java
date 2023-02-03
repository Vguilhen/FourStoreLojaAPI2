package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.SaleDto;
import com.example.demo.models.Order;
import com.example.demo.models.Sale;
import com.example.demo.repository.SaleRepository;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/api")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleService saleService;

    @GetMapping("/sale")
    public List<Sale> saleList() {
        return saleRepository.findAll();
    }

    @PostMapping("/sale")
    public Sale purchase(@RequestBody SaleDto dto) {
        return saleService.purchase(dto);
    }
}
