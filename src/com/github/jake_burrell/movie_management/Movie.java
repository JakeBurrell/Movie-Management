package com.github.jake_burrell.movie_management;

import java.time.Duration;
import java.util.Date;

/**
 * Community Library Movie
 * @author Jake Burrell
 */

public class Movie {

    enum Classification {General, ParentalGuidance, Mature, MatureAccompanied };
    enum Genre {Drama, Adventure, Family, SciFi, Comedy, Animation, Thriller, Other};

    private String title;
    private String[] starring;
    private String director;
    private Duration duration;
    private Genre[] genres;
    private Classification classification;
    private Date releaseDate;
    private int numDVDs;


}
