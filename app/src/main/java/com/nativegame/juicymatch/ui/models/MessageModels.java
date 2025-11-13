package com.nativegame.juicymatch.ui.models;

public class MessageModels {
    String message,u_id, amount ,sender_id;

    public MessageModels(String message, String u_id, String amount ,String sender_id) {
        this.message = message;
        this.u_id = u_id;
        this.amount = amount;
        this.sender_id = sender_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
}
