package com.assessment.questionpro.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.questionpro.grocery.model.Grocery;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {
	
	List<Grocery> findByStockGreaterThan(int stock);
}
