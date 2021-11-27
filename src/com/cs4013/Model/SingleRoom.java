package com.cs4013.Model;

public class SingleRoom extends Room{
    public SingleRoom(String hotelId){
        super(hotelId);
        this.type = "Single";
        this.minOccupancy = 1;
        this.maxOccupancy = 1;
    }

    @Override
    public void bookRoom(String userId, long checkInTime, long checkOutTime){
        
    }

}