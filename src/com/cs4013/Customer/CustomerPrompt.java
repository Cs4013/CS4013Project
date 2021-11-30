package com.cs4013.Customer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

import com.cs4013.Admin.HotelManager;
import com.cs4013.Admin.RoomManager;
import com.cs4013.Interface.IPrompt;
import com.cs4013.Misc.CurrentUser;
import com.cs4013.Misc.StringUtils;
import com.cs4013.Misc.TerminalColor;
import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Model.Booking;
import com.cs4013.Misc.*;
import com.cs4013.Model.Hotel;
import com.cs4013.Model.HotelAccount;
import com.cs4013.Model.Room;


public class CustomerPrompt implements IPrompt {

    public boolean keepGoing = true;
    String currentPath = "/";
    public Map<String,String> definition =new HashMap<>();
    public Map<String,ArrayList<String>> navStack = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ArrayList<String> prevPath = new ArrayList<String>();
    int width = 50;
    private RoomManager roomManager = new RoomManager();
    public CustomerPrompt(){
        populateNavStack();
    }
    private void populateNavStack(){

        definition.put("CI","Enter CI to Check In");
        definition.put("VR", "Enter VR to View Reservations"); 
        definition.put("MR","Enter MR Modify Reservations" );

        definition.put("BR","Enter BR to Book Room");
        definition.put("AF","Enter AF to Add Funds");


        definition.put("CR","Enter CR to Cancel Reservation");
        definition.put("CT","Enter CT to Change Reservation ");



        ArrayList<String> init = new ArrayList<>();
        init.add("CI");
        init.add("VR");
        init.add("MR");
        init.add("BR");
        init.add("AF");
        navStack.put("/",init);

        init = new ArrayList<>();
        init.add("CR");
        //init.add("CT");
        navStack.put("MR",init);



         init = new ArrayList<>();

    }
    public void printDefiniton(String command){
        for(String s : navStack.get(command)){
           TerminalLogger.logln(definition.get(s), TerminalColor.ANSI_CYAN);
        }
            
            
    }


    public void checkIn(){
        ArrayList<Booking> bookings = CurrentUser.user.getReservations("y");
        System.out.println(bookings);
        if(bookings.size()>0){
            boolean b = false;
            while(!b){
                viewRoom();
                String s = TerminalLogger.textfield("Enter 1-"+bookings.size(),width);
                if(s.matches("[0-9]+")) {
                    int n = Integer.parseInt(s);
                    if (n > 0 && n <= bookings.size()) {
                        bookings.get(n).setCheckedIn(true);
                        new ReservationManager().update(bookings.get(n));
                        b = true;
                    } else {
                        TerminalLogger.logError("Please \"Enter 1-\"+bookings.size(),width ");
                    }
                }
                else{
                        TerminalLogger.logError("Please \"Enter 1-\"+bookings.size(),width ");

                }
            }
            TerminalLogger.logln("✓".repeat(width),TerminalColor.ANSI_GREEN);
            TerminalLogger.logln("Successfully Checked In!", TerminalColor.ANSI_GREEN);
            TerminalLogger.logln("✓".repeat(width) + "\n",TerminalColor.ANSI_GREEN);


        }
        else{
            TerminalLogger.logError("No Reservations To Check Into!");
            goBack();
        }

    }
    public Hotel getHotel(String hotelId){
        ArrayList<Hotel> hotels = new FileParser().getHotels();
        for(Hotel h : hotels){
            if(h.getHotelId().equals(hotelId)){
                return h;
            }
        }
        return null;
    }
    public Room getRoom(String roomId){
        ArrayList<Room> rooms = new FileParser().getRooms();
        for(Room r : rooms){
            if(r.getRoomId().equals(roomId)){
                return r;
            }
        }
        return null;
    }

    public void viewRoom(){
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString(CurrentUser.user.username + " | "+" View reservations",width-2));
        TerminalLogger.logln("+".repeat(width));
        ArrayList <String> bookingId = CurrentUser.user.reservations;
        String input = TerminalLogger.textfield("Do you want to see all approved reservations? y/n", 50);
        ArrayList<Booking> bookings = CurrentUser.user.getReservations(input);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(bookings.size() < 1)
        {
            TerminalLogger.logln("No Reservations Found",TerminalColor.ANSI_YELLOW);
        }else{
            int i = 1;
            for(Booking b: bookings){
                Hotel h = getHotel(b.getHotelId());
                Room r = getRoom(b.getRoomId());
                long cin  = b.getCheckInTime();
                long cout = b.getCheckOutDate();
                TerminalLogger.log(i+") ", TerminalColor.ANSI_PURPLE);
                TerminalLogger.log(h.getName()+" ", TerminalColor.ANSI_PURPLE);
                TerminalLogger.log(r.getType()+" ", TerminalColor.ANSI_YELLOW);
                TerminalLogger.log(format.format(cin)+"-"+format.format(cout), TerminalColor.ANSI_BLUE);
                TerminalLogger.logln("");
                i++;
            }
        }


        int i = 1;
        for(Booking b: bookings){
            Hotel h = getHotel(b.getHotelId());
            Room r = getRoom(b.getRoomId());
            long cin  = b.getCheckInTime();
            long cout = b.getCheckOutDate();
            TerminalLogger.log(i+") ", TerminalColor.ANSI_PURPLE);
            TerminalLogger.log(h.getName()+" ", TerminalColor.ANSI_PURPLE);
            TerminalLogger.log(r.getType()+" ", TerminalColor.ANSI_YELLOW);
            TerminalLogger.log(format.format(cin)+"-"+format.format(cout), TerminalColor.ANSI_BLUE);
            TerminalLogger.logln("");
            i++;
        }
        TerminalLogger.logln("=".repeat(width));

        goBack();

    }
    public void changeReservation(){
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString(CurrentUser.user.username + " | "+" Change Reservation",width-2));
        TerminalLogger.logln("+".repeat(width));
        ArrayList<Booking> bookings = CurrentUser.user.getReservations("y");

        viewRoom();

        String input = "";
        boolean b = false;
        TerminalLogger.logln("NOTE: \nPlease note that an Advanced Booking cannot be fully refunded.\nA Standard Booking an Only be refunded within 48 hours from Check-In Time",TerminalColor.ANSI_YELLOW);
        if(bookings.size() < 1)
        {
            TerminalLogger.logln("No Reservations Found",TerminalColor.ANSI_YELLOW);
        }else {
            while (!b) {
                input = TerminalLogger.textfield("Enter 1-" + bookings.size(), 50);
                if (input.matches("[0-9]+")) {
                    int n = Integer.parseInt(input);
                    if (n > 0 && n <= bookings.size()) {
                        Booking bk = bookings.get(n);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date checkin = format.parse(new SimpleDateFormat("dd/MM/yyyy").format(bk.getCheckInTime()));
                            Calendar start = Calendar.getInstance();
                            start.setTime(checkin);
                            start.add(Calendar.HOUR, -48);
                            Date checkin48 = start.getTime();
                            if (checkin48.before(checkin)) {
                                //cancel
                                //refund
                                if (bk.getBookingType().equals("AR")) {
                                    CurrentUser.user.wallet += bk.getTotalCost();
                                    CurrentUser.user.updateUser();
                                    Hotel hotel = getHotel(bk.getHotelId());
                                    int index = -1;
                                    HotelAccount a = null;
                                    for (HotelAccount ha : hotel.getAccount()) {
                                        if (ha.getUserId().equals(CurrentUser.user.userId)) {
                                            index += 1;
                                            a = ha;
                                            a.setAmountPayed(a.getAmountPayed() - bk.getTotalCost());
                                            break;
                                        }
                                        index++;
                                    }
                                    if (index > -1) {
                                        hotel.getAccount().set(index, a);
                                    }
                                    try {
                                        new HotelManager().edit(bk.getHotelId(), hotel);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    TerminalLogger.logln("✓".repeat(width), TerminalColor.ANSI_GREEN);
                                    TerminalLogger.logln("Refund Rewarded Successfully", TerminalColor.ANSI_GREEN);
                                    TerminalLogger.logln("✓".repeat(width) + "\n", TerminalColor.ANSI_GREEN);
                                    b = true;
                                } else {
                                    TerminalLogger.logError("_".repeat(width));
                                    TerminalLogger.logError("You are not entitled to a refund, unfortunately");
                                    TerminalLogger.logError("_".repeat(width) + "\n");

                                }
                            } else {
                                TerminalLogger.logError("_".repeat(width));
                                TerminalLogger.logError("You are not entitled to a refund, unfortunately");
                                TerminalLogger.logError("_".repeat(width) + "\n");

                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    } else {
                        TerminalLogger.logError("Enter 1-" + bookings.size());
                    }
                } else {
                    TerminalLogger.logError("Enter 1-" + bookings.size());
                }
            }
        }

    }
    public void cancelReservation(){
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString(CurrentUser.user.username + " | "+" Cancel Reservation",width-2));
        TerminalLogger.logln("+".repeat(width));

        ArrayList<Booking> bookings = CurrentUser.user.getReservations("y");

        viewRoom();

        String input = "";
        boolean b = false;
        TerminalLogger.logln("NOTE: \nPlease note that an Advanced Booking cannot be fully refunded.\nA Standard Booking an Only be refunded within 48 hours from Check-In Time",TerminalColor.ANSI_YELLOW);
        if(bookings.size() < 1)
        {
            TerminalLogger.logln("No Reservations Found",TerminalColor.ANSI_YELLOW);
        }else{
            while(!b){
                input = TerminalLogger.textfield("Enter 1-"+bookings.size(),50);
                if(input.matches("[0-9]+")){
                    int n = Integer.parseInt(input);
                    if(n > 0 && n <= bookings.size()){
                        Booking bk = bookings.get(n);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date checkin = format.parse(new SimpleDateFormat("dd/MM/yyyy").format(bk.getCheckInTime()));
                            Calendar start = Calendar.getInstance();
                            start.setTime(checkin);
                            start.add(Calendar.HOUR, -48);
                            Date checkin48 = start.getTime();
                            if(checkin48.before(checkin)){
                                //cancel
                                //refund
                                if(bk.getBookingType().equals("AR")){
                                    CurrentUser.user.wallet+=bk.getTotalCost();
                                    CurrentUser.user.updateUser();
                                    Hotel hotel = getHotel(bk.getHotelId());
                                    int index = -1;
                                    HotelAccount a = null;
                                    for(HotelAccount ha: hotel.getAccount()){
                                        if(ha.getUserId().equals(CurrentUser.user.userId)){
                                            index+=1;
                                            a = ha;
                                            a.setAmountPayed(a.getAmountPayed()-bk.getTotalCost());
                                            break;
                                        }
                                        index++;
                                    }
                                    if(index>-1){
                                        hotel.getAccount().set(index,a);
                                    }
                                    try {
                                        new HotelManager().edit(bk.getHotelId(),hotel);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    TerminalLogger.logln("✓".repeat(width),TerminalColor.ANSI_GREEN);
                                    TerminalLogger.logln("Refund Rewarded Successfully", TerminalColor.ANSI_GREEN);
                                    TerminalLogger.logln("✓".repeat(width) + "\n",TerminalColor.ANSI_GREEN);
                                    b=true;
                                }else {
                                    TerminalLogger.logError("_".repeat(width));
                                    TerminalLogger.logError("You are not entitled to a refund, unfortunately");
                                    TerminalLogger.logError("_".repeat(width) + "\n");

                                }
                            }else {
                                TerminalLogger.logError("_".repeat(width));
                                TerminalLogger.logError("You are not entitled to a refund, unfortunately");
                                TerminalLogger.logError("_".repeat(width) + "\n");

                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }else{
                        TerminalLogger.logError("Enter 1-"+bookings.size());
                    }
                }else{
                    TerminalLogger.logError("Enter 1-"+bookings.size());
                }
            }
        }

        //Advanced purchases are un refundable
        //SR the full booking value will be charge if cancel whithin 48 hours
        //save when checking

        TerminalLogger.logln("=".repeat(width));
        goBack();

    }
    public void bookReseversation(){
        new BookingManager(CurrentUser.user).searchRoom();
    }
    public void addFunds(){

        int currentWallet = CurrentUser.user.wallet;
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString(CurrentUser.user.username + " | "+" Add Funds",width-2));
        TerminalLogger.logln("+".repeat(width));
        String input = TerminalLogger.textfield("Enter Payment Amount", width);
        int newAmount = Integer.parseInt(input);
        int total = newAmount + currentWallet;
        TerminalLogger.logln("✓".repeat(width),TerminalColor.ANSI_GREEN);
        TerminalLogger.logln("Funds Successfully Added", TerminalColor.ANSI_GREEN);
        TerminalLogger.logln("✓".repeat(width) + "\n",TerminalColor.ANSI_GREEN);

        CurrentUser.user.wallet = total;
        CurrentUser.user.updateUser();

        goBack();

    }
    public void goBack(){
        if(prevPath.size() > 0){
            currentPath = prevPath.remove(prevPath.size()-1);
        }
        else{
            keepGoing = false;
        }
    }
    @Override
    public void  display(String command){
            switch (command) {
                case "CI":
                    checkIn();
                    break;
                case "VR":
                    viewRoom();
                    break;
                case "CR":
                    cancelReservation();
                    break;
                case "BR":
                    bookReseversation();
                    break;
                case "AF":
                    addFunds();
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
    @Override
    public void execute(){
        TerminalLogger.logln("=".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Hotels.com/"+CurrentUser.user.username, 50, "|"));
        TerminalLogger.logln("=".repeat(width)+ "\n");
        while(keepGoing){
            display(currentPath);
            if(!keepGoing){
                String result = TerminalLogger.textfield("Are you sure you want to exit? y/n", width);
                if(result.equals("n")){
                    keepGoing = true;
                }

                }
            }
        }
    }


