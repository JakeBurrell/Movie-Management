package com.github.jake_burrell.movie_management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Console application used by Community Library in order to manage their Movie DVDs
 * @author Jake Burrell
 */
public class MovieManagement {

    private static final String staffUserName = "staff";
    private static final String staffPassword = "today123";

    private static MemberCollection registeredMembers = new MemberCollection();
    private static MovieCollection movies = new MovieCollection();

    public static void main(String[] args) throws IOException {

        // Hard coded member
        Member hardMember = new Member("Jake", "Burrell");
        String[] hardMemberInfo = {"address", "1234567890", "1234"};
        registeredMembers.registerMember(hardMember);
        hardMember.registerInfo(hardMemberInfo);

        welcomeActions();
    }

    /**
     * Control actions from welcome menu
     * @throws IOException
     */

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
                String[] userCreds = loginForm();
                Member loggedInMember = memberAuthenticate(userCreds);
                if (loggedInMember != null) memberActions(loggedInMember);
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

    /**
     * Control staff actions from staffMenu
     * @throws IOException
     */

    private static void staffActions() throws IOException {
        switch (staffMenu()) {
            case 1:
                // adds movie
                break;
            case 2:
                // removes movie
                break;
            case 3:
                registerMember();
                break;
            case 4:
                memberPhonePrompt();
                break;
            case 0:
                welcomeActions();
                break;
            default:
                System.out.println("Invalid Selection");
                break;
        }
    }

    /**
     *  Control members actions from memberMenu
     * @param loggedInMember Currently, logged in Member
     * @throws IOException
     */

    private static void memberActions(Member loggedInMember) throws IOException {

        switch (memberMenu(loggedInMember)) {
            case 1:
                // Display all movies
                break;
            case 2:
                // Borrow a DVD
                break;
            case 3:
                // Return a movie
                break;
            case 4:
                // List current borrowed movie DVDs
                break;
            case 5:
                // Display top 10 most popular movies
                break;
            case 0:
                welcomeActions();
                break;
            default:
                System.out.println("Invalid Selection");
        }
    }

    /**
     * Displays welcome menu
     * @return WelcomeMenu user selection int
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

        System.out.print(" Please make a selection (1-2, or 0 to exit): ");
        Integer selection = null;
        try {
            selection = checkSelection();
        } catch (InputMismatchException e)  {
            System.out.println("Invalid Selection");
            welcomeActions();
        }
        return selection;

    }

    /**
     * Displays StaffMenu
     * @return staffMenu user selection int
     * @throws IOException
     */
    private static int staffMenu() throws IOException {
        System.out.println("\n" +
                "============Staff Menu==========\n" +
                "1. Add a new movie DVD\n" +
                "2. Remove a movie DVD\n" +
                "3. Register a new member\n" +
                "4. Find a registered member's phone number\n" +
                "0. Return to main menu\n" +
                "================================\n");

        System.out.print(" Please make a selection (1-4, or 0 to return to main menu): ");
        int selection = Integer.MAX_VALUE;
        try {
            selection = checkSelection();
        } catch (InputMismatchException e)  {
            System.out.println("Invalid Selection");
            staffActions();
        }
        return selection;
    }

    /**
     * Displays memberMenu
     * @param loggedInMember Member currently logged in
     * @return MemberMenu user selection
     */
    public static int memberMenu(Member loggedInMember) throws IOException{
        System.out.println("\n" +
                "===========Member Menu==========\n" +
                "1. Display all movies\n" +
                "2. Borrow a movie DVD\n" +
                "3. Return a movie DVD\n" +
                "4. List current borrowed movie DVDs\n" +
                "5. Display top 10 most popular movies\n" +
                "0. Return to main menu\n" +
                "================================\n");

        System.out.print(" Please make a selection (1-5, or 0 to return to main menu): ");

        Integer selection = null;
        try {
            selection = checkSelection();
        } catch (InputMismatchException e)  {
            System.out.println("Invalid Selection");
            memberActions(loggedInMember);
        }
        return selection;
    }

    /**
     * Prompts user to enter name of user who's phone number is required then prints number to screen before returning
     * to staffActions()
     */
    public static void memberPhonePrompt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] user = new String[2];
        Integer phoneNumber = null;
        System.out.print("Please enter member's first name: ");
        user[0] = reader.readLine();
        System.out.print("Please enter member's last name: ");
        user[1] = reader.readLine();
        phoneNumber = registeredMembers.getPhoneNumber(user[1]+user[0]);
        if (phoneNumber != null) {
            System.out.printf("%s %s's number is %d\n",user[0], user[1], phoneNumber);
        } else {
            System.out.println("Invalid User");
        }
        staffActions();
    }


    /**
     * Community Library Login prompt
     * @return user supplied credentials in String array
     * @throws IOException
     */
    private static String[] loginForm() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username: ");
        String username = reader.readLine();
        System.out.print("Enter Password: ");
        String password = reader.readLine();
        String[] returnArray = {username, password};
        return returnArray;
    }


    /**
     * Promotes staff for additional member info then returns them to staffActions().
     * @param newMember A member to add info too.
     * @throws IOException
     */
    public static void registerMemberInfo(Member newMember) throws IOException {
        String[] userData = new String[3];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter member's address: ");
        userData[0] = reader.readLine();

        boolean phoneNumOk = false;
        while (!phoneNumOk) {
            System.out.print("Enter member's phone number: ");
            userData[1] = reader.readLine();
            if (userData[1].matches("\\d{10}")) phoneNumOk = true;
            else System.out.print("Invalid phone number");
        }

        boolean passwordOk = false;
        while (!passwordOk) {
            System.out.print("Enter member's password(4 digits): ");
            userData[2] = reader.readLine();
            if (userData[2].matches("\\d{4}")) passwordOk = true;
            else System.out.println("Invalid password");
        }
        newMember.registerInfo(userData);
        System.out.printf("Successfully added %s %s", newMember.firstName, newMember.lastName);
        staffActions();
    }

    /**
     * Registers member and calls registerMemberInfo unless they already exits. In which case it calls staffActions()
      * @throws IOException
     */
    public static void registerMember() throws IOException {
        String[] username = new String[2];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter member's first name: ");
        username[0] = reader.readLine();
        System.out.print("Enter member's last name: ");
        username[1] = reader.readLine();
        Member newMember = new Member(username);
        if (registeredMembers.registerMember(newMember)) {
            registerMemberInfo(newMember);
        } else {
            System.out.printf("%s %s has already registered.\n", username[0], username[1]);
            staffActions();
        }
    }


    /**
     * Authenticates Community library users
     * @param userCreds String[] of users first name followed by their last
     * @return member if authenticated else returns null
     */
    private static Member memberAuthenticate(String[] userCreds) throws IOException {
        String username = userCreds[0];
        Integer password = null;
        try {
            password = Integer.parseInt(userCreds[1]);
        } catch (NumberFormatException E) {
            System.out.println("Invalid Password");
            welcomeActions();
        }
        if (registeredMembers.checkPassword(username , password)) {
            return registeredMembers.getMember(userCreds[0]);
        } else return null;
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
