package com.cs4013.Admin;

import com.cs4013.Misc.StringUtils;
import com.cs4013.Misc.TerminalColor;
import com.cs4013.Misc.TerminalLogger;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomManager {
    public int width = 48;

    public RoomManager() {
    }

    public void addRoom() {
        TerminalLogger.logln("+".repeat(width));
        TerminalLogger.logln(StringUtils.centerString("Admin Control Centre | Add Room", 48, "|"));
        TerminalLogger.logln("+".repeat(width) + "\n");
        boolean success = false;

        ArrayList<String> roomType = new ArrayList<>();
        roomType.add("Single");
        roomType.add("Double");
        roomType.add("Triple");
        roomType.add("Quad");

        TerminalLogger.textfield("Enter Room Type", width);


        //ask for room type
        //occupancy min/max
        //rates
        //hotel
        String input = "";
        String type = "single";
        int minO = 1;
        int maxO = 1;
        while (success == false){
           TerminalLogger.logln("Enter Room Type"+ TerminalColor.ANSI_YELLOW+"(Single)"+TerminalColor.ANSI_RESET);

            for(int i=0; i < roomType.size();i++){
            TerminalLogger.logln((i+1)+") "+roomType.get(i));
        }
        TerminalLogger.logln("");
            input = TerminalLogger.textfield("Enter 1-"+roomType.size(),width);
            if(input.matches("[1-9]")) {
                int n = Integer.parseInt(input);
                if (n > 0 && n < roomType.size()) {
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
