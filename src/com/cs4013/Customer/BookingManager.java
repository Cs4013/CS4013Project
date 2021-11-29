package com.cs4013.Customer;

import java.time.LocalDate;

import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import com.cs4013.Misc.Formats;
import com.cs4013.Misc.TerminalLogger;

public class BookingManager {
    public BookingManager (){

    }
    /**
     * 
     * @return
     */
    public void searchRoom (){

    
   
       boolean success = true;
        

        //get check in date and check out date from user//
        //make sure that both check in date and check out dates are valid in the format dd||mm||yyyy //
        String input = "";
        LocalDate checkinDate = LocalDate.now();
        LocalDate checkoutDate = LocalDate.now();
        LocalDate today = LocalDate.now();
    while (success == false) {

        input = TerminalLogger.textfield("Enter Check In date in format dd/mm/yyyy", 50);
        if (Formats.isValidDate(input)){
            String [] temp = input.split("/");
            int d = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            int y = Integer.parseInt(temp[2]);
            checkinDate = LocalDate.of(y, m, d);
            if (checkinDate.isAfter(today)||checkinDate.isEqual(today)){
                success = true;
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
            checkoutDate = LocalDate.of(y, m, d);
            if (checkoutDate.isAfter(checkinDate)){
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


    }
}
