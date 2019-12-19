package com.jok.pieceofcake.bakerSide;

import com.jok.pieceofcake.Address;
import com.jok.pieceofcake.User;

public class Baker extends User {

    public Baker(){
        super();
    }
    public Baker(String email, String full_name, String phone, Address address, String role) {
        super(email, full_name, phone, address, role);
    }


}
