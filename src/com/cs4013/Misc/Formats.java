package com.cs4013.Misc;

public class Formats {
    public static boolean isValidDate(String date) {
        boolean valid = false;
        String d = "([0-2][0-9])|(3[0-1])";
        String m = "([0][0-9])|(1[0-2])";
        String y = "([1-2][0-9][0-9][0-9])";
        String regex = d+"/"+m+"/"+y;
        if (date.matches(regex)){
            valid=true;
        }

        return valid;

    }

}
