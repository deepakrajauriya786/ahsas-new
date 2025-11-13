package com.nativegame.juicymatch.ui.models;

public class WinnerModel {

    String wd_id , winner_name,game_name,win_time,win_date,time,date,status,type,message;

    public WinnerModel(String wd_id, String winner_name, String game_name, String win_time, String win_date, String time, String date, String status, String type, String message) {
        this.wd_id = wd_id;
        this.winner_name = winner_name;
        this.game_name = game_name;
        this.win_time = win_time;
        this.win_date = win_date;
        this.time = time;
        this.date = date;
        this.status = status;
        this.type = type;
        this.message = message;
    }

    public String getWd_id() {
        return wd_id;
    }

    public void setWd_id(String wd_id) {
        this.wd_id = wd_id;
    }

    public String getWinner_name() {
        return winner_name;
    }

    public void setWinner_name(String winner_name) {
        this.winner_name = winner_name;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getWin_time() {
        return win_time;
    }

    public void setWin_time(String win_time) {
        this.win_time = win_time;
    }

    public String getWin_date() {
        return win_date;
    }

    public void setWin_date(String win_date) {
        this.win_date = win_date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
