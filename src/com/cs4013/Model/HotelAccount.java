package com.cs4013.Model;

public class HotelAccount {
    private String userId;
    private int amountPayed;
    private long datePayed;
    private String hotelId;

    private void HotelAccount(){
        this.userId = "";
        this.amountPayed = 0;
        this.datePayed = System.currentTimeMillis();
        this.hotelId = "";

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    

    public int getAmountPayed() {
        return amountPayed;
    }

    public long getDatePayed() {
        return datePayed;
    }

    public void setDatePayed(long datePayed) {
        this.datePayed = datePayed;
    }

    public void setAmountPayed(int amountPayed) {
        this.amountPayed = amountPayed;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
   
    @Override
    public String toString() {
        
        return hotelId+","+userId+","+amountPayed+","+datePayed+",";
    }
    public String toString(String sep) {
        
        return hotelId+sep+userId+sep+amountPayed+sep+datePayed+sep;
    }
    
    
}
