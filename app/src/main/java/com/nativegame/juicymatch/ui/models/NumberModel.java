package com.nativegame.juicymatch.ui.models;

public class NumberModel {

    String number , amount;

    public NumberModel(String number, String amount) {
        this.number = number;
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
