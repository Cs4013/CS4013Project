package com.cs4013.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cs4013.Admin.RoomManager;
import com.cs4013.Interface.IPrompt;

public class CustomerPrompt implements IPrompt {

    public boolean keepGoing = true;
    String currentPath = "/";
    public Map<String,String> definition =new HashMap<>();
    public Map<String,ArrayList<String>> navStack = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ArrayList<String> prevPath = new ArrayList<String>();
    int width = 50;
    private RoomManager roomManager = new RoomManager();
    
    @Override
    public void  display(String command){

    }
    @Override
    public void execute(){
        while(keepGoing)
        {
            if(!keepGoing){

            }
        }
    }
}

