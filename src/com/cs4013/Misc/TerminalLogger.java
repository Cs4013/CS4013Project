package com.cs4013.Misc;

import java.util.Scanner;

public class TerminalLogger {
    public static void logln(Object msg){
        System.out.println(msg);
    }
    public static void logln(Object msg,String color){
        System.out.println(color+msg+TerminalColor.ANSI_RESET);
    }
    public static void log(Object msg){
        System.out.print(msg);
    }

    public static void log(Object msg,String color){
        System.out.println(color+msg+TerminalColor.ANSI_RESET);
    }

    public static void logError(Object msg){
        System.out.println("\n"+TerminalColor.ANSI_RED+"Error: "+msg+TerminalColor.ANSI_RESET+"\n");
    }
    
    public static String textfield(String prompt,int width){
        String input = "";
        logln("");
        logln("-".repeat(width));
        Scanner scanner = new Scanner(System.in);
        log(String.format("» %s:\t",prompt));
        input = scanner.nextLine();
        logln("-".repeat(width));
        return input;
    }
}
