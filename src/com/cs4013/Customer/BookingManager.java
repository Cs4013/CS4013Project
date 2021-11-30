package com.cs4013.Customer;

import java.io.IOException;
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

import com.cs4013.Admin.HotelManager;
import com.cs4013.Misc.FileManager;
import com.cs4013.Misc.FileParser;
import com.cs4013.Misc.Formats;
import com.cs4013.Misc.StringUtils;
import com.cs4013.Misc.TerminalColor;
import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Model.Booking;
import com.cs4013.Model.Hotel;
import com.cs4013.Model.HotelAccount;
import com.cs4013.Model.Room;
import com.cs4013.Model.User;

public class BookingManager {

    ArrayList<Hotel> hotels = new ArrayList<>();
    Map <Integer , String> dMap = new HashMap<>();
    
    ArrayList<Room>availableRooms = new ArrayList<>();
    User currentUser;
    public BookingManager (){
        hotels = new FileParser().getHotels();
    }
    
    public BookingManager (User user){
        hotels = new FileParser().getHotels();
        this.currentUser = user;
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
        
        dMap.put(0, "Sunday");
        dMap.put(1, "Monday");
        dMap.put(2, "Tuesday");
        dMap.put(3, "Wednesday"); 
        dMap.put(4, "Thursday"); 
        dMap.put(5, "Friday");
        dMap.put(6, "Saturday"); 


        TerminalLogger.logln("+".repeat(50));
        TerminalLogger.logln(StringUtils.centerString(currentUser.username+" | Book Room", 48, "|"));
        TerminalLogger.logln("+".repeat(50) + "\n");
        
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
    ArrayList<Booking>bookings = parser.getReservation(true);
    ArrayList<Room>rooms = parser.getRooms();
    availableRooms = new ArrayList<>();

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
   
    

    TerminalLogger.logln("*".repeat(50));
    TerminalLogger.logln("Rooms Available Between "+format.format(checkinDate)+" - "+format.format(checkoutDate));
    TerminalLogger.logln("Please Select A Room to book your reservation "+format.format(checkinDate)+" - "+format.format(checkoutDate));
    TerminalLogger.logln("*".repeat(50));
 Map<Integer, Integer> rMap = new HashMap<>();
    int index = 0;
    

    for(Room rm : availableRooms){
        TerminalLogger.log((index+1)+") ",TerminalColor.ANSI_CYAN);
        index++;
        TerminalLogger.logln(">".repeat(50));
        printRoomDetails(rm,rMap,checkinDate,checkoutDate);
        TerminalLogger.logln("<".repeat(50));
        TerminalLogger.log("\n");
        
    }

    //Ask For Selection
    success = false;
    int selectedRoomIndex = 0;
    while(success == false){
        input = TerminalLogger.textfield("Enter 1-"+availableRooms.size(), 50);
        if(input.matches("[0-9]+")){
            int n = Integer.parseInt(input);
            if(n <= availableRooms.size() && n > 0){
                selectedRoomIndex = n-1;
                TerminalLogger.logln("=".repeat(50));
                 TerminalLogger.logln("SELECTED ROOM\n");
                printRoomDetails(availableRooms.get(selectedRoomIndex),rMap,checkinDate,checkoutDate);
                TerminalLogger.logln("=".repeat(50)+"\n");
                input = TerminalLogger.textfield("Are you happy with your selection? y/n", 50);
                if(input.equals("y")){
                    success=true;
                }
             
            }
            else{
                TerminalLogger.logError("Invalid Number: Enter 1-"+availableRooms.size());
            }
        }else{
            TerminalLogger.logError("Invalid Number: Enter 1-"+availableRooms.size());
        }
    }
    success = false;
    String reservationType = "S";
    double discount = 0;
   //Advance Purchase / Standard
   while(success == false){
        TerminalLogger.logln("Select Purchase Type"+TerminalColor.ANSI_YELLOW+"(Standard)"+TerminalColor.ANSI_RESET);

        TerminalLogger.log(1+") ",TerminalColor.ANSI_YELLOW);
        TerminalLogger.logln("Standard");

        TerminalLogger.log(2+") ",TerminalColor.ANSI_YELLOW);
        TerminalLogger.logln("Advance(5% discount)");

        input = TerminalLogger.textfield("Enter 1-2 or help for more info", 50);
        if(input.equals("help")){
            TerminalLogger.logln("Standard Purchase: the full booking value will be charged if the cancellation is received within 48 hours of check-in or if the booking is a ‘no-show’. A standard reservation is fully refundable if cancelled more than 48 hours before the check-in date");
            TerminalLogger.logln("Advance Purchase: an advance purchase reservation. A 5% discount applies to all AP reservations. Advance Reservations are Non-fundable");


        }
        else if(input.matches("[1-2]")){
            if(input.equals("1")){
                reservationType = "S";
            }else{
                reservationType = "AR";
                discount=0.05;
            }
            success = true;
        }else{
            TerminalLogger.logError("Please Enter 1-2 or help for more info");
        }
   }
   
   input = TerminalLogger.textfield("Would you like to continue with purchase? y/n", 50);
   if(input.equals("y")){
       //Transaction
       //Show Purchase + Discount
       TerminalLogger.logln("=".repeat(50));
       TerminalLogger.logln("Your Purchase");
       TerminalLogger.logln("=".repeat(50));
       int total = printRoomDetails(availableRooms.get(selectedRoomIndex), rMap, checkinDate, checkoutDate);
       TerminalLogger.logln("Discount: "+total*discount);
       TerminalLogger.logln("-".repeat(50));
       TerminalLogger.logln("TOTAL: "+( total - (total*discount)));
       TerminalLogger.logln("-".repeat(50));
       //Enter Password
       int attempt = 2;
       success = false;
       while(attempt>0){
           input = TerminalLogger.textfield("Confirm Password", 50);
           if(input.matches(currentUser.password)){
               success = true;//check if Transaction is valid
               break;
           }else{
            attempt--;
            TerminalLogger.logError("Invalid Password, Please Retry ("+attempt+" left)");
           }
       }
        //Add to reservation_requests
       if(success){
           Booking booking = new Booking();
           booking.setRoomId(availableRooms.get(selectedRoomIndex).getRoomId());
           booking.setCheckInTime(checkinDate.getTime());
           booking.setCheckOutDate(checkoutDate.getTime());
           booking.setApproved(false);
           booking.setHotelId(availableRooms.get(selectedRoomIndex).getHotelId());
           booking.setTotalCost((int)(total - (total*discount)));
           booking.setUserId(currentUser.userId);
           booking.setBookingType(reservationType);

           FileManager fm = new FileManager("bookings.csv");
           try {
            fm.write(booking.toString());
            currentUser.reservations.add(booking.getBookingId());
            currentUser.updateUser();
            HotelAccount hotelAccount = new HotelAccount();
            hotelAccount.setAmountPayed((int)(total - (total*discount)));
            hotelAccount.setDatePayed(today.getTime());
            hotelAccount.setHotelId(availableRooms.get(selectedRoomIndex).getHotelId());
            hotelAccount.setUserId(currentUser.userId);

            new HotelManager().edit(hotelAccount.getHotelId(), getHotel(hotelAccount.getHotelId()));

            TerminalLogger.logln("✓".repeat(50),TerminalColor.ANSI_GREEN);
            TerminalLogger.logln("Booked Room Successfully, Your Reservation will be confirmed soon.\nThank You", TerminalColor.ANSI_GREEN);
            TerminalLogger.logln("✓".repeat(50) + "\n",TerminalColor.ANSI_GREEN);

        } catch (IOException e) {
            e.printStackTrace();
        }

       }
      
   }

}

public int printRoomDetails(Room rm, Map<Integer,Integer> rMap,Date checkinDate,Date checkoutDate ){
    Hotel h =getHotel(rm.getHotelId());
    int totalRates = -1;
    if(h!=null){
        totalRates = 0;
        TerminalLogger.log(h.getName(),TerminalColor.ANSI_PURPLE);
        TerminalLogger.logln("\t"+rm.getType()+" Room",TerminalColor.ANSI_BLUE);
        rMap.put(0, rm.getRate().getSunday());
        rMap.put(1, rm.getRate().getMonday());
        rMap.put(2, rm.getRate().getTuesday());
        rMap.put(3, rm.getRate().getWednesday());
        rMap.put(4, rm.getRate().getThursday());
        rMap.put(5, rm.getRate().getFriday());
        rMap.put(6, rm.getRate().getSaturday());
        
        
        Calendar start = Calendar.getInstance();
        start.setTime(checkinDate);
        Calendar end = Calendar.getInstance();
        end.setTime(checkoutDate);

        //Print retes for days in between cin - cout
       
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            // Do your job here with `date`.
            Calendar temp = Calendar.getInstance();
            temp.setTime(date);
            int curDay = temp.get(Calendar.DAY_OF_WEEK)-1;
            TerminalLogger.logln("\t"+dMap.get(curDay)+": \t \u20AC"+rMap.get(curDay),TerminalColor.ANSI_YELLOW);
            totalRates+= rMap.get(curDay);
        }
        TerminalLogger.logln("-".repeat(50));
        TerminalLogger.logln("Total Amount to be paid: \t \u20AC"+totalRates,TerminalColor.ANSI_YELLOW);
        TerminalLogger.logln("-".repeat(50));
        TerminalLogger.log("");

    }else{
      
    }
   
        return totalRates;
}
    public Hotel getHotel(String hotelId){
        Hotel s=null;
        for(Hotel hotel : hotels){
            if(hotel.getHotelId().equals(hotelId)){
                s=hotel;
                break;
            }
        }
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
