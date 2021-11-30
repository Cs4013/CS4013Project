package com.cs4013.Misc;

import com.cs4013.Model.*;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FileParser {
    FileManager io = new FileManager("");
    public FileParser(){

    }
    public ArrayList<User> getUsers(){
        io = new FileManager("customers.csv");
        ArrayList<User> users = new ArrayList<>();
        try{
            ArrayList<ArrayList<String>> us = io.readCsv();
            for(ArrayList<String> s : us){
              User user = new User(s.get(0));
              user.password = s.get(2);
              user.username=s.get(1);
              user.wallet=Integer.parseInt(s.get(3));
              if(us.size() > 4){
                  user.reservations= new ArrayList<>(Arrays.asList(s.get(4).split("_")));
              }
              users.add(user);
            }
        }catch(IOException e){

        }
        return users;
    }
    public ArrayList<Room> getRooms(){
        io = new FileManager("rooms.csv");
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            ArrayList<ArrayList<String>> rms = io.readCsv();
            for(ArrayList<String> r : rms){
                String roomType = r.get(2);
                String hotelId = r.get(1);
                Room room;
                switch(roomType){
                    case "Quad" :
                        room = new QuadRoom(hotelId);
                        break;
                    case "Triple" :
                        room = new TripleRoom(hotelId);
                        break;
                    case "Double":
                        room = new DoubleRoom(hotelId);
                        break;
                    default:
                        room = new SingleRoom(hotelId);
                        break;
                }
                room.setRoomId(r.get(0));
                room.setType(roomType);
                room.setHotelId(hotelId);
                room.setMinOccupancy(Integer.parseInt(r.get(4)));
                room.setMaxOccupancy(Integer.parseInt(r.get(5)));
                String[] rates = r.get(3) .split("_");
                ArrayList<Integer> rts = new ArrayList<>();
                for(String s : rates){
                    rts.add(Integer.parseInt(s));
                }
                room.setRate(new Rates(rts.get(0),rts.get(1),rts.get(2),rts.get(3),rts.get(4),rts.get(5),rts.get(6)));
                if(r.size() > 6){
                    String[] bks = r.get(6) .split("_");
                    room.setBookings( new ArrayList<String>(Arrays.asList(bks)));

                }
                rooms.add(room);
            }
        }catch(IOException e){

        }
        return rooms;
    }
    public ArrayList<Hotel> getHotels(){
        ArrayList<Hotel> hotels = new ArrayList<>();

        io = new FileManager("hotels.csv");
        try{
            ArrayList<ArrayList<String>> rms = io.readCsv();
            for(ArrayList<String> r : rms){
                Hotel hotel = new Hotel();
                hotel.setHotelId(r.get(0));
                hotel.setRatings(r.get(1));
                //System.out.println("######");
                ArrayList<String> rm = new ArrayList<>(Arrays.asList(r.get(2).split("_")));
                if(rm.size()<1){
                    if(!r.get(2).equals(""))
                    rm.add(r.get(2));
                }

                hotel.setRooms(rm);
                //System.out.println(hotel.getRooms());
                hotel.setName(r.get(3));
                ArrayList<HotelAccount> accounts = new ArrayList<>();
                if(r.size() > 4){
                    String[] ac = r.get(4) .split("_");


                    for(String s : ac){
                        String[] acc = r.get(4) .split(">");
                        HotelAccount hotelAccount = new HotelAccount();
                        hotelAccount.setHotelId(acc[0]);
                        hotelAccount.setUserId(acc[1]);
                        hotelAccount.setAmountPayed(Integer.parseInt((acc[0])));
                        hotelAccount.setDatePayed(Long.parseLong(acc[0]));
                        accounts.add(hotelAccount);
                    }

                }
                hotel.setAccount(accounts);
                hotels.add(hotel);
            }
        }catch(IOException e){

        }
        return hotels;
    }
    public ArrayList<Booking> getReservation(){
        ArrayList<Booking> bookings = new ArrayList<>();

        io = new FileManager("bookings.csv");
        try{
            ArrayList<ArrayList<String>> rms = io.readCsv();
            for(ArrayList<String> r : rms){
                Booking booking = new Booking(r.get(2),Long.parseLong(r.get(4)),Long.parseLong(r.get(5)));
              booking.setBookingId(r.get(0));
              booking.setUserId(r.get(1));
              booking.setHotelId(r.get(3));
              bookings.add(booking);

            }
        }catch(IOException e){

        }

        return bookings;

    }
}
