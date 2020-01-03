package com.jok.pieceofcake.bakerSide;


import com.google.firebase.database.Exclude;
import com.jok.pieceofcake.Upload;

import java.io.Serializable;
import java.util.ArrayList;

public class Pastry implements Serializable {
    private  String price;
    private String name;
    private String allerganics;
    private String description;
    public String docID;
    ArrayList<Upload> images;
    private String imagesID;

    public  Pastry(){
        // no args constructor needed
    }

    public Pastry(String price, String name, String allerganics, String description){
        this.price=price;
        this.name=name;
        this.allerganics=allerganics;
        this.description=description;
        images = new ArrayList<Upload>();
        imagesID = "";
    }
    public void addImage(Upload upload){
        images.add(upload);
    }
    public ArrayList<Upload> getImages(){
        return images;
    }

    public String getImagesID() {
        return imagesID;
    }

    public void setImagesID(String imagesID) {
        this.imagesID = imagesID;
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
