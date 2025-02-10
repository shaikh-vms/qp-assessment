package com.assessment.questionpro.grocery.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.questionpro.grocery.controller.AdminController;
import com.assessment.questionpro.grocery.model.Grocery;
import com.assessment.questionpro.grocery.service.GroceryService;

@RestController
@RequestMapping("/admin/grocery")
public class AdminControllerImpl implements AdminController {

    @Autowired
    private GroceryService groceryService;
    
    @GetMapping("/test")
    public String test() {
        return "test success";
    }
    
    @PostMapping("/add")
    @Override
    public ResponseEntity<Object> addGroceryItems(@RequestBody List<Grocery> groceryItemList) {
    	
    	for (Grocery grocery : groceryItemList) {
    		groceryService.addGroceryItem(grocery);
		}
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body("Request Processed...!");
    }

    @GetMapping("/view")
    @Override
    public List<Grocery> viewGroceryItems() {
        return groceryService.viewGroceryItems();
    }

    @DeleteMapping("/remove/{id}")
    @Override
    public void removeGroceryItem(@PathVariable Long id) {
    	groceryService.removeGroceryItem(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public Grocery updateGroceryItem(@PathVariable Long id, @RequestBody Grocery grocery) {
        return groceryService.updateGroceryItem(id, grocery);
    }

    @PutMapping("/updateStock/{id}")
    @Override
    public Grocery updateStock(@PathVariable Long id, @RequestParam Integer stock) {
        return groceryService.updateStock(id, stock);
    }

    
}
