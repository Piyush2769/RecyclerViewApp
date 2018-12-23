package com.example.piyush.recyclerview;

public class Contact {

    int img_id;
    String email,name;

    public Contact(int img_id, String email, String name) {
        this.img_id = img_id;
        this.email = email;
        this.name = name;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
