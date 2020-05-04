package com.github.jake_burrell.movie_management;

import java.time.Duration;
import java.util.Date;

/**
 * Community Library Movie
 * @author Jake Burrell
 */

public class Movie implements Comparable<Movie> {

    @Override
    public int compareTo(Movie otherMovie) {
        int numBorrows = this.numBorrows;
        if (numBorrows > otherMovie.numBorrows) return 1;
        else if (numBorrows < otherMovie.numBorrows) return -1;
        else return 0;
    }

    enum Classification {General, ParentalGuidance, Mature, MatureAccompanied };
    enum Genre {Drama, Adventure, Family, SciFi, Comedy, Animation, Thriller, Other};

    private String title;
    private String[] starring;
    private String director;
    private Duration duration;
    private Genre[] genres;
    private Classification classification;
    private Date releaseDate;
    private int copiesAvailable;
    private int numBorrows;

    public boolean movieBorrowed() {
        if(copiesAvailable > 0) {
            numBorrows++;
            copiesAvailable--;
            return true;
        } else return false;
    }

    public void movieReturned() {
        copiesAvailable++;
    }


}
