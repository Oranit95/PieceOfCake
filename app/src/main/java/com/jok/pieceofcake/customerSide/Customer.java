package com.jok.pieceofcake.customerSide;

import com.jok.pieceofcake.Address;
import com.jok.pieceofcake.User;

public class Customer extends User {
    public Customer(String email, String full_name, String phone, Address address, String role) {
        super(email, full_name, phone, address, role);
    }
    public Customer(){

    }

}
