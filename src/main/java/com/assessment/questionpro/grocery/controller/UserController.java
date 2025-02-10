package com.assessment.questionpro.grocery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.assessment.questionpro.grocery.model.GroceryOrder;
import com.assessment.questionpro.grocery.model.Grocery;

public interface UserController {

	ResponseEntity<Object> bookGroceries(List<GroceryOrder> orderLists);

    List<Grocery> viewAvailableItems();
}