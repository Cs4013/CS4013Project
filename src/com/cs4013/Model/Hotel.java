package com.cs4013.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Hotel {
    private String hotelId;
    private String ratings;
    private ArrayList<String> rooms;
    private String name;
    private ArrayList<HotelAccount> account;

    public Hotel() {
        this.hotelId = UUID.randomUUID().toString();
        this.ratings = "3-Star";
        this.rooms = new ArrayList<>();
        this.name = "Unknown";
        this.account = new ArrayList<>();
    }


    public Hotel(String ratings,String name) {
        this.hotelId = UUID.randomUUID().toString();;
        this.ratings = ratings;
        this.rooms = new ArrayList<>();
        this.name = name;
        this.account = new ArrayList<>();

    }

    public Hotel(String ratings, ArrayList<String> rooms, String name) {
        this.hotelId = UUID.randomUUID().toString();;
        this.ratings = ratings;
        this.rooms = rooms;
        this.name = name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public ArrayList<HotelAccount> getAccount() {
        return account;
    }

    public void setAccount(ArrayList<HotelAccount> account) {
        this.account = account;
    }

    public String getHotelId() {
        return this.hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRatings() {
        return this.ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public ArrayList<String> getRooms() {
        return this.rooms;
    }

    public void setRooms(ArrayList<String> rooms) {
        this.rooms = rooms;
    }

    //create new variable
    //amount paid

    public String toString(){
        String bks="";
        for (String b : rooms){
            bks+=b+"_";
        }
        String B = "";
        if(!bks.equals("")){
            B = bks.substring(0,bks.length()-1);
        }

        String acts="";
        for (HotelAccount b : account){
            acts+=b.toString(">")+"_";
        }
        String A = "";
        if(!acts.equals("")){
            A = acts.substring(0,acts.length()-1);
        }
        return this.hotelId+","+this.ratings+","+B+","+this.name+","+A;
    }

}