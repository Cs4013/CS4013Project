package com.cs4013.Authentication;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.cs4013.Misc.TerminalLogger;
import com.cs4013.Model.User;
import com.cs4013.Misc.*;
/**
 * Register
 * This class is used to register new customers to the application
 * @Method addNewUser is used in order to add the new username and password needed 
 * Then storing the information into a csv file
 */
public class Register {
    public Register(){}
        
        public User addNewUser()throws IOException{
          
           String username = TerminalLogger.textfield("Enter username", 50);
            String password = TerminalLogger.textfield("Enter a password", 50);

            String userId = UUID.randomUUID().toString();

            User user = new User(userId);
            user.username = username;
            user.password = password;
            user.wallet = 0;
            user.reservations=new ArrayList<>();

            String csv = user.toString();
            FileManager fm = new FileManager("customers.csv");
            
            fm.write(csv);
            TerminalLogger.logln("Welcome "+username+" registration successfull",TerminalColor.ANSI_GREEN);


            return user; 
        
    }
    
    
}
