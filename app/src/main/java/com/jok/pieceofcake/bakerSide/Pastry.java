package com.jok.pieceofcake.bakerSide;

import com.google.firebase.firestore.Exclude;

public class Pastry {
    private  String price;
    private String name;
    private String allerganics;
    private String description;
    public String docID;


    public  Pastry(){
        // no args constructor needed
    }

    public Pastry(String price, String name, String allerganics, String description){
        this.price=price;
        this.name=name;
        this.allerganics=allerganics;
        this.description=description;
    }
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getAllerganics() {
        return allerganics;
    }

    public String getDescription() {
        return description;
    }

    public String getDocID() {
        return docID;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAllerganics(String allerganics) {
        this.allerganics = allerganics;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public void setDocID(String docID) {
        this.docID = docID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
