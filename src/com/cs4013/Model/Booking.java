package com.cs4013.Model;


import java.util.ArrayList;
import java.util.UUID;

public class Booking {
    private String userId;
    private String bookingId;
    private long checkInTime;
    private long checkOutDate;
    private ArrayList<String> roomId;
    private String hotelId;
    private String bookingType;
    private int totalCost;

    public Booking() {
    }

    public Booking( long checkInTime, long checkOutDate) {
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.roomId = new ArrayList<>();
        this.userId = "";
        this.hotelId = "";
        this.bookingType = "S";
        this.totalCost = 0;
    }

    public Booking(long checkInTime, long checkOutDate, String userId,
                   String hotelId) {
        this.userId = userId;
        this.bookingId = UUID.randomUUID().toString();
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.roomId = new ArrayList<>();
        this.hotelId = hotelId;
        this.bookingType = "S";
        this.totalCost = 0;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public long getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(long checkInTime) {
        this.checkInTime = checkInTime;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public ArrayList<String> getRoomId() {
        return roomId;
    }

    public void setRoomId(ArrayList<String> roomId) {
        this.roomId = roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String toString(){
        String bk = "";
        for(String s : roomId){
            bk+=s+"_";
        }
        if(roomId.size() > 0){
            bk = bk.substring(0,roomId.size()-1);
        }
        return bookingId+","+userId+","+bk+","+hotelId+","+checkInTime+","+checkOutDate+","+bookingType+","+totalCost;
    }


}

