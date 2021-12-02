package com.cs4013.Model;

import com.cs4013.Misc.FileManager;

import java.io.IOException;
import java.util.*;

public abstract class Room{
    protected String roomId;
    protected String hotelId;
    protected String type;
    protected Rates rate;
    protected int minOccupancy;
    protected int maxOccupancy;
    protected ArrayList<String> bookings;

    public Room(String hotelId){

        this.roomId = UUID.randomUUID().toString();
        this.type = "";
        this.rate = new Rates();
        this.hotelId = hotelId;
        this.bookings = new ArrayList<>();
        this.minOccupancy = 1;
        this.maxOccupancy = 4;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setMinOccupancy(int minOccupancy) {
        this.minOccupancy = minOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public ArrayList<String> getBookings(){
        return bookings;
    }

    public void setBookings(ArrayList<String> bookings){
        this.bookings = bookings;
    }

    public abstract void bookRoom(String userId, long checkInTime, long checkOutTime);



    public void addRoom()throws IOException{

        FileManager fm = new FileManager("rooms.csv");
        //fm.write(this.toString());
    }

    public String toString(){
        String bks="";
        for (String b : bookings){
            bks+=b+"_";
        }
        String B = "";
        if(!bks.equals("")){
            B = bks.substring(0,bks.length()-1);
        }
        return roomId+","+hotelId+","+type+","+rate.toString("_")+","+minOccupancy+","+maxOccupancy+","+B;
    }


}


