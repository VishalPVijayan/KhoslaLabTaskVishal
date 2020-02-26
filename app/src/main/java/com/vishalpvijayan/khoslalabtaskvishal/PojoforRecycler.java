package com.vishalpvijayan.khoslalabtaskvishal;

public class PojoforRecycler {
    String temperature;
    String Date;
    int imgID;

    public PojoforRecycler() {
    }

    public PojoforRecycler(String temperature, String date, int imgID) {
        this.temperature = temperature;
        Date = date;
        this.imgID = imgID;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
