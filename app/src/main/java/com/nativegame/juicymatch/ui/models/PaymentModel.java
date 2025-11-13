package com.nativegame.juicymatch.ui.models;

public class PaymentModel {

    String message,payment_url,msg;

    public PaymentModel(String message, String payment_url, String msg) {
        this.message = message;
        this.payment_url = payment_url;
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayment_url() {
        return payment_url;
    }

    public void setPayment_url(String payment_url) {
        this.payment_url = payment_url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
