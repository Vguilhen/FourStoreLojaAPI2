package com.example.demo.repository;

import com.example.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
    Optional<Order> findBySku(String sku);
    @Transactional
    @Modifying
    @Query("delete from Order o where o.sku = ?1")
    void deleteBySku(String sku);
}
