package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{sku}")
    public Product findBySku(@PathVariable(value = "sku") String sku) {
        Product productSku = productService.findBySku(sku);
        return productService.findBySku(sku);
    }

    @GetMapping("/product")
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody ProductDto dto) {
        return productService.saveProduct(dto);
    }

    @DeleteMapping("/product/{sku}")
    public void delete(@PathVariable String sku) {
        productService.delete(sku);
    }

    @PutMapping("/product/{sku}")
    public Product update(@RequestBody ProductDto dto, @PathVariable String sku) {
    Product product = productService.findBySku(sku);
    return productService.update(product, dto);
    }
}
