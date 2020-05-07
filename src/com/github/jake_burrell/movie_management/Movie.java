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
    } };

    enum Genre {Drama, Adventure, Family, Action, SciFi {
        @Override
        public String toString() {
            return "Sci-Fi";
        }
    }, Comedy, Animation, Thriller, Other};

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

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }


    public Object getCopiesAvailable() {
        return this.copiesAvailable;
    }

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

    public void addCopies(int numCopies) {
        this.copiesAvailable += numCopies;
    }

    public void setStarring(String[] actors) {
        this.starring = actors;
    }


    @Override
    public int compareTo(Movie otherMovie) {
        return this.title.compareTo(otherMovie.title);
//        int numBorrows = this.numBorrows;
//        if (numBorrows > otherMovie.numBorrows) return 1;
//        else if (numBorrows < otherMovie.numBorrows) return -1;
//        else return 0;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

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

    public void setDuration() {
        System.out.print("Enter the duration (minutes): ");
        Integer minutes = returnDigit();
        if (minutes == null) setDuration();
        else if (minutes <= 0) {
            System.out.println("Duration must be positive.");
            setDuration();
        } else this.duration = Duration.ofMinutes(minutes);
    }

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

    @Override
    public String toString(){
        String directorStr = Arrays.toString(directors);
        String starringStr = Arrays.toString(directors);
        return String.format("\n" +
                "Title: %s\n" +
                "Starring: %s\n" +
                "Director(s): %s\n" +
                "Genre: %s\n" +
                "Classification: %s\n" +
                "Release Date: %d" +
                "Duration: %d minutes\n" +
                "Release Date: %d\n" +
                "Times Rented: %d",
                title, starringStr.substring(1, starringStr.length()-1), directorStr.substring(1,directorStr.length()-1),
                genre.toString(), classification.toString(), releaseDate,
                duration.toMinutes(), copiesAvailable, numBorrows);

    }

    /**
     * Auto-Generated override from template
     * @param o
     * @return
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

    //    // For testing
//    public Movie[] hardCoddedMovies() {
//        Movie[] movies = new Movie[3];
//        Movie movie1 = new Movie("Movie1");
//        movie.
//    }


}
