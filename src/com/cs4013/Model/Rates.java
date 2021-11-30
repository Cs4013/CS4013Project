package com.cs4013.Model;

public class Rates{
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int saturday;
    private int sunday;
    public Rates(int sunday, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday,){
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }
    public Rates(){
        this.monday = 0;
        this.tuesday = 0;
        this.wednesday = 0;
        this.thursday = 0;
        this.friday = 0;
        this.saturday = 0;
        this.sunday = 0;
    }
    public int getMonday(){
        return monday;
    }
    public void setMonday(int monday){
        this.monday = monday;
    }
    public int getTuesday(){
        return tuesday;
    }
    public void setTuesday(int tuesday){
        this.tuesday = tuesday;
    }
    public int getWednesday(){
        return wednesday;
    }
    public void setWednesday(int wednesday){
        this.wednesday = wednesday;
    }
    public int getThursday(){
        return thursday;
    }
    public void setThursday(int thursday){
        this.thursday = thursday;
    }
    public int getFriday(){
        return friday;
    }
    public void setFriday(int friday){
        this.friday = friday;
    }
    public int getSaturday(){
        return saturday;
    }
    public void setSaturday(int saturday){
        this.saturday = saturday;
    }
    public int getSunday(){
        return sunday;
    }
    public void setSunday(int sunday){
        this.sunday = sunday;
    }
    public String toString(){
        return monday+","+tuesday+","+wednesday+","+thursday+","+friday+","+saturday+","+sunday;
    }
    public String toString(String sep){
        return monday+sep+tuesday+sep+wednesday+sep+thursday+sep+friday+sep+saturday+sep+sunday;
    }
}