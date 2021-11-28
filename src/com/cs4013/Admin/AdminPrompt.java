package com.cs4013.Admin;

import com.cs4013.Interface.IPrompt;
import com.cs4013.Misc.TerminalLogger;

import java.util.*;

public class AdminPrompt implements IPrompt {

    public boolean keepGoing = true;
    String currentPath = "/";
    public Map<String,String> definition =new HashMap<>();
    public Map<String,ArrayList<String>> navStack = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ArrayList<String> prevPath = new ArrayList<String>();
    int width = 50;


    public AdminPrompt(){
        populateNavStack();
    }
    private void populateNavStack(){

        definition.put("MR","Enter MR to Modifify Rooms");
        definition.put("MH", "Enter MH to Modify Hotels"); 
        definition.put("AH","Enter AH for data analysis for hotel" );

        definition.put("AR","Enter AR to Add Rooms");
        definition.put("ER","Enter ER to Edit Rooms");
        definition.put("Dr","Enter DR to Delete rooms");
        definition.put("VR","Enter to View Rooms");

        definition.put("","");

        ArrayList<String> init = new ArrayList<>();
        init.add("MR");
        init.add("MH");
        init.add("AH");
        navStack.put("/",init);

         init = new ArrayList<>();
         init.add("AR");
         init.add("ER");
         init.add("DR");
         init.add("VR");
         navStack.put("MR",init);
         //ah eh dh

         init = new ArrayList<>();

        
         navStack.put("AR",init);

         

         
    }
    public void printDefiniton(String command){
        for(String s : navStack.get(command)){
           TerminalLogger.logln(definition.get(s));
        }
            
            
    }
    public void addRoom(){

    }
           

     


    @Override
    public void  display(String command){
        switch(command){
            case "Ar": 
            addRoom();
            break;
            default:
            printDefiniton(command);
            String input = TerminalLogger.textfield("Enter Here", width);
            if(input.equals("back") || (input.equals("exit"))){
                if(input.equals("exit")){
                    keepGoing = false;
                }
                else{
                    if(prevPath.size() > 0){
                        currentPath = prevPath.remove(prevPath.size()-1);
                    }
                    else{
                        keepGoing = false;
                    }
                }
               
            }
            else{
                if(navStack.get(currentPath).contains(input)){

                    prevPath.add(currentPath);
                    currentPath = command;
    
                }
                else{
                    TerminalLogger.logError("Command " + input + " not recognized! ");
                }
            }
            
            break;
        }
     }
        
        
    public void execute(){
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
    
    
