package com.tsofen.agsenceapp.entities;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(int id, String userName, String email) {
        super(id, userName, email);
    }
}
