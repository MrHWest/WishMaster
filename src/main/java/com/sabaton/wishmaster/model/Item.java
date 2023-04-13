package com.sabaton.wishmaster.model;

public class Item {
    private int id;
    private String title;
    private String link;

    public Item(int id, String title, String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return this.link;
    }
}
