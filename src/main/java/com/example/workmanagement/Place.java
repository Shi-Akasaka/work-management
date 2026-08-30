package com.example.workmanagement;

public class Place {

    private int placeId;

    private String place;

    private String date;

    private String name;

    private String worker;

    private String remarks;


    public Place() {
    }


    public Place(
            int placeId,
            String place,
            String date,
            String name,
            String worker,
            String remarks) {

        this.placeId = placeId;
        this.place = place;
        this.date = date;
        this.name = name;
        this.worker = worker;
        this.remarks = remarks;
    }


    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}