package com.jok.pieceofcake;
public class PastryItem {

    private String name;
    private String price;

    public PastryItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}