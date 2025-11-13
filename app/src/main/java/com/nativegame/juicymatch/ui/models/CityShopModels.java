package com.nativegame.juicymatch.ui.models;

public class CityShopModels {

    String ah_id, id, csc_id, name, photo, status, city,ship_amount;
    int message;

    public CityShopModels(String ah_id, String id, String csc_id, String name, String photo, String status, String city, String ship_amount, int message) {
        this.ah_id = ah_id;
        this.id = id;
        this.csc_id = csc_id;
        this.name = name;
        this.photo = photo;
        this.status = status;
        this.city = city;
        this.ship_amount = ship_amount;
        this.message = message;
    }

    public String getShip_amount() {
        return ship_amount;
    }

    public void setShip_amount(String ship_amount) {
        this.ship_amount = ship_amount;
    }

    public String getAh_id() {
        return ah_id;
    }

    public void setAh_id(String ah_id) {
        this.ah_id = ah_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
