package com.cs4013.Admin;

import com.cs4013.Customer.BookingManager;
import com.cs4013.Customer.ReservationManager;
import com.cs4013.Interface.IPrompt;
import com.cs4013.Misc.*;
import com.cs4013.Model.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**AdminPrompt
 * This class is used to access the Admin Prompts for the application
 * There are multiple selections available
 * @method populateNavStack add commands ask eys
 * @mothod printDefinition
 * @method addRoom
 * @method addHotel
 *
 *
 */
public class AdminPrompt implements IPrompt {

    public boolean keepGoing = true;
    String currentPath = "/";
    public Map<String,String> definition =new HashMap<>();
    public Map<String,ArrayList<String>> navStack = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ArrayList<String> prevPath = new ArrayList<String>();
    int width = 50;
    private RoomManager roomManager = new RoomManager();
    private HotelManager hotelManager = new HotelManager();

    public AdminPrompt(){
        populateNavStack();
    }
    private void populateNavStack(){

        definition.put("MR","Enter MR to Modifify Rooms");
        definition.put("MH", "Enter MH to Modify Hotels"); 
        definition.put("AH","Enter AH for data analysis for hotel" );
        definition.put("AB","Enter AB for approve booking for room" );

        definition.put("AR","Enter AR to Add Rooms");
        definition.put("ER","Enter ER to Edit Rooms");
        definition.put("DR","Enter DR to Delete rooms");
        definition.put("VR","Enter VR to View Rooms");

        definition.put("ADH","Enter ADH to Add Hotel");
        definition.put("EH","Enter EH to Edit Hotel");
        definition.put("DH","Enter DH to Delete Hotel");
        definition.put("VH","Enter VH to View Hotel");

        

        ArrayList<String> init = new ArrayList<>();
        init.add("MR");
        init.add("MH");
        init.add("AH");
        init.add("AB");
        navStack.put("/",init);

         init = new ArrayList<>();
         init.add("AR");
//         init.add("ER");
//         init.add("DR");
//         init.add("VR");
         navStack.put("MR",init);
         

         init = new ArrayList<>();

        
        init.add("ADH");
//        init.add("EH");
//        init.add("DH");
//        init.add("VH");
         navStack.put("MH",init);

         init = new ArrayList<>();

         navStack.put("AH",init);




         

         
    }
    public void printDefiniton(String command){
        for(String s : navStack.get(command)){
           TerminalLogger.logln(definition.get(s), TerminalColor.ANSI_CYAN);
        }
            
            
    }
    public boolean addRoom()throws IOException{
        return  roomManager.addRoom();
    }
    public boolean addHotel()throws IOException{
        return hotelManager.addHotel();
    }

    public void goBack(){
        if(prevPath.size() > 0){
            currentPath = prevPath.remove(prevPath.size()-1);
        }
        else{
            keepGoing = false;
        }
    }
    public void analyzeHotel(){

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


        TerminalLogger.logln("+".repeat(50));
        TerminalLogger.logln(StringUtils.centerString("Analysis For Hotels", 48, "|"));
        TerminalLogger.logln("+".repeat(50) + "\n");

//        while (success == false) {
//
//            input = TerminalLogger.textfield("Enter Check In date in format dd/mm/yyyy", 50);
//            if (Formats.isValidDate(input)){
//
//                String [] temp = input.split("/");
//                int d = Integer.parseInt(temp[0]);
//                int m = Integer.parseInt(temp[1]);
//                int y = Integer.parseInt(temp[2]);
//                try {
//                    checkinDate = format.parse(input);
//                } catch (ParseException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            } else {
//                TerminalLogger.logError("Invalid date format");
//            }
//
//        }
//        success = false;
//        while (success == false) {
//
//            input = TerminalLogger.textfield("Enter Check Out date in format dd/mm/yyyy", 50);
//            if (Formats.isValidDate(input)){
//                String [] temp = input.split("/");
//                int d = Integer.parseInt(temp[0]);
//                int m = Integer.parseInt(temp[1]);
//                int y = Integer.parseInt(temp[2]);
//
//                try {
//                    checkoutDate = format.parse(input);
//                } catch (ParseException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                if (checkoutDate.after(checkinDate)){
//                    success = true;
//                }
//                else{
//                    TerminalLogger.logError("Please Enter a date past or equal to "+format.format(checkinDate));
//                }
//
//
//
//            } else {
//                TerminalLogger.logError("Invalid date format");
//            }
//
//        }
        //we firs
        ArrayList<Hotel> hotels = new FileParser().getHotels();
        ArrayList<Room> rooms = new FileParser().getRooms();

        for(Hotel h : hotels){
            TerminalLogger.logln("Hotel Analysis",TerminalColor.ANSI_CYAN);
            TerminalLogger.logln("_".repeat(50));
            TerminalLogger.logln("Name: "+h.getName());
            TerminalLogger.logln("Ovr. Occupancy: "+h.getAccount().size());

            int n = 0;
            for(HotelAccount act : h.getAccount()){
                n+=act.getAmountPayed();
            }
            TerminalLogger.logln("Avg. Rates: "+(n/Math.max(1,h.getAccount().size())));

        }

        TerminalLogger.logln("");

        goBack();
    }
    public void aproveBooking(){
        ArrayList<Booking> bookings = new FileParser().getReservation("n_approved");
        TerminalLogger.logln("-".repeat(50));
        TerminalLogger.logln(StringUtils.centerString("Approve Booking", 48, "|"));
        TerminalLogger.logln("-".repeat(50) + "\n");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        boolean succes = false;
        String input = "";
        while(!succes){
            int i = 0;

            for(Booking b : bookings){

                TerminalLogger.log((i+1)+") ",TerminalColor.ANSI_YELLOW);
                Hotel h = new FileParser().getHotel(b.getHotelId());
                Room r = new FileParser().getRoom(b.getRoomId());
                User u = new FileParser().getUser(b.getUserId());
                TerminalLogger.logln("Username: "+u.username+" | Hotel:"+h.getName()+" | Room Type: "+r.getType()+" | Dates: "+ format.format(b.getCheckInTime())+"-"+format.format(b.getCheckOutDate()),TerminalColor.ANSI_BLUE);
                i++;
            }
            input = TerminalLogger.textfield("Please Enter Room 1-"+bookings.size(),width);
            if(input.matches("[1-9]+")){
                int n = Integer.parseInt(input)-1;
                if(n<bookings.size()&&n>-1){

                    Booking bk =  bookings.get(n);
                    bk.setApproved(true);
                    new ReservationManager().update(bk);


                    String s = TerminalLogger.textfield("Are you sure you want approve this booking? y/n",width);
                    if(s.equals("y")){
                        TerminalLogger.logln("✓".repeat(width),TerminalColor.ANSI_GREEN);
                        TerminalLogger.logln("Room Successfully Added", TerminalColor.ANSI_GREEN);
                        TerminalLogger.logln("✓".repeat(width) + "\n",TerminalColor.ANSI_GREEN);
                        succes = true;
                        goBack();
                    }
                }
                else{
                    TerminalLogger.logError("Booking Approved"+bookings.size());
                }




            }else{
                TerminalLogger.logError("Invalid Number Entered 1-"+bookings.size());
            }

        }
    }
    @Override
    public void  display(String command){
        switch(command){
            case "AR": 
            try{
               if(!addRoom()){
                    goBack();
               }
               
            }catch(IOException e){

            }
            break;
            case "AH":
                analyzeHotel();
                break;
            case "AB":
                aproveBooking();
                break;
            case "ADH":
                try{
                    if(!addHotel()){
                        goBack();
                    }

                }catch(IOException e){

                }
                break;
            default:
            printDefiniton(command);
            String input = TerminalLogger.textfield("Enter Here", width);
            if(input.equals("back") || (input.equals("exit"))){
                if(input.equals("exit")){
                    keepGoing = false;
                }
                else{
                    goBack();
                }
               
            }
            else{
                if(navStack.get(currentPath).contains(input)){

                    prevPath.add(currentPath);
                    currentPath = input;
    
                }
                else{
                    TerminalLogger.logError("Command " + input + " not recognized! ");
                }
            }
            
            break;
        }
     }
        
        
    public void execute(){

        TerminalLogger.logln("=".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Center", 50, "|"));
        TerminalLogger.logln("=".repeat(width)+ "\n");
        while(keepGoing){
            display(currentPath);
            if(!keepGoing){
                String result = TerminalLogger.textfield("ARe you sure you want to exit? y/n", width);
                if(result.equals("n")){
                    keepGoing = true;
                }

                }
            }
        }

    }
    
    
