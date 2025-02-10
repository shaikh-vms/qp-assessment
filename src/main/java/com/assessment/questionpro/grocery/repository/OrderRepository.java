package com.assessment.questionpro.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.questionpro.grocery.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
