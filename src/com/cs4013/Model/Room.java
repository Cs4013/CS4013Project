package com.cs4013.Model;

import java.io.IOException;
import java.util.*;

public abstract class Room{
    protected String roomId;
    protected String hotelId;
    protected String type;
    protected Rates rate;
    protected int minOccupancy;
    protected int maxOccupancy;
    protected ArrayList<Booking> bookings;

    public Room(String hotedId){
        this.roomId = UUID.randomUUID().toString();
        this.type = "";
        this.rate = new Rates();
        this.hotelId = hotelId;
        this.bookings = new ArrayList<>();
        this.minOccupancy = 1;
        this.maxOccupancy = 4;
    }

    public String getRoomId(){
        return roomId;
    }

    public String getHotelId(){
        return hotelId;
    }

    public void setHotelId(String hotelId){
        this.hotelId = hotelId;
    }

    public String getType(){
        return type;
    }

    public Rates getRate(){
        return rate;
    }

    public void setRate(Rates rate){
        this.rate = rate;
    }

    public int getMinOccupancy(){
        return minOccupancy;
    }

    public int getMaxOccupancy(){
        return maxOccupancy;
    }

    public ArrayList<Booking> getBookings(){
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings){
        this.bookings = bookings;
    }
}


