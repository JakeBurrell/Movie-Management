package com.github.jake_burrell.movie_management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Console application used by Community Library in order to manage their Movie DVDs
 * @author Jake Burrell
 */

public class MovieManagement {

    private static final String staffUserName = "staff";
    private static final String staffPassword = "today123";

    private static final MemberCollection registeredMembers = new MemberCollection();
    private static final MovieCollection movies = new MovieCollection();

    public static void main(String[] args) {

        // Testing --------------------------------------------------------------------------------------------------
        Member testMember1 = new Member("Jake", "Burrell");
        Member testMember = new Member("John", "Smith");
        String[] hardMemberInfo = {"address", "1234567890", "1234"};
        registeredMembers.registerMember(testMember1);
        registeredMembers.registerMember(testMember);
        testMember1.registerInfo(hardMemberInfo);
        testMember.registerInfo(hardMemberInfo);
//        Movie[] testMovies = Movie.hardCoddedMovies();
//        for (Movie movie: testMovies) {
//            movies.addMovie(movie);
//        }
        //------------------------------------------------------------------------------------------------------------

        try {
            welcomeActions();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    ----------------------------------------------------Actions--------------------------------------------------------
     */

    /**
     * Control actions from welcome menu
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
                System.out.println("username: LastnameFirstname");
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
     */

    private static void staffActions() throws IOException {
        switch (staffMenu()) {
            case 1:
                // adds movie
                addMoviePrompt();
                break;
            case 2:
                // removes movie
                deleteMoviePrompt();
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
     */

    private static void memberActions(Member loggedInMember) throws IOException {

        switch (memberMenu(loggedInMember)) {
            case 1:
                // Display all movies
                movies.displayMovieInfo();
                memberActions(loggedInMember);
                break;
            case 2:
                // Borrow a DVD
                borrowingPrompt(loggedInMember);
                break;
            case 3:
                // Return a movie
                returningPrompt(loggedInMember);
                break;
            case 4:
                // List current borrowed movie DVDs
                loggedInMember.displayBorrowed();
                memberActions(loggedInMember);
                break;
            case 5:
                // Display top 10 most popular movies
                topTenMovies(loggedInMember);
                break;
            case 0:
                welcomeActions();
                break;
            default:
                System.out.println("Invalid Selection");
                break;
        }
    }

    /*
    ----------------------------------------------------Menu's--------------------------------------------------------
     */

    /**
     * Displays welcome menu
     * @return WelcomeMenu user selection int
     */
    private static Integer welcomeMenu() throws IOException {
        System.out.println("\n" +
                "Welcome to the Community Library\n" +
                "============Main Menu===========\n" +
                "1. Staff Login\n" +
                "2. Member Login\n" +
                "0. Exit\n " +
                "================================\n");

        Integer selection = checkSelection( 2);

        if (selection == null) welcomeActions();
        return selection;
    }

    /**
     * Displays StaffMenu
     * @return staffMenu user selection int
     */
    private static Integer staffMenu() throws IOException {
        System.out.println("\n" +
                "============Staff Menu==========\n" +
                "1. Add a new movie DVD\n" +
                "2. Remove a movie DVD\n" +
                "3. Register a new member\n" +
                "4. Find a registered member's phone number\n" +
                "0. Return to main menu\n" +
                "================================\n");

        Integer selection = checkSelection( 4);
        if (selection == null) staffActions();
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

        Integer selection = checkSelection( 5);
        if (selection == null) {
            memberActions(loggedInMember);
            selection = 0;
        }

        return selection;
    }

    /*
    ------------------------------------------------Staff Related Methods-----------------------------------------------
     */

    /**
     * Registers member and calls registerMemberInfo unless they already exits. In which case
     * it calls staffActions()
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
     * Promotes staff for additional member info then returns them to staffActions().
     * @param newMember A member to add info too.
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
            else System.out.print("Invalid phone number\n");
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
     * Checks if movie already exists if so prompts for number of copies else calls addMovieInfo
     */
    public static void addMoviePrompt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the movie title: ");
        String movieName = reader.readLine().trim();
        Movie newMovie = new Movie(movieName);
        if (!movies.addMovie(newMovie)) {
            System.out.print("Enter the number of copies you would like to add: ");
            Movie existingMovie = movies.retrieveMovie(movieName);
            existingMovie.addCopies(returnDigit());
            System.out.printf("There are now %S copies of %s\n",existingMovie.getCopiesAvailable(), existingMovie.getTitle());
            staffActions();
        } else {
            addMovieInfo(newMovie);
        }
    }

    /**
     * Adds movie info to a particular Movie
     * @param movie Movie to add info to
     */
    public static void addMovieInfo(Movie movie) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the staring actors(s): ");
        String starringStr = reader.readLine();
        movie.setStarring(starringStr.split(","));
        System.out.print("Enter the director(s): ");
        String directorsStr = reader.readLine();
        movie.setDirectors(directorsStr.split(","));
        movie.setGenre();
        movie.setClassification();
        movie.setDuration();
        movie.setReleaseDate();
        movie.setCopiesAvailable();

        System.out.printf("\n%s has been added.\n", movie.getTitle());
        staffActions();
    }

    /**
     * Prompts user to enter name of user who's phone number is required then prints number to screen
     * before returning to staffActions()
     */
    public static void memberPhonePrompt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] user = new String[2];
        Integer phoneNumber;
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
     * Prompts user to enter the name of the movie they wish to delete, checks if its a valid movie and then deletes it
     */
    public static void deleteMoviePrompt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of the movie you wish to delete: ");
        String movieName = reader.readLine();
        Movie movie = new Movie(movieName);
        if (movies.removeMovie(movie)) {
            System.out.printf("The movie %s was deleted.\n", movieName);
        } else System.out.println("Invalid movie name");
        staffActions();
    }

    /*
    ---------------------------------------------Member Related Method-------------------------------------------------
     */

    /**
     * Prints the top 10 most frequently borrowed movies
     * @param loggedInMember Member currently logged in
     */
    public static void topTenMovies(Member loggedInMember) throws IOException {
        System.out.println("Top 10 Most Frequently Borrowed Movies: ");
        MovieCollection.MoviePair[] topTenMovies = movies.top10Borrowed();
        for (MovieCollection.MoviePair movie : topTenMovies) {
            if (movie == null) break;
           // System.out.println(movies.retrieveMovie(movieName).toString());
            System.out.printf("%s borrowed %s times.\n", movie.getMovieTitle(), movie.getNumBorrows() );
        }
        memberActions(loggedInMember);
    }

    /**
     * Return Movie prompt which removes provided movie from members borrowedMovie movieCollection by calling
     * the membersReturned method which intern will increment the num borrows of the particular movie
     * @param loggedInMember Currently logged in member
     */
    public static void returningPrompt(Member loggedInMember) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the movie you wish to return: ");
        String movieName = reader.readLine();
        Movie returnedMovie = loggedInMember.getBorrowedMovies().retrieveMovie(movieName);
        if (returnedMovie != null) {
            loggedInMember.memberReturns(returnedMovie);
            System.out.printf("You returned the movie %s\n", returnedMovie.getTitle());
        } else System.out.println("Invalid movie name");
        memberActions(loggedInMember);
    }

    /**
     * Prompts user to enter the movie they wish to
     * @param loggedInMember Currently logged in member
     */
    public static void borrowingPrompt(Member loggedInMember) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter the movie you wish to borrow: ");
        String movieName = reader.readLine();
        Movie borrowedMovie = movies.retrieveMovie(movieName);
        if (borrowedMovie != null) {
            if (loggedInMember.memberBorrows(borrowedMovie)){
                System.out.printf("You have borrowed the movie %s\n", borrowedMovie.getTitle());
            } else System.out.println("Movie unavailable");
        } else System.out.println("Invalid movie name");
        memberActions(loggedInMember);
    }

    /**
     * Authenticates Community library users
     * @param userCreds String[] of users first name followed by their last
     * @return member if authenticated else returns null
     */
    private static Member memberAuthenticate(String[] userCreds) throws IOException {
        String username = userCreds[0];
        int password = 0;
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


    /*
    ------------------------------------------------Other Methods-------------------------------------------------------
     */

    /**
     * Prints prompt and scans for user selection
     * @return Integer relating to the users selection
     */
    private static Integer checkSelection(int endingSelection) {
        Integer input = null;
        while(input == null) {
            System.out.printf(" Please make a selection (0-%d, or 0 to return to main menu): ",
                    endingSelection);
            input = returnDigit();
        }

        if ((input < 0) || (input > endingSelection)) {
            input = null;
            System.out.println("Invalid Selection");
            checkSelection(endingSelection);
        }

        return input;
    }

    /**
     * Returns digit from the user
     * @return Integer relating to the digit selected by the user
     */
    protected static Integer returnDigit() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        Integer input = null;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e)  {
            System.out.println("Invalid number");
        }
        return input;
    }

    /**
     * Community Library Login prompt
     * @return user supplied credentials in String array
     */
    private static String[] loginForm() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username: ");
        String username = reader.readLine();
        System.out.print("Enter Password: ");
        String password = reader.readLine();
        return new String[]{username, password};
    }

}
