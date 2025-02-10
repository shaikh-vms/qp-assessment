package com.assessment.questionpro.grocery.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.questionpro.grocery.controller.UserController;
import com.assessment.questionpro.grocery.model.Grocery;
import com.assessment.questionpro.grocery.model.GroceryOrder;
import com.assessment.questionpro.grocery.service.GroceryService;
import com.assessment.questionpro.grocery.service.OrderService;

@RestController
@RequestMapping("/groceries")
public class UserControllerImpl implements UserController {

	@Autowired
	private GroceryService groceryService;
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Object> bookGroceries(@RequestBody List<GroceryOrder> orderLists) {
		orderService.bookGroceries(orderLists);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order Placed...!");
	}

	@GetMapping
	@Override
	public List<Grocery> viewAvailableItems() {
		return groceryService.viewAvailableItems();
	}

}
