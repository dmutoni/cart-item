package com.example.classbjunit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classbjunit.model.Item;
import com.example.classbjunit.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;


	public List<Item> getAll() {
		
		List<Item> items = itemRepository.findAll();
		
		for(Item item:items) {
			item.setValue(item.getQuantity()*item.getPrice());
		}
		
		return items;
	}

	public Item save(Item newItem) {
		return itemRepository.save(newItem);
	}

	public Item update(int id, Item updateItem) throws Exception {
		// testing if the model with this id exists
		Optional<Item> itemOptional = itemRepository.findById(id);

		if (!itemOptional.isPresent()) throw new Exception("course is not found");


		updateItem.setId(id);

		return itemRepository.save(updateItem);

	}
	public Item delete(int id) throws  Exception{

		Optional<Item> itemOptional = itemRepository.findById(id);

		if(! itemOptional.isPresent()) throw new Exception("course is not found");

		itemRepository.deleteById(id);

		return itemOptional.get();
	}

	}
