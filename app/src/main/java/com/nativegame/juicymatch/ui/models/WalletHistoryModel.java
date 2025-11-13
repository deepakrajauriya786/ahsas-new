package com.nativegame.juicymatch.ui.models;

public class WalletHistoryModel {

    String sawh_id,u_id,date,time,amount,b_bal,a_bal,note,duration;
    int message,type;

    public WalletHistoryModel(String duration,String sawh_id, String u_id, int type, String date, String time, String amount, String b_bal, String a_bal, String note, int message) {
        this.duration = duration;
        this.sawh_id = sawh_id;
        this.u_id = u_id;
        this.type = type;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.b_bal = b_bal;
        this.a_bal = a_bal;
        this.note = note;
        this.message = message;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSawh_id() {
        return sawh_id;
    }

    public void setSawh_id(String sawh_id) {
        this.sawh_id = sawh_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getB_bal() {
        return b_bal;
    }

    public void setB_bal(String b_bal) {
        this.b_bal = b_bal;
    }

    public String getA_bal() {
        return a_bal;
    }

    public void setA_bal(String a_bal) {
        this.a_bal = a_bal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
