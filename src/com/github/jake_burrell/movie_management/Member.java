package com.github.jake_burrell.movie_management;

/**
 * Community Library members
 * @author Jake Burrell
 */

public class Member {

    protected String firstName;
    protected String lastName;
    private String address;
    protected int phoneNumber;
    // four digits
    protected int password;
    private MovieCollection borrowedMovies;

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        borrowedMovies = null;
    }

    public Member(String[] username) {
        this.firstName = username[0];
        this.lastName = username[1];
        borrowedMovies = null;
    }

    public void registerInfo(String[] memberInfo) {
        this.address = memberInfo[0];
        this.phoneNumber = Integer.parseInt(memberInfo[1]);
        this.password = Integer.parseInt(memberInfo[2]);
        borrowedMovies = null;
    }

    public String getUsername() {
        String usrName = lastName + firstName;
        return usrName;
    }

    /**
     * Called when member borrows movie. It increments numBorrows of movie and adds borrowedMovie to the members
     * borrowedMovies MovieCollection
     * @param borrowedMovie Movie that is being borrowed
     */
    public boolean memberBorrows(Movie borrowedMovie) {
        if(borrowedMovie.movieBorrowed()) {
            this.borrowedMovies.addMovie(borrowedMovie);
            return true;
        } else return false;
    }

    public boolean memberReturns(Movie returnedMovie) {
        return true;
    }

}
