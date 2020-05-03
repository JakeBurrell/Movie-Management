package com.github.jake_burrell.movie_management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Console application used by Community Library in order to manage their Movie DVDs
 * @author Jake Burrell
 */
public class MovieManagement {

    private static final String staffUserName = "staff";
    private static final String staffPassword = "today123";

    public static void main(String args[]) throws IOException {
         welcomeActions();
    }

    public static void welcomeActions() throws IOException {
        switch (welcomeMenu()) {
            case 1:
                String[] passArray = {staffUserName,staffPassword};
                boolean correctPasswd = Arrays.equals(passArray, loginForm());
                if (correctPasswd) {
                    staffActions();
                } else {
                    System.out.println("Invalid Credentials");
                    welcomeActions();
                }
                break;
            case 2:
                if (memberAuthenticate()) memberMenu();
                else {
                    System.out.println("Invalid Credentials");
                    welcomeActions();
                }
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid selection");
                break;
        }
    }

    private static void staffActions() throws IOException {
        switch (staffMenu()) {
            case 1:
                // adds movie
                break;
            case 2:
                // removes movie
                break;
            case 3:
                // registers a new member
                break;
            case 4:
                // finds a registered member's phone number
                break;
            case 0:
                welcomeActions();
                break;
            default:
                System.out.println("Invalid Selection");
                break;
        }
    }

    private static void memberActions() {
        switch (memberMenu()) {
            case 1:
                // Display all movies
                break;
            case 2:
                // Borrow a movie
        }
    }

    /**
     * Displays welcome menu
     * @return welcomeMenu selection int
     * @throws IOException
     */

    private static int welcomeMenu() throws IOException {
        System.out.println("\n" +
                "Welcome to the Community Library\n" +
                "============Main Menu===========\n" +
                "1. Staff Login\n" +
                "2. Member Login\n" +
                "0. Exit\n " +
                "================================\n");

        System.out.printf(" Please make a selection (1-2, or 0 to exit): ");

        return checkSelection();
    }

    /**
     * Displays StaffMenu
     * @return staffMenu selection int
     */

    private static int staffMenu() {
        System.out.println("\n" +
                "============Staff Menu==========\n" +
                "1. Add a new movie DVD\n" +
                "2. Remove a movie DVD\n" +
                "3. Register a new member\n" +
                "4. Find a registered member's phone number\n" +
                "0. Return to main menu\n" +
                "================================\n");

        System.out.printf(" Please make a selection (1-4, or 0 to return to main menu): ");

        return checkSelection();
    }

    /**
     * Displays memberMenu
     * @return memberMenu selection int
     */

    public static int memberMenu() {
        System.out.println("\n" +
                "===========Member Menu==========\n" +
                "1. Display all movies\n" +
                "2. Borrow a movie DVD\n" +
                "3. Return a movie DVD\n" +
                "4. List current borrowed movie DVDs\n" +
                "5. Display top 10 most popular movies\n" +
                "0. Return to main menu\n");

        System.out.printf(" Please make a selection (1-5, or 0 to return to main menu): ");

        return checkSelection();
    }

    /**
     * Community Library Login Form
     * @return user supplied credentials in String array
     * @throws IOException
     */

    private static String[] loginForm() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Enter username: ");
        String username = reader.readLine();
        System.out.printf("Enter Password: ");
        String password = reader.readLine();
        String[] returnArray = {username, password};
        return returnArray;
    }


    /**
     * Authenticates Community library users
     * @return member if authenticated else returns null
     */
    private static Member memberAuthenticate() throws IOException {
        String[] userCreds = loginForm();
        Member aMember = new Member();
        return ;
    }

    /**
     * Scans for user selection
     * @return selection int
     */
    private static int checkSelection() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        int input = scanner.nextInt();
        return input;
    }

}
