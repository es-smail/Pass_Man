package com.example.newproject.methods;

import java.sql.Timestamp;

public class UserLogin extends item{
   private int id;
    private String title;
    private String email;
    private String password;

    public UserLogin(int id,String title,String email, String password,Timestamp date_l){
        super(date_l);
        this.id=id;
        this.title=title;
        this.email=email;
        this.password=password;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
