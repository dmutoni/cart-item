package com.example.classbjunit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.classbjunit.model.Item;
import com.example.classbjunit.repository.ItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {


	@Mock
	private ItemRepository itemRepositoryMock;
	
	@InjectMocks
	private ItemService itemService;
	
	@Test
	public void getAllWithSomeElements() {

		when(itemRepositoryMock.findAll()).thenReturn(Arrays.asList(new Item(1,"Potato",1,10),
				new Item(2,"Banana",4,100)));
		assertEquals(10,itemService.getAll().get(0).getValue());
	}


	@Test
	public  void createItem(){
		when(itemRepositoryMock.save(ArgumentMatchers.any(Item.class))).thenReturn(new Item(1,"Samuel",1,10));
		assertEquals("Samuel",itemService.save(new Item(1,"Samuel",1,10)).getName());
	}

	@Test
	public void deleteStudent() throws Exception {
		Item item = new Item(1,"Mango",1,10);
		when(itemRepositoryMock.findById(item.getId())).thenReturn(Optional.of(item));

		itemService.delete(item.getId());

		verify(itemRepositoryMock).deleteById(item.getId());
	}

	@Test
	public void updateStudent() throws Exception {
		Item item = new Item(1,"Orange",1,10);
		Item updatedItem = new Item(1,"Orange",1,500);
		given(itemRepositoryMock.findById(item.getId())).willReturn(Optional.of(item));

		itemService.update(item.getId(),updatedItem);
		verify(itemRepositoryMock).save(updatedItem);
		verify(itemRepositoryMock).findById(item.getId());
	}


}
