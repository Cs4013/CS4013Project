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
        Calendar c = Calendar.getInstance();
        c.setTime(checkinDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate cidDate = LocalDate.parse(format2.format(checkinDate));
        LocalDate codDate = LocalDate.parse(format2.format(checkoutDate));
        Map <Integer , String> dMap = new HashMap<>();
        dMap.put(0, "Sunday");
        dMap.put(1, "Monday");
        dMap.put(2, "Tuesday");
        dMap.put(3, "Wednesday"); 
        dMap.put(4, "Thursday"); 
        dMap.put(5, "Friday");
        dMap.put(6, "Saturday"); 

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
  

    for (Room r: rooms){
      
        if(r.getBookings().size()>0){
                for(String l: r.getBookings()){
              

            if (checkAvailability(checkinDate.getTime(), 
            checkoutDate.getTime(), 
            bookingMap.get(l)[0],
            bookingMap.get(l)[1]))  {

                availableRooms.add(r);
                
            }
            else break;
                    
            }
        }
        else{
            availableRooms.add(r);
        }
      
       

    }
    Map<Integer, Integer> rMap = new HashMap<>();

    for(Room rm : availableRooms){
        TerminalLogger.log(getHotelName(rm.getHotelId())+", ");
        TerminalLogger.logln(rm.getType()+" Room");
        rMap.put(0, rm.getRate().getSunday());
        rMap.put(1, rm.getRate().getMonday());
        rMap.put(2, rm.getRate().getTuesday());
        rMap.put(3, rm.getRate().getWednesday());
        rMap.put(4, rm.getRate().getThursday());
        rMap.put(5, rm.getRate().getFriday());
        rMap.put(6, rm.getRate().getSaturday());
        
        
        System.out.println(format2.format(cidDate));
        System.out.println(format2.format(codDate));

        for(LocalDate d= cidDate; d.isBefore(codDate); d = d.plusDays(1)){
            int curDay = d.getDayOfWeek().getValue();
            System.out.println(curDay);
        }
       

    }
    }

    public String getHotelName(String hotelId){
        String s = "";
        return s;
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
