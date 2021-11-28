package com.cs4013.Misc;

/**
 * StringUtils
 */
public class StringUtils {

    public static String centerString(String s,int padding){
       return String.format("%-" + padding + "s", String.format("%" + (padding - s.length()) + "s", s));
   
    }
    public static String centerString(String s,int padding,String border){
        return String.format("%-" + padding + "s"+border, String.format(border+"%" + (padding - s.length()) + "s", s));
    
     }
}