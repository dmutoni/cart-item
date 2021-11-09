package com.example.classbjunit.controller;

import com.example.classbjunit.model.Cart;
import com.example.classbjunit.model.Item;
import com.example.classbjunit.service.CartService;
import com.example.classbjunit.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/all-items")
    public List<Cart> getAll(){

        return cartService.getAll();
    }

//
//    @PostMapping("/create")
//    private ResponseEntity<?> save(@RequestBody Cart newCart){
//        Cart savedCart = cartService.save(newCart);
//
//        return ResponseEntity.ok(savedCart);
//
//    }

    @DeleteMapping("/{id}")
    private  ResponseEntity<?> delete(@RequestParam int id)throws Exception{
        Cart deleteCart = cartService.delete(id);

        return ResponseEntity.ok("cart is deleted!");
    }
}
