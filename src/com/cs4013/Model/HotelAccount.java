package com.cs4013.Model;

public class HotelAccount {
    private String userId;
    private int amount;
    private long amountPayed;
    private String hotelId;

    private void HotelAccount(){
        
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(long amountPayed) {
        this.amountPayed = amountPayed;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    
    
}
