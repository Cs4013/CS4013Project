package com.cs4013.Model;

public class TripleRoom extends Room{
    public TripleRoom(String hotelId){
        super(hotelId);
        this.type = "Triple";
        this.minOccupancy = 1;
        this.maxOccupancy = 3;
    }

    @Override
    public void bookRoom(String userId, long checkInTime, long checkOutTime){
     
    }
    
}