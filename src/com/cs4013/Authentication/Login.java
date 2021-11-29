package com.cs4013.Authentication;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.cs4013.Admin.AdminPrompt;
import com.cs4013.Misc.FileManager;
import com.cs4013.Misc.TerminalColor;
import com.cs4013.Misc.TerminalLogger;

/**
 * Login
 */
public class Login {

   
    public Login(){
    }
    public boolean loginAsAdmin (){
        boolean success = false;
        String password="";
        FileManager f = new FileManager("AdminPassword.txt");
        try {
            ArrayList<String> data= f.readLine();
            int attempt = 3;
            int i = 0;
            while(i<attempt){
               password = TerminalLogger.textfield("Enter password",50);
               if (data.contains(password)){
                    TerminalLogger.logln("Login success welcome back",TerminalColor.ANSI_GREEN);
                    success = true;
                    break;
                }
                TerminalLogger.logError("Incorrect Password, "+((attempt-i)-1)+" Attempts Left!");
                i++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

       return success;


    }
    public boolean loginAsCustomer ()throws IOException{
        boolean success = false;
        FileManager bfile = new FileManager("customers.csv");
            ArrayList<ArrayList<String>> data = bfile.readCsv();
            Map<String,String> users = new HashMap<>();
        while(success==false){
            String userName = TerminalLogger.textfield("Enter your user name", 50);
            String password = TerminalLogger.textfield("Enter your password", 50);
            for(ArrayList<String> user: data){
                users.put(user.get(1), user.get(2));
            }
            if(users.containsKey(userName)){
                if(users.get(userName).equals(password)){
                    TerminalLogger.logln("Welcome back "+ userName);
                    success=true;
                }else{
                    TerminalLogger.logError("incorrect password");
                }
            }else{
                TerminalLogger.logError("User "+userName+ " does not exist");
                String create = TerminalLogger.textfield("Do you want to create an account text?y/n", 50);
                if(create.equals("y")){
                      success= new Register().addNewUser();
                 
                }else if(create.equals("n")){

                }

            }
                        

            }
        return success;

    } 
}