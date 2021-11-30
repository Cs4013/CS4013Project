package com.cs4013.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cs4013.Admin.RoomManager;
import com.cs4013.Interface.IPrompt;
import com.cs4013.Misc.*;
import com.cs4013.Model.Hotel;
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

        

        ArrayList<String> init = new ArrayList<>();
        init.add("CI");
        init.add("VR");
        init.add("MR");
        init.add("BR");
        init.add("AF");
        navStack.put("/",init);

         init = new ArrayList<>();

    }
    public void printDefiniton(String command){
        for(String s : navStack.get(command)){
           TerminalLogger.logln(definition.get(s), TerminalColor.ANSI_CYAN);
        }
            
            
    }

    public void checkIn(){

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
            if(r.getHotelId().equals(roomId)){
                return r;
            }
        }
        return null;
    }

    public void viewRoom(){

    }
    public void modifyReseversation(){

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
                case "MR":
                    modifyReseversation();
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


