package com.cs4013.Model;

public class QuadRoom extends Room{
    public QuadRoom(String hotelId){
        super(hotelId);
        this.type = "Quad";
        this.minOccupancy = 1;
        this.maxOccupancy = 4;
    }

    @Override
    public void bookRoom(String userId, long checkInTime, long checkOutTime){
      
    }
    
}