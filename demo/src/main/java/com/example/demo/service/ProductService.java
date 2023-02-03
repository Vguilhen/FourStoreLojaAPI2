package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.SkuNotFoundException;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findBySku(String sku) {
        Optional<Product> productSku = productRepository.findBySku(sku);

        if (productSku.isPresent()) {
            return productSku.get();
        } else throw new SkuNotFoundException("Sku inválido! " + "SKU digitado:" + sku);
    }

    public Product saveProduct(ProductDto dto) {

        while (true)
            if (productRepository.findBySku(dto.getSku()).isPresent()) {
                throw new RuntimeException("sku-Já existe tente um novo sku");
            } else {
                Product product = new Product();

                product.setSku(dto.getSku());
                product.setQuantity(dto.getQuantity());
                product.setDescription(dto.getDescription());
                product.setPrice(dto.getPrice());
                product.parseSku();

                productRepository.save(product);
                break;
            }
        return null;
    }

    public void delete(String sku) {

        Optional<Product> productSku = productRepository.findBySku(sku);

        if (productSku.isPresent()) {
            productRepository.deleteBySku(sku);
        }
        throw new RuntimeException("sku-inválido ou inexistente");
    }

    public Product update(Product product, ProductDto dto) {

        product.setSku(dto.getSku());
        product.setQuantity(dto.getQuantity());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.parseSku();

        return productRepository.save(product);
    }
}
