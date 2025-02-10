package com.assessment.questionpro.grocery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.questionpro.grocery.model.Grocery;
import com.assessment.questionpro.grocery.repository.GroceryRepository;

@Service
public class GroceryService {

	@Autowired
    private GroceryRepository groceryRepository;
    
    public void addGroceryItem(Grocery grocery) {
        groceryRepository.save(grocery);
        return;
    }

    public List<Grocery> viewGroceryItems() {
        return groceryRepository.findAll();
    }

    public List<Grocery> viewAvailableItems() {
        return groceryRepository.findByStockGreaterThan(0);
    }
    
    public void removeGroceryItem(Long id) {
        groceryRepository.deleteById(id);
    }

    public Grocery updateGroceryItem(Long id, Grocery grocery) {
        grocery.setId(id);
        return groceryRepository.save(grocery);
    }

    public Grocery updateStock(Long id, Integer stock) {
        Grocery grocery = groceryRepository.findById(id).orElseThrow();
        grocery.setStock(stock);
        return groceryRepository.save(grocery);
    }
    
}
