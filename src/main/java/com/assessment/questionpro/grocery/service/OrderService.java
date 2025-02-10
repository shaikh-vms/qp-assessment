package com.assessment.questionpro.grocery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.questionpro.grocery.model.Grocery;
import com.assessment.questionpro.grocery.model.GroceryOrder;
import com.assessment.questionpro.grocery.model.Order;
import com.assessment.questionpro.grocery.repository.GroceryRepository;
import com.assessment.questionpro.grocery.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private GroceryRepository groceryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	public void bookGroceries(List<GroceryOrder> orderLists) {

		for (GroceryOrder groceryOrder : orderLists) {

			Long itemId = groceryOrder.getGroceryItemId();

			 Optional<Grocery> groceryOptional = groceryRepository.findById(itemId);
		        if(groceryOptional.isEmpty())
		        	throw new RuntimeException("Invalid item/ID");
		        Grocery grocery = groceryOptional.get();

			Integer availableStock = grocery.getStock();
			Integer quantityAsked = groceryOrder.getQuantity();

			if (quantityAsked > availableStock) {
				throw new RuntimeException("Item " + itemId + " out of stock");
			} else {
				grocery.setStock(availableStock - quantityAsked);
				groceryRepository.save(grocery);
			}

			Order order = new Order();
			order.setGrocery(grocery);
			order.setQuantity(quantityAsked);

			orderRepository.save(order);
		}

	}

}
