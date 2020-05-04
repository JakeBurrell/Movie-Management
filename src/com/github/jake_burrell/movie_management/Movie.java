package com.github.jake_burrell.movie_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Date;

/**
 * Community Library Movie
 * @author Jake Burrell
 */

public class Movie implements Comparable<Movie> {


    enum Classification {General, ParentalGuidance, Mature, MatureAccompanied };
    enum Genre {Drama, Adventure, Family, SciFi, Comedy, Animation, Thriller, Other};

    private String title;
    private String[] starring;
    private String director;
    private Duration duration;
    private Genre genres;
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

    @Override
    public int compareTo(Movie otherMovie) {
        int numBorrows = this.numBorrows;
        if (numBorrows > otherMovie.numBorrows) return 1;
        else if (numBorrows < otherMovie.numBorrows) return -1;
        else return 0;
    }

    public void setStarring(String[] starring) {
        this.starring = starring;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenres(Genre genres) {
        this.genres = genres;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}
