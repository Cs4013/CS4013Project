package com.cs4013.Model;

public class ThripleRoom extends Room{
    public ThripleRoom(String hotelId){
        super(hotelId);
        this.type = "Thriple";
        this.minOccupancy = 1;
        this.maxOccupancy = 3;
    }

    @Override
    public void bookRoom(String userId, long checkInTime, long checkOutTime){
     
    }
    
}