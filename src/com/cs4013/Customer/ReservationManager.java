package com.cs4013.Customer;

import com.cs4013.Misc.FileManager;
import com.cs4013.Misc.FileParser;
import com.cs4013.Model.Booking;
import com.cs4013.Model.Hotel;

import java.io.IOException;
import java.util.ArrayList;

public class ReservationManager  {
    public ReservationManager (){
        
    }
    public void update(Booking booking){
        ArrayList<Booking> bookings = new FileParser().getReservation(true);
        try {
            new FileManager("bookings.csv").overwrite("");
            for(Booking b : bookings){

                if(booking.getBookingId().equals(b.getBookingId())){
                    b.setCheckedIn(booking.isCheckedIn());
                    b.setCheckInTime(booking.getCheckInTime());
                    b.setCheckOutDate(booking.getCheckInTime());
                    b.setTotalCost(booking.getTotalCost());
                    b.setBookingType(booking.getBookingType());
                    b.setApproved(booking.isApproved());
                    b.setHotelId(booking.getHotelId());
                    b.setUserId(booking.getUserId());
                    b.setBookingId(booking.getBookingId());
                    b.setRoomId(booking.getRoomId());


                }
                new FileManager("hotels.csv").write(b.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addReservation (){

    }

    public void removeReservation (){

    }
    
    public void editReservation (){

    }
    
    public void changeReservation (){

    }
}
