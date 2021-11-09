package com.example.classbjunit.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Cart {
    @Id
    private int id;
    @ManyToMany
    private ArrayList<Item> items;

    private int TotalPrice;


    public Cart(int id, ArrayList<Item> items, int totalPrice) {
        this.id = id;
        this.items = items;
        this.TotalPrice = totalPrice;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }


}
