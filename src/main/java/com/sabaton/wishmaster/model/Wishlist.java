package com.sabaton.wishmaster.model;
import java.util.ArrayList;

public class Wishlist {
    private int id;
    private String password;
    private ArrayList<String> items;

    public Wishlist(int id, String password){
        this.id = id;
        this.password = password;
        items = new Arraylist<>();
    }

    public ArrayList<String> getItems() {
        return items;
    }
    public void addItem(String item){
        items.add(item);
    }
    public void removeItem(String item){
        items.remove(item);
    }
}
