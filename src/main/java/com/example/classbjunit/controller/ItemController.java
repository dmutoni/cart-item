package com.example.classbjunit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.classbjunit.model.Item;
import com.example.classbjunit.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/all-items")
	public List<Item> getAll(){

		return itemService.getAll();
	}


	@PostMapping("/create")
	private ResponseEntity<?> save(@RequestBody Item newItem){
		Item savedItem = itemService.save(newItem);

		return ResponseEntity.ok(savedItem);

	}

	@DeleteMapping("/{id}")
	private  ResponseEntity<?> delete(@RequestParam int id)throws Exception{
		Item deletedItem = itemService.delete(id);

		return ResponseEntity.ok("Item is deleted!");
	}
}
