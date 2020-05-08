package com.github.jake_burrell.movie_management;

import com.github.jake_burrell.movie_management.models.BinarySearchTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Year;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static com.github.jake_burrell.movie_management.MovieManagement.returnDigit;

/**
 * Community Library Movie
 * @author Jake Burrell
 */

public class Movie implements Comparable<Movie> {

    //Properties
    private String title;
    private String[] starring;
    private String[] directors;
    private Duration duration;
    private Genre genre;
    private Classification classification;
    private int releaseDate;
    private int copiesAvailable;
    private int numBorrows;

    /**
     * Movie Classifications overriding the toString method.
     */
    enum Classification {General {
        @Override
        public String toString() {
            return "General (G)";
        }
    }, ParentalGuidance {
        @Override
        public String toString() {
            return "Parental Guidance (PG)";
        }
    }, Mature {
        @Override
        public String toString() {
            return "Mature (M15+)";
        }
    }, MatureAccompanied {
        @Override
        public String toString() {
            return "Mature Accompanied (MA15+)";
        }
    } }


    /**
     * Movie genres overriding the toString method where needed
     */
    enum Genre {Drama, Adventure, Family, Action, SciFi {
        @Override
        public String toString() {
            return "Sci-Fi";
        }
    }, Comedy, Animation, Thriller, Other}

    // Constructor
    public Movie(String title) {
        this.title = title;
        numBorrows = 0;
    }

    //Getters
    public String getTitle() {
        return this.title;
    }

    /**
     * Allows Movies to be compared based on their title
     * @param otherMovie Movie that this instance is compared to
     * @return 1 if this movie is compared to be more then, -1 less than and 0 if equal
     */
    @Override
    public int compareTo(Movie otherMovie) {
        return this.title.compareTo(otherMovie.title);
    }

    /**
     * Auto-Generated override from template
     * Allows movies with the same title to be considered equal to one and other.
     * @param o Movie to compare if equals to
     * @return True if equals else returns false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public int getNumBorrows() {
        return numBorrows;
    }

    public Object getCopiesAvailable() {
        return this.copiesAvailable;
    }

    /**
     * Checks if their a copies available of particular movie. If so increments numBorrows and decrements
     * copiesAvailable
     * @return Returns true if and only if their where copies avalible
     */
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStarring(String[] actors) {
        this.starring = actors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public void addCopies(int numCopies) {
        this.copiesAvailable += numCopies;
    }

    /**
     * Prompts user to select genre and then sets it.
     */
    public void setGenre() {
        System.out.print("\n" +
                "Select the genre:\n" +
                "1. Drama\n" +
                "2. Adventure\n" +
                "3. Family\n" +
                "4. Action\n" +
                "5. Sci-Fi\n" +
                "6. Comedy\n" +
                "7. Animation\n" +
                "8. Thriller\n" +
                "9. Other\n" +
                "Make selection(1-9): ");
        Integer selection = returnDigit();
        if ((selection == null) || (selection < 1 || selection > 9)) {
            System.out.println("Invalid Genre");
            this.setGenre();
        } else {
            switch (selection) {
                case 1:
                    this.genre = Genre.Drama;
                    break;
                case 2:
                    this.genre = Genre.Adventure;
                    break;
                case 3:
                    this.genre = Genre.Family;
                    break;
                case 4:
                    this.genre = Genre.Action;
                    break;
                case 5:
                    this.genre = Genre.SciFi;
                    break;
                case 6:
                    this.genre = Genre.Comedy;
                    break;
                case 7:
                    this.genre = Genre.Animation;
                    break;
                case 8:
                    this.genre = Genre.Thriller;
                    break;
                case 9:
                    this.genre = Genre.Other;
            }
        }
    }

    /**
     * Prompts user to provide classification and then sets it.
     */
    public void setClassification() {
        System.out.print("\n" +
                "Select the classification: \n" +
                "1. General (G)\n" +
                "2. Parental Guidance (PG)\n" +
                "3. Mature (M15+)\n" +
                "4. Mature Accompanied (MA15+)\n" +
                "Make Selection(1-4): ");
        Integer selection = returnDigit();
        if ((selection == null) || (selection < 1 || selection > 4)) {
            System.out.println("Invalid Genre");
            this.setClassification();
        } else {
            switch (selection) {
                case 1:
                    this.classification = Classification.General;
                    break;
                case 2:
                    this.classification = Classification.ParentalGuidance;
                    break;
                case 3:
                    this.classification = Classification.Mature;
                    break;
                case 4:
                    this.classification = Classification.MatureAccompanied;
                    break;
            }
        }
    }

    /**
     * Prompts user to enter movie duration in minutes and then sets it
     */
    public void setDuration() {
        System.out.print("Enter the duration (minutes): ");
        Integer minutes = returnDigit();
        if (minutes == null) setDuration();
        else if (minutes <= 0) {
            System.out.println("Duration must be positive.");
            setDuration();
        } else this.duration = Duration.ofMinutes(minutes);
    }

    /**
     * Prompts user to enter movie releaseDate then sets it.
     */
    public void setReleaseDate() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the release date (year): ");
        String yearStr = reader.readLine();
        if (yearStr.matches("\\d{4}")) this.releaseDate = Integer.parseInt(yearStr);
        else {
            System.out.println("Invalid year");
            setReleaseDate();
        }
    }

    /**
     * Prompts user to set copies and then sets it
     */
    public void setCopiesAvailable() {
        System.out.print("Enter the number of copies available: ");
        Integer copies = returnDigit();
        if (copies == null) setCopiesAvailable();
        else if (copies >= 0) this.copiesAvailable = copies;
        else {
            System.out.println("Copies must be positive");
            setCopiesAvailable();
        }
    }

    /**
     * Returns a string with all the movie information
     * @return Formatted with movie information
     */
    @Override
    public String toString(){
        String directorStr = Arrays.toString(directors);
        String starringStr = Arrays.toString(starring);
        return String.format("\n" +
                "Title: %s\n" +
                "Starring: %s\n" +
                "Director(s): %s\n" +
                "Genre: %s\n" +
                "Classification: %s\n" +
                "Release Date: %d\n" +
                "Duration: %d minutes\n" +
                "Copies Available: %d\n" +
                "Times Rented: %d\n",
                title, starringStr.substring(1, starringStr.length()-1), directorStr.substring(1,directorStr.length()-1),
                genre.toString(), classification.toString(), releaseDate,
                duration.toMinutes(), copiesAvailable, numBorrows);

    }



    // For testing
    public static Movie[] hardCoddedMovies() {

        Movie movie1 = new Movie("Movie1");
        movie1.genre = Genre.Adventure;
        movie1.starring = new String[]{"Actor"};
        movie1.directors = new String[] { "Director"};
        movie1.duration = Duration.ofMinutes(50);
        movie1.classification = Classification.MatureAccompanied;
        movie1.releaseDate = 2012;
        movie1.copiesAvailable = 1;
        movie1.numBorrows = 1;

        Movie movie2 = new Movie("Movie2");
        movie2.genre = Genre.Adventure;
        movie2.starring = new String[]{"Actor, Actor 2"};
        movie2.directors = new String[] { "Director, Director 3"};
        movie2.duration = Duration.ofMinutes(50);
        movie2.classification = Classification.MatureAccompanied;
        movie2.releaseDate = 2012;
        movie2.copiesAvailable = 2;
        movie2.numBorrows = 2;

        Movie movie3 = new Movie("Movie3");
        movie3.genre = Genre.Adventure;
        movie3.starring = new String[]{"Actor"};
        movie3.directors = new String[] { "Director"};
        movie3.duration = Duration.ofMinutes(50);
        movie3.classification = Classification.MatureAccompanied;
        movie3.releaseDate = 2012;
        movie3.copiesAvailable = 3;
        movie3.numBorrows = 3;

        Movie movie4 = new Movie("Movie4");
        movie4.genre = Genre.Adventure;
        movie4.starring = new String[]{"Actor, Actor 2"};
        movie4.directors = new String[] { "Director, Director 3"};
        movie4.duration = Duration.ofMinutes(50);
        movie4.classification = Classification.MatureAccompanied;
        movie4.releaseDate = 2012;
        movie4.copiesAvailable = 4;
        movie4.numBorrows = 4;

        Movie movie5 = new Movie("Movie5");
        movie5.genre = Genre.Adventure;
        movie5.starring = new String[]{"Actor, Actor 2"};
        movie5.directors = new String[] { "Director, Director 3"};
        movie5.duration = Duration.ofMinutes(50);
        movie5.classification = Classification.MatureAccompanied;
        movie5.releaseDate = 2012;
        movie5.copiesAvailable = 5;
        movie5.numBorrows = 5;


        Movie movie6 = new Movie("Movie6");
        movie6.genre = Genre.Adventure;
        movie6.starring = new String[]{"Actor, Actor 2"};
        movie6.directors = new String[] { "Director, Director 3"};
        movie6.duration = Duration.ofMinutes(50);
        movie6.classification = Classification.MatureAccompanied;
        movie6.releaseDate = 2012;
        movie6.copiesAvailable = 5;
        movie6.numBorrows = 6;

        Movie movie7 = new Movie("Movie7");
        movie7.genre = Genre.Adventure;
        movie7.starring = new String[]{"Actor, Actor 2"};
        movie7.directors = new String[] { "Director, Director 3"};
        movie7.duration = Duration.ofMinutes(50);
        movie7.classification = Classification.MatureAccompanied;
        movie7.releaseDate = 2012;
        movie7.copiesAvailable = 5;
        movie7.numBorrows = 7;

        Movie movie8 = new Movie("Movie8");
        movie8.genre = Genre.Adventure;
        movie8.starring = new String[]{"Actor, Actor 2"};
        movie8.directors = new String[] { "Director, Director 3"};
        movie8.duration = Duration.ofMinutes(50);
        movie8.classification = Classification.MatureAccompanied;
        movie8.releaseDate = 2012;
        movie8.copiesAvailable = 5;
        movie8.numBorrows = 8;

        Movie movie9 = new Movie("Movie9");
        movie9.genre = Genre.Adventure;
        movie9.starring = new String[]{"Actor, Actor 2"};
        movie9.directors = new String[] { "Director, Director 3"};
        movie9.duration = Duration.ofMinutes(50);
        movie9.classification = Classification.MatureAccompanied;
        movie9.releaseDate = 2012;
        movie9.copiesAvailable = 5;
        movie9.numBorrows = 9;

        Movie movie10 = new Movie("Movie10");
        movie10.genre = Genre.Adventure;
        movie10.starring = new String[]{"Actor, Actor 2"};
        movie10.directors = new String[] { "Director, Director 3"};
        movie10.duration = Duration.ofMinutes(50);
        movie10.classification = Classification.MatureAccompanied;
        movie10.releaseDate = 2012;
        movie10.copiesAvailable = 5;
        movie10.numBorrows = 10;

        Movie[] movies = {movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10};
        return movies;

    }


}
