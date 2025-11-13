package com.nativegame.juicymatch.ui.models;

public class UpiModel {

    String ud_id, upi_id, date, time, update_date, update_time, status, amount ;

    public UpiModel(String ud_id, String upi_id, String date, String time, String update_date, String update_time, String status, String amount) {
        this.ud_id = ud_id;
        this.upi_id = upi_id;
        this.date = date;
        this.time = time;
        this.update_date = update_date;
        this.update_time = update_time;
        this.status = status;
        this.amount = amount;
    }

    public String getUd_id() {
        return ud_id;
    }

    public void setUd_id(String ud_id) {
        this.ud_id = ud_id;
    }

    public String getUpi_id() {
        return upi_id;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
