package com.assessment.questionpro.grocery.service;

import java.util.List;
import java.util.Optional;

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
        Optional<Grocery> groceryOptional = groceryRepository.findById(id);
        if(groceryOptional.isEmpty())
        	throw new RuntimeException("Invalid item/ID");
        Grocery grocery = groceryOptional.get();
        
        grocery.setStock(stock);
        return groceryRepository.save(grocery);
    }
    
}
