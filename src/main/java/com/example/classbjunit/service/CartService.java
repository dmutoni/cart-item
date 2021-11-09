package com.example.classbjunit.service;

import com.example.classbjunit.model.Cart;
import com.example.classbjunit.model.Item;
import com.example.classbjunit.repository.CartRepository;
import com.example.classbjunit.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;


    public List<Cart> getAll() {

        List<Cart> carts = cartRepository.findAll();

//        for(Cart cart:carts) {
//            cart.setTotalPrice(cart.getQuantity()*item.getPrice());
//        }

        for(Cart cart:carts) {
            for(Item item: cart.getItems()) {
//                item.setValue(item.getQuantity()*item.getPrice());
                int cartTotalprice =0;
                cartTotalprice +=item.getPrice() ;
                cart.setTotalPrice(cartTotalprice);
            }
        }

        return carts;
    }

    public Cart save(Cart newCart) {
        return cartRepository.save(newCart);
    }
    public  Cart findById(Integer Id){
        return cartRepository.getById(Id);
    }
    public Cart update(int id, Cart updateCart) throws Exception {
        // testing if the model with this id exists
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (!cartOptional.isPresent()) throw new Exception("course is not found");


        updateCart.setId(id);

        return cartRepository.save(updateCart);

    }
    public Cart delete(int id) throws  Exception{

        Optional<Cart> cartOptional = cartRepository.findById(id);

        if(! cartOptional.isPresent()) throw new Exception("course is not found");

        cartRepository.deleteById(id);

        return cartOptional.get();
    }
}
