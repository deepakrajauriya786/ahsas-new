package com.nativegame.juicymatch.ui.models;

public class SliderModels {
    String b_id, place_id, img, city, india, link_type, status,url;
    int message;

    public SliderModels(String b_id, String place_id, String img, String city, String india, String link_type, String status, int message) {
        this.b_id = b_id;
        this.place_id = place_id;
        this.img = img;
        this.city = city;
        this.india = india;
        this.link_type = link_type;
        this.status = status;
        this.message = message;
    }

    public String getLink_type() {
        return link_type;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndia() {
        return india;
    }

    public void setIndia(String india) {
        this.india = india;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
