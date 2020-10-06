package com.tsofen.agsenceapp.entities;

import androidx.annotation.NonNull;

public class AccountCompany {
    private String name;
    private String id;

    public AccountCompany(){}
    public AccountCompany(String name, String id) {
        this.name = name;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @NonNull
    @Override
    public String toString() {
        return getId()+" - "+getName();
    }
}
