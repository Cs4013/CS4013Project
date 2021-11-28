package com.cs4013.Model;

import java.util.ArrayList;

public class Hotel {
    private String hotelId;
    private String ratings;
    private ArrayList<Room> rooms;
    private String name;

    public Hotel() {
        this.hotelId = "";
        this.ratings = "3-Star";
        this.rooms = new ArrayList<>();
        this.name = "Unknown";
    }

    public Hotel(String hotelId, String ratings, ArrayList<Room> rooms, String name) {
        this.hotelId = hotelId;
        this.ratings = ratings;
        this.rooms = rooms;
        this.name = name;

    }

    public String getHotelId() {
        return this.hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRatings() {
        return this.ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

}