package com.sabaton.wishmaster.model;
import java.util.ArrayList;

public class Wishlist {
    private int id;
    private String password;
    private ArrayList<Item> items;

    public Wishlist(int id, String password){
        this.id = id;
        this.password = password;
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }
}
