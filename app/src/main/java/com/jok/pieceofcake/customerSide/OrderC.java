package com.jok.pieceofcake.customerSide;

public class OrderC {
    String C_id;
    String numOfOrder;
    String B_email;
    String City;
    String date;
    String Pastry_name;
    String Pastry_id;
    String pay;

    public OrderC(String C_id,String numOfOrder, String b_email, String city, String date, String pastry_name,String pastry_id, String pay) {
        this.C_id = C_id;
        this.numOfOrder = numOfOrder;
        this.B_email = b_email;
        this.City = city;
        this.date = date;
        this.Pastry_name = pastry_name;
        this.Pastry_id = pastry_id;
        this.pay = pay;
    }

    public String getC_id() {
        return C_id;
    }

    public String getNumOfOrder() {
        return numOfOrder;
    }

    public void setNumOfOrder(String numOfOrder) {
        this.numOfOrder = numOfOrder;
    }

    public String getB_email() {
        return B_email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getPastry_name() {
        return Pastry_name;
    }

    public void setPastry_name(String pastry_name) {
        Pastry_name = pastry_name;
    }

    public String getPastry_id() {
        return Pastry_id;
    }

    public void setPastry_id(String pastry_id) {
        Pastry_id = pastry_id;
    }

}
