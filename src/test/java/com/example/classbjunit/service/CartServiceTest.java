package com.example.classbjunit.service;

import com.example.classbjunit.model.Cart;
import com.example.classbjunit.model.Item;
import com.example.classbjunit.repository.CartRepository;
import com.example.classbjunit.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepositoryMock;

    @InjectMocks
    private CartService cartService;

    @Test
    public void getAllWithSomeElements() {

        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        Item item1 = new Item(1,"Banana",1,10);
        Item item2 = new Item(2,"Potato",1,30);
        itemArrayList.add(item1);
        itemArrayList.add(item2);
        when(cartRepositoryMock.findAll()).thenReturn(Arrays.asList(new Cart(1,itemArrayList ,40)));
        assertEquals(40,cartService.getAll().get(0).getTotalPrice());
    }


    @Test
    public  void createCart(){
        ArrayList<Item> createdCartItems = new ArrayList<Item>();
        Item item4 = new Item(1,"Samuel",1,100);
        Item item5 = new Item(2,"Sam",1,30);
        createdCartItems.add(item4);
        createdCartItems.add(item5);
        when(cartRepositoryMock.save(ArgumentMatchers.any(Cart.class))).thenReturn(new Cart(1,createdCartItems ,130));
        assertEquals(130,cartService.save(new Cart(1,createdCartItems ,130)).getTotalPrice());
    }

    @Test
    public void deleteCart() throws Exception {
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        Item item1 = new Item(1,"Banana",1,100);
        Item item2 = new Item(2,"Potato",1,30);
        itemArrayList.add(item1);
        itemArrayList.add(item2);
        Cart cart= new Cart(1,itemArrayList ,130);
        when(cartRepositoryMock.findById(cart.getId())).thenReturn(Optional.of(cart));

        cartService.delete(cart.getId());

        verify(cartRepositoryMock).deleteById(cart.getId());
    }

    @Test
    public void updateCart() throws Exception {
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        Item item1 = new Item(1,"Banana",1,100);
        Item item2 = new Item(2,"Potato",1,30);
        itemArrayList.add(item1);
        itemArrayList.add(item2);

        ArrayList<Item> newCartItemList = new ArrayList<Item>();
        Item item3 = new Item(1,"Banana",1,200);
        Item item4 = new Item(2,"Potato",1,40);
        itemArrayList.add(item1);
        itemArrayList.add(item2);

        Cart cart = new Cart(1,itemArrayList ,130);
        Cart updatedCart = new Cart(1,newCartItemList,240);
        given(cartRepositoryMock.findById(cart.getId())).willReturn(Optional.of(cart));

        cartService.update(cart.getId(),updatedCart);
        verify(cartRepositoryMock).save(updatedCart);
        verify(cartRepositoryMock).findById(cart.getId());
    }
}
