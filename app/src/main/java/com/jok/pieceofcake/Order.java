package com.jok.pieceofcake;

public class Order {
    String b_id;
    String c_id;
    String b_email;
    String c_email;
    String numOfOrder;

    Address addressBaker;
    Address addressCustomer;

    String date;
    String pastry_name;
    String pastry_id;
    String comments;

    boolean card;
    boolean delivery;

    public Order(String b_id, String c_id,
                 String b_email, String c_email,
                 String numOfOrder, String comments,
                 Address addressBaker,
                 Address addressCustomer,
                 String date,
                 String pastry_name, String pastry_id,
                 boolean card, boolean delivery) {
        this.b_id = b_id;
        this.c_id = c_id;
        this.b_email = b_email;
        this.c_email = c_email;
        this.numOfOrder = numOfOrder;
        this.addressBaker = addressBaker;
        this.addressCustomer = addressCustomer;
        this.date = date;
        this.pastry_name = pastry_name;
        this.pastry_id = pastry_id;
        this.card = card;
        this.delivery = delivery;
        this.comments = comments;
    }

    public String getB_id() {
        return b_id;
    }

    public Address getAddressBaker() {
        return addressBaker;
    }

    public void setAddressBaker(Address addressBaker) {
        this.addressBaker = addressBaker;
    }

    public Address getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(Address addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }


    public String getC_id() {
        return c_id;
    }

    public String getB_email() {
        return b_email;
    }

    public String getC_email() {
        return c_email;
    }

    public String getNumOfOrder() {
        return numOfOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPastry_name() {
        return pastry_name;
    }

    public void setPastry_name(String pastry_name) {
        this.pastry_name = pastry_name;
    }

    public String getPastry_id() {
        return pastry_id;
    }
}
