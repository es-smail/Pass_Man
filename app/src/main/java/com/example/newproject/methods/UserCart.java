package com.example.newproject.methods;

import java.sql.Timestamp;

public class UserCart extends item{
    private int id;
    private String name;
    private String type;
    private int number;
    private int cvc;
    private int date;
    private int pin;
    private int expiry_date;


    public UserCart(int id, String name, String type, int number, int cvc, int date, int pin,Timestamp date_c,int expiry_date ) {
        super(date_c);
        this.id = id;
        this.name = name;
        this.type = type;
        this.number = number;
        this.cvc = cvc;
        this.date = date;
        this.pin = pin;
        this.expiry_date = expiry_date;
    }


    public int getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(int expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
