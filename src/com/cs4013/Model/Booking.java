package com.cs4013.Model;


import java.util.UUID;

public class Booking {
    private String userId;
    private String bookingId;
    private long checkInTime;
    private long checkOutDate;
    private String roomId;
    private String hotelId;

    public Booking() {
    }

    public Booking( String roomId, long checkInTime, long checkOutDate) {
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.roomId = roomId;
        this.userId = "";
        this.hotelId = "";
    }

    public Booking(long checkInTime, long checkOutDate, String roomId,String userId,
                   String hotelId) {
        this.userId = userId;
        this.bookingId = UUID.randomUUID().toString();
        this.checkInTime = checkInTime;
        this.checkOutDate = checkOutDate;
        this.roomId = roomId;
        this.hotelId = hotelId;
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String toString(){
        return bookingId+","+userId+","+roomId+","+hotelId+","+checkInTime+","+checkOutDate;
    }


}

