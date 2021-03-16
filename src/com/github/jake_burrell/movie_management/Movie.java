package com.github.jake_burrell.movie_management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Arrays;
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
    private enum Classification {
        General {
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
    },  MatureAccompanied {
        @Override
        public String toString() {
            return "Mature Accompanied (MA15+)";
        }};

        /**
         * Helper method to return a Classification from an int
         * @param number from 1-4 corresponding to a Classification
         * @return Classification corresponding to integer
         */
        public static Classification fromInt(int number) {
            switch (number) {
                case 1:
                    return Classification.General;
                case 2:
                    return Classification.ParentalGuidance;
                case 3:
                    return Classification.Mature;
                case 4:
                   return Classification.MatureAccompanied;
                default:
                    return null;
            }
        }

    }


    /**
     * Movie genres overriding the toString method where needed and providing a fromInt method
     */
    private enum Genre {Drama, Adventure, Family, Action, SciFi {
        @Override
        public String toString() {
            return "Sci-Fi";
        }
    }, Comedy, Animation, Thriller, Other;

        /**
         * Helper method to set Genre given its corresponding number
         * @param number between 1 - 9 corresponding to a Genre
         * @return Genre corresponding to the number
         */
    public static Genre fromInt(int number) {
        switch (number) {
            case 1:
                return Genre.Drama;
            case 2:
                return Genre.Adventure;
            case 3:
                return Genre.Family;
            case 4:
                return Genre.Action;
            case 5:
                return Genre.SciFi;
            case 6:
                return Genre.Comedy;
            case 7:
                return Genre.Animation;
            case 8:
                return Genre.Thriller;
            case 9:
                return Genre.Other;
            default:
                return null;
        }

    }
    }

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
     * Checks if their a copies available of particular movie.
     * @return Returns true if and only if their where copies avalible
     */
    public boolean movieBorrowable() {
        return copiesAvailable > 0;
    }

    /**
     * Increments numBorrows and decrements copiesAvailable
     */
    public void movieBorrow() {
        numBorrows++;
        copiesAvailable--;
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
            this.genre = Genre.fromInt(selection);
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
            this.classification = Classification.fromInt(selection);
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


        Movie movie11= new Movie("Movie11");
        movie11.genre = Genre.Adventure;
        movie11.starring = new String[]{"Actor, Actor 2"};
        movie11.directors = new String[] { "Director, Director 3"};
        movie11.duration = Duration.ofMinutes(70);
        movie11.classification = Classification.MatureAccompanied;
        movie11.releaseDate = 2015;
        movie11.copiesAvailable = 15;
        movie11.numBorrows = 5;

        Movie[] movies = {movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10, movie11};
        return movies;

    }


}
