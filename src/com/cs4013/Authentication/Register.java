package com.cs4013.Authentication;

import java.io.IOError;
import java.io.IOException;
import java.util.UUID;

import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Misc.*;
/**
 * Register
 * This class is used to register new customers to the application
 * @Method addNewUser is used in order to add the new username and password needed 
 * Then storing the information into a csv file
 */
public class Register {
    public Register(){}
        
        public boolean addNewUser()throws IOException{
            boolean success=false;
           String username = TerminalLogger.textfield("Enter username", 50);
            String password = TerminalLogger.textfield("Enter a password", 50);

            String userId = UUID.randomUUID().toString();

            String csv = userId+","+username+","+password;
            FileManager fm = new FileManager("customers.csv");
            
            fm.write(csv);
            TerminalLogger.logln("Welcome "+username+" registration successfull",TerminalColor.ANSI_GREEN);


            return success; 
        
    }
    
    
}
