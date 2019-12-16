package com.jok.pieceofcake;

public class User {
    String email;
    String full_name;
    String phone;
    String address;
    String Role;

    public User(String email, String full_name, String phone, String address, String role) {
        this.email = email;
        this.full_name = full_name;
        this.phone = phone;
        this.address = address;
        Role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
