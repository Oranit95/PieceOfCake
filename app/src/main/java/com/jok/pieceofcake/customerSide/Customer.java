package com.jok.pieceofcake.customerSide;

import com.jok.pieceofcake.Objects.Address;
import com.jok.pieceofcake.Objects.User;

public class Customer extends User {
    public Customer(String email, String full_name, String phone, Address address, String userID) {
        super(email, full_name, phone, address, userID);
    }
    public Customer(){

    }

}
