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
        borrowedMovies = new MovieCollection();
    }

    public Member(String[] username) {
        this.firstName = username[0];
        this.lastName = username[1];
        borrowedMovies = new MovieCollection();
    }

    public void registerInfo(String[] memberInfo) {
        this.address = memberInfo[0];
        this.phoneNumber = Integer.parseInt(memberInfo[1]);
        this.password = Integer.parseInt(memberInfo[2]);
        borrowedMovies = new MovieCollection();
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
        if(borrowedMovie.movieBorrowed() && (!this.borrowedMovies.movieExist(borrowedMovie))) {
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
