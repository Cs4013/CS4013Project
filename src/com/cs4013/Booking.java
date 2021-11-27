package com.cs4013;

public class Booking {
    private String userId;
    private String bookingId;
    private long checkInTime;
    private long checkOutTime;
    private String roomId;
    private String hotelId;
    private String price;
    private String keyCard;
    private String checkInDate;
    private String checkOutDate;
    private String roomType;
    private String cancellations;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getBookingId() {
        return bookingId;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public String getCancellations() {
        return cancellations;
    }
    public void setCancellations(String cancellations) {
        this.cancellations = cancellations;
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
    public long getCheckOutTime() {
        return checkOutTime;
    }
    public void setCheckOutTime(long checkOutTime) {
        this.checkOutTime = checkOutTime;
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
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getKeyCard() {
        return keyCard;
    }
    public void setKeyCard(String keyCard) {
        this.keyCard = keyCard;
    }
    public String getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
    public String getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    


}
