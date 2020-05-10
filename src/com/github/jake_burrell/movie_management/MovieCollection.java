package com.github.jake_burrell.movie_management;

import com.github.jake_burrell.movie_management.models.BinarySearchTree;

/**
 * Stores a collection of movies
 * @author Jake Burrell
 */

public class MovieCollection {

    // Property
    private BinarySearchTree<Movie> movies;

    // Constructor
    public MovieCollection() {
        this.movies = new BinarySearchTree<>();
    }

    //Getters
    public BinarySearchTree<Movie> getMovies() {
        return this.movies;
    }

    public int getNumMovies() {
        return movies.getNumNodes();
    }

    /**
     * Display Information about all movies
     */
    public void displayMovieInfo() {
        for (Movie movie: movies) {
            System.out.println(movie.toString());
        }
    }

    /**
     * Returns if the movie name exists within the movie collection
     * @param movie Movie potentially in MovieCollection
     * @return True if movie exists
     */
    public boolean movieExist(Movie movie) {
        return movies.itemExists(movie);
    }

    /**
     * Retrieves a movie from the movie collection
     * @param movieName The name of them movie as a String
     * @return The movie with the provided name
     */
    public Movie retrieveMovie(String movieName){
        Movie someMovie = new Movie(movieName);
        return movies.searchTree(someMovie);
    }

    /**
     * Adds a new movie to the Movie collection if a movie of the same name wasn't already added
     * @param movie Move in which to add to the movie collection
     * @return True if the movie did npt exist within the movie collection
     */
    public boolean addMovie(Movie movie) {
        if (!movieExist(movie)) {
            movies.addNode(movie);
            return true;
        } else return false;
    }

    /**
     * Removes a movie from the MovieCollection if it exists
     * @param movie The movie to be deleted.
     * @return True if movie was deleted
     */
    public boolean removeMovie(Movie movie){
        return movies.removeNode(movie);
    }

    /**
     * Display the top 10 most frequently borrowed movies
     */
    public MoviePair[] top10Borrowed() {
        BinarySearchTree<MoviePair> orderedMovies = new BinarySearchTree<>();
        MoviePair[] titleTopTen = new MoviePair[10];
        // adds each movie to new search true
        for (Movie movie : movies) {
            MoviePair moviePair = new MoviePair(movie.getTitle(), movie.getNumBorrows());
            orderedMovies.addNode(moviePair);

        }

        int index = 0;
        // Records first 10 most frequently borrowed movie's names to array
        for (MoviePair moviePair: orderedMovies) {
            if (index == 10) break;
            titleTopTen[index] = moviePair;
            index++;
        }
        return titleTopTen;
    }

    /**
     * Class for storing movie names alongside their number of borrows. It also overrides the compareTo method so
     * that MovieParis are ordered based on their num borrows in reverse order. This means when inserted into
     * the Binary Search Tree. The tree can be traversed using in-order traversal thus returning a list of movies based
     * on their numBorrows from most borrowed to least
     */
    public static class MoviePair implements Comparable<MoviePair>{
        private final String movieName;
        private final Integer numBorrows;

        public MoviePair(String movieName, int numBorrows) {
            this.movieName = movieName;
            this.numBorrows = numBorrows;
        }

        /**
         * Allows MoviePairs to be compared inversely based on their numBorrows
         * @param otherPair Pair that this instance is compared to
         * @return 1 if this moviePar is compared to have less numBorrows, -1 more numBorrows than and 0 if equal
         */
        @Override
        public int compareTo(MoviePair otherPair) {
            int numBorrows = this.numBorrows;
            if (numBorrows > otherPair.numBorrows) return -1;
            else if (numBorrows < otherPair.numBorrows) return 1;
            else return 0;
        }

        public String getMovieTitle() {
            return this.movieName;
        }

        public Integer getNumBorrows() {
            return numBorrows;
        }
    }

}
