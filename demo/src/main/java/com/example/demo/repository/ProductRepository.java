package com.example.demo.repository;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long > {
    Optional<Product> findBySku(String sku);
    @Transactional
    @Modifying
    @Query("delete from Product p where p.sku = ?1")
    void deleteBySku(String sku);
}
