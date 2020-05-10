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
    private final MovieCollection borrowedMovies;

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        borrowedMovies = new MovieCollection();
    }

    public Member(String[] username) {
        this.firstName = username[0].trim();
        this.lastName = username[1].trim();
        borrowedMovies = new MovieCollection();
    }

    /**
     * Sets a registered members info
     * @param memberInfo String array containing members info in the from of (address, phoneNumber, password)
     */
    public void registerInfo(String[] memberInfo) {
        this.address = memberInfo[0];
        this.phoneNumber = Integer.parseInt(memberInfo[1]);
        this.password = Integer.parseInt(memberInfo[2]);
    }

    public String getUsername() {
        return lastName + firstName;
    }

    /**
     * Called when member borrows movie. It increments numBorrows of movie and adds borrowedMovie to the members
     * borrowedMovies MovieCollection
     * @param borrowedMovie Movie that is being borrowed
     */
    public boolean memberBorrows(Movie borrowedMovie) {
        if((!this.borrowedMovies.movieExist(borrowedMovie)) && borrowedMovie.movieBorrowable()){
            if (this.borrowedMovies.getNumMovies() >= 10) {
                System.out.println("Too many borrowed movies.");
                return false;
            }
            borrowedMovie.movieBorrow();
            this.borrowedMovies.addMovie(borrowedMovie);
            return true;
        } else return false;
    }

    public void memberReturns(Movie returnedMovie) {
        returnedMovie.movieReturned();
        borrowedMovies.removeMovie(returnedMovie);
    }

    public MovieCollection getBorrowedMovies() {
        return this.borrowedMovies;
    }

    public void displayBorrowed() {
        if (borrowedMovies.getNumMovies() > 0) {
            System.out.println("You have borrowed the following movies: ");
            for (Movie borrowedMovie: borrowedMovies.getMovies()) {
                System.out.println(borrowedMovie.getTitle());
            }
        } else System.out.println("You have no borrowed movies");

    }

}
