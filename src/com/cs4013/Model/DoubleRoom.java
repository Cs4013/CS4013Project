package com.cs4013.Model;

public class DoubleRoom extends Room{
    public DoubleRoom(String hotelId){
        super(hotelId);
        this.type = "Double";
        this.minOccupancy = 1;
        this.maxOccupancy = 2;
    }

    @Override
    public void bookRoom(String userId, long checkInTime, long checkOutTime){
   
    }
    
}