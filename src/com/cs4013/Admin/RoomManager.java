package com.cs4013.Admin;

import com.cs4013.Misc.*;
import com.cs4013.Model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomManager {

    FileManager fileManager = new FileManager("rooms.csv");

    public int width = 48;

    public RoomManager() {
    }

    public boolean addRoom()throws IOException {
        boolean keepGoing = false;
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Add Room", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
        boolean success = false;

        ArrayList<String> roomType = new ArrayList<>();
        roomType.add("Single");
        roomType.add("Double");
        roomType.add("Triple");
        roomType.add("Quad");


        //ask for room type
        //occupancy min/max
        //rates
        //hotel
        String input = "";
        String type = "single";
        int minO = 1;
        int maxO = 1;
        while (success == false){

            for(int i=0; i < roomType.size();i++){
            TerminalLogger.logln((i+1)+") "+roomType.get(i));
        }
            TerminalLogger.logln("Enter Room Type"+ TerminalColor.ANSI_YELLOW+"(Single)"+TerminalColor.ANSI_RESET);
            input = TerminalLogger.textfield("Enter 1-"+roomType.size(),width);
            if(input.matches("[0-9]")) {
                int n = Integer.parseInt(input);
                if (n > 0 && n <= roomType.size()) {
                    success = true;
                    type = roomType.get(n - 1);
                }
                else{
                    TerminalLogger.logError("Please Enter a number between 1-"+roomType.size());
                }
            }
            else{
                TerminalLogger.logError("Invalid Format!");
            }
        }
        success = false;
        while(success == false){
            TerminalLogger.logln("Enter Minimum Occupancy"+ TerminalColor.ANSI_YELLOW+"(1)"+TerminalColor.ANSI_RESET);
            TerminalLogger.logln("");
            input = TerminalLogger.textfield("Enter 1-5",width);
            if(input.matches("[1-5]")) {
                int n = Integer.parseInt(input);
                minO = n;
                success = true;
            }
                else{
                    TerminalLogger.logError("Please enter a number between 1-5");
            }
        }
        success = false;
        while(success == false){
            TerminalLogger.logln("Enter Maximum Occupancy"+ TerminalColor.ANSI_YELLOW+"(1)"+TerminalColor.ANSI_RESET);
            TerminalLogger.logln("");
            input = TerminalLogger.textfield("Enter "+ minO+"-5",width);
            if(input.matches("[1-5]")) {
                int n = Integer.parseInt(input);
                if (n >= minO) {
                    minO = n;
                    success = true;
                }
                else{
                    TerminalLogger.logError("Please enter a number between 1-5");
                }
            }
            else{
                TerminalLogger.logError("Please enter a number between 1-5");
            }
        }
        success = false;
        Rates rates = new Rates();


        while(success == false){
            TerminalLogger.logln("Enter Rates From Monday - Sunday"+ TerminalColor.ANSI_YELLOW+"(0-0-0-0-0-0-0)"+TerminalColor.ANSI_RESET);
            TerminalLogger.logln("");
            input = TerminalLogger.textfield("Enter Sun-Mon-Tues-Wed-Thurs-Fri-Sat",width);
            String []temp = input.split("-");
            if(temp.length == 7){
                if(input.matches("(([0-9]+)(-)?)+")) {
                    rates = new Rates(
                            Integer.parseInt(temp[0]),
                            Integer.parseInt(temp[1]),
                            Integer.parseInt(temp[2]),
                            Integer.parseInt(temp[3]),
                            Integer.parseInt(temp[4]),
                            Integer.parseInt(temp[5]),
                            Integer.parseInt(temp[6]));
                            success = true;
                    }
                    else{
                        TerminalLogger.logError("Please enter in the right format: 0-10-0-0-0-0-0, where Mon=0, Tues=10");
                    }
                }
                else{
                    TerminalLogger.logError("Please enter in the right format: 0-10-0-0-0-0-0, where Mon=0, Tues=10");
            }

            }
        ArrayList<Hotel> hotels = new FileParser().getHotels();
        success = false;
        Hotel selectedHotel = new Hotel();
        if(hotels.size()>0){
            selectedHotel = hotels.get(0);
        }

        while(success == false){
            TerminalLogger.logln("Select Hotel 1-"+hotels.size()+ TerminalColor.ANSI_YELLOW+"(1)"+TerminalColor.ANSI_RESET);
            TerminalLogger.logln("*".repeat(width));
            int i = 1;
            for(Hotel s : hotels){
                TerminalLogger.logln(i+") "+s.getName());
                i++;
            }
            TerminalLogger.logln("*".repeat(width));
            input = TerminalLogger.textfield("Enter 1-"+hotels.size(),width);
            if(input.matches("[0-9]+")){
                int n = Integer.parseInt(input);
                if(n <= hotels.size()){
                    selectedHotel = hotels.get(n-1);
                    success = true;
                }else{
                    TerminalLogger.logError("Please Enter Between  1-"+hotels.size());
                }
            }else{
                TerminalLogger.logError("Please Enter Between  1-"+hotels.size());
            }

        }

        Room room = new SingleRoom(selectedHotel.getHotelId());
        if(type.equals(roomType.get(0))){
            room = new SingleRoom(selectedHotel.getHotelId());
        }
        else if(type.equals(roomType.get(1))){
            room = new DoubleRoom(selectedHotel.getHotelId());
        }
        else if(type.equals(roomType.get(2))){
            room = new TripleRoom(selectedHotel.getHotelId());
        }
        else if(type.equals(roomType.get(3))){
            room = new QuadRoom(selectedHotel.getHotelId());
        }
        room.setRate(rates);
        room.setMaxOccupancy(maxO);
        room.setMinOccupancy(minO);
        selectedHotel.getRooms().clear();
        selectedHotel.getRooms().add(room.getRoomId());
        new HotelManager().edit(selectedHotel.getHotelId(),selectedHotel);
        fileManager.write(room.toString());
        TerminalLogger.logln("✓".repeat(width),TerminalColor.ANSI_GREEN);
        TerminalLogger.logln("Room Successfully Added", TerminalColor.ANSI_GREEN);
        TerminalLogger.logln("✓".repeat(width) + "\n",TerminalColor.ANSI_GREEN);

        String s = TerminalLogger.textfield("Would you like to add another room? y/n",width);
        if(s.equals("y")){
            keepGoing = true;
        }
        return keepGoing;
    }







    public void editRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Edit Room", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

    public void deleteRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Delete Room", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }
  public void viewRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | View Room", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
    }

}
