package com.cs4013.Model;

import java.util.ArrayList;

import com.cs4013.Misc.FileManager;
import com.cs4013.Misc.FileParser;

public class User {
    public String username;
    public String password;

    public String userId;

    public int wallet;

    public ArrayList<String> reservations;

    public User(String userId){
        this.userId= userId;
        this.username="";
        this.wallet=0;
        this.reservations=new ArrayList<>();
    }

    public ArrayList<Booking> getReservations(String approved){
        ArrayList<Booking> bookings = new ArrayList<>();
        if(approved.equals("y")){
            bookings = new FileParser().getReservation(false);
        }else{
            bookings = new FileParser().getReservation(true);
        }
        ArrayList<Booking>myRes = new ArrayList<>();
        for(Booking b : bookings){
            if(this.reservations.contains(b.getBookingId())){
                myRes.add(b);
            }
        }
        return bookings;
    }

    public void cancelReservation(){

    }

    public void checkIn(){

    }

    public void bookRoom(){

    }

    public void updateUser(){
        ArrayList<User> users = new FileParser().getUsers();

        System.out.println("->"+users);
        ArrayList<String> us = new ArrayList<>();
        try {
          FileManager fm =  new FileManager("customers.csv");
          fm.overwrite("");
            for(User u : users){
                if(u.userId.equals(this.userId)){
                    u.wallet = this.wallet;
                    u.reservations = this.reservations;
                    u.username = this.username;
                }
                fm.write(u.toString());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
    public String toString(){
        String bks="";
        for (String b : reservations){
            bks+=b+"_";
        }
        String B = "";
        if(!bks.equals("")){
            B = bks.substring(0,bks.length()-1);
        }
        return userId+","+username+","+password+","+wallet+","+B;
    }
}
