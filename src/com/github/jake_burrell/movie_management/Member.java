package com.github.jake_burrell.movie_management;

/**
 * Community Library members
 * @author Jake Burrell
 */

public class Member {

    private String firstName;
    private String lastName;
    private String address;
    private int phoneNumber;
    // four digits
    private int password;
    private MovieCollection borrowedMovies;

    public String getUsername() {
        String usrName = lastName + firstName;
        return usrName;
    }

}
