package com.cs4013;

/**
 * Main
 * This class is the main entry point for the application
 * The Application provides several options
 * <br/>
 * <b> Command line Flags</b>
 * <ul>
 *  <li> <b>-a</b> Admin. A password is required to access admin functions </li>
 *  <li> <b>-g</b> Guest. A username and password must be entered to access personal reservations and more </li>
 * </ul>
 * <br/>
 * <b>Admin</b>
 * <br/>
 * Prompt to enter <b>password</b>, which has been outlined in stored in the file AdminPassword.txt
 * <ul>
 *  <li> Enter <b>AR</b> to add room</li>
 *  <li> Enter <b>AH</b> to add hotel</li>
 *  <li> Enter <b>A</b> for data analysis for hotel</li>
 * </ul>
 * <br/>
 * <b>Guest</b>
 * <br/>
 * Prompt to enter <b>username</b> and <b>password</b>, which has been outlined in stored in the file AdminPassword.txt
 * <ul>
 *  <li> Enter <b>S</b> to search a hotel based on the check-in date and a check-out date criteria </li>
 *  <li> Enter <b>V</b> view all reservations made</li>
 * </ul>
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        //args[0] = -g / -a / --

        boolean admin = false;


        if (args.length > 0) {
            if (args[0].equals("-a")) {
                admin = true;
            } else if (!args[0].equals("-g")) {
                System.out.println("Sorry command unrecognised, enter -a for admin or -g for customer");
                System.exit(0);
            }
        }

        if (admin) {
            runAsAdmin();
        } else {
            runAsCustomer();
        }

    }

    public static void runAsCustomer() {
        System.out.println("Running as customer");
    }

    public static void runAsAdmin() {
        System.out.println("Running as admin");

    }
}