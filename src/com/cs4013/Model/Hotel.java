package com.cs4013.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Hotel {
    private String hotelId;
    private String ratings;
    private ArrayList<Room> rooms;
    private String name;

    public Hotel() {
        this.hotelId = UUID.randomUUID().toString();
        this.ratings = "3-Star";
        this.rooms = new ArrayList<>();
        this.name = "Unknown";
    }


    public Hotel(String ratings,String name) {
        this.hotelId = UUID.randomUUID().toString();;
        this.ratings = ratings;
        this.rooms = new ArrayList<>();
        this.name = name;

    }

    public Hotel(String ratings, ArrayList<Room> rooms, String name) {
        this.hotelId = UUID.randomUUID().toString();;
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