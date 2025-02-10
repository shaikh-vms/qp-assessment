package com.assessment.questionpro.grocery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.assessment.questionpro.grocery.model.Grocery;

public interface AdminController {

	    ResponseEntity<Object> addGroceryItems(List<Grocery> grocery);

	    List<Grocery> viewGroceryItems();

	    void removeGroceryItem( Long id);

	    Grocery updateGroceryItem(Long id, Grocery grocery);

	    Grocery updateStock(Long id, Integer stock);
	    
}
