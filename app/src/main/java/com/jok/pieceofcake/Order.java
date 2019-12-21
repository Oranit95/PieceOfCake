package com.jok.pieceofcake;

import com.jok.pieceofcake.bakerSide.Baker;
import com.jok.pieceofcake.customerSide.Customer;

public class Order {
    Customer customer;
    Baker baker;
    String date;
    String pastry_name;
    String pastry_id;
    String comments;

    boolean card;
    boolean delivery;

    public Order(Customer customer, Baker baker,
                 String date, String pastry_name,
                 String pastry_id, String comments,
                 boolean card, boolean delivery) {

        this.customer = customer;
        this.baker = baker;
        this.date = date;
        this.pastry_name = pastry_name;
        this.pastry_id = pastry_id;
        this.comments = comments;
        this.card = card;
        this.delivery = delivery;
    }

    public Order(){

    }

    public Customer getCustomer() {
        return customer;
    }

    public Baker getBaker() {
        return baker;
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
