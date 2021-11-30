package com.cs4013.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import com.cs4013.Misc.FileParser;
import com.cs4013.Misc.Formats;
import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Model.Booking;
import com.cs4013.Model.Room;

public class BookingManager {
    public BookingManager (){

    }
    /**
     * 
     * @return
     */
    public void searchRoom (){

    
   
       boolean success = false;
        

        //get check in date and check out date from user//
        //make sure that both check in date and check out dates are valid in the format dd||mm||yyyy //
        String input = "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date checkinDate=null,checkoutDate=null,today=null;
        try {
             checkinDate = format.parse(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
             checkoutDate = format.parse(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
             today = format.parse(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
            
        } catch (ParseException e) {

        }
       
    while (success == false) {

        input = TerminalLogger.textfield("Enter Check In date in format dd/mm/yyyy", 50);
        if (Formats.isValidDate(input)){
            
            String [] temp = input.split("/");
            int d = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            int y = Integer.parseInt(temp[2]);
            try {
                checkinDate = format.parse(input);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (checkinDate.after(today)||checkinDate.equals(today)){
                success = true;
            }else{
                TerminalLogger.logError("Please Enter a date past or equal to "+format.format(today));
            }
            

        } else {
            TerminalLogger.logError("Invalid date format");
        }

    }
    success = false;
    while (success == false) {

        input = TerminalLogger.textfield("Enter Check Out date in format dd/mm/yyyy", 50);
        if (Formats.isValidDate(input)){
            String [] temp = input.split("/");
            int d = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            int y = Integer.parseInt(temp[2]);
          
            try {
                checkoutDate = format.parse(input);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (checkoutDate.after(checkinDate)){
                success = true;
            }
            

        } else {
            TerminalLogger.logError("Invalid date format");
        }

    }
    //we firstly search for all the rooms in the hotels//

    //we then search for the rooms between the check in and check out date//
    //we specify if the rooms are booked or not//
    //we display all the rooms//

    FileParser parser = new FileParser();
    ArrayList<Booking>bookings = parser.getReservation();
    ArrayList<Room>rooms = parser.getRooms();
    ArrayList<Room>availableRooms = new ArrayList<>();

    Map<String, long[]> bookingMap = new HashMap<>();
    for (Booking b: bookings) {
        long[] res=new long[2];
        res[0]= b.getCheckInTime();
        res[1]= b.getCheckOutDate();
        bookingMap.put(b.getBookingId(), res );

    }
    System.out.println(bookingMap);
    System.out.println(rooms);

    for (Room r: rooms){
        System.out.println(r);
        for(String l: r.getBookings()){
            System.out.println(l);
            System.out.println("Ollie entered checkin: "+format.format(checkinDate.getTime()));
            System.out.println("Ollie entered checkout: "+format.format(checkoutDate.getTime()));
            System.out.println("booking checkin: "+format.format(bookingMap.get(l)[0]));
            System.out.println("booking checkout: "+format.format(bookingMap.get(l)[1]));


          if (checkAvailability(checkinDate.getTime(), 
          checkoutDate.getTime(), 
          bookingMap.get(l)[0],
          bookingMap.get(l)[1]))  {
              availableRooms.add(r);
              
          }
          else break;
                
        }
 

    }

    System.out.println(availableRooms);
    }

    public boolean checkAvailability(long uCheckIn, long uCheckOut, long bCheckIn, long bCheckOut){
        if (uCheckOut < bCheckIn || uCheckIn > bCheckOut){
            return true;
            
        }
        else{
            return false;
        }

    }
}
