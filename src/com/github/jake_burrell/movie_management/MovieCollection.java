package com.github.jake_burrell.movie_management;

import com.github.jake_burrell.movie_management.models.BinarySearchTree;

/**
 * Stores a collection of movies
 * @author Jake Burrell
 */

public class MovieCollection {

    private BinarySearchTree<Movie> movies;

    public MovieCollection() {
        this.movies = new BinarySearchTree<>();
    }

    public BinarySearchTree<Movie> getMovies() {
        return this.movies;
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
     * @return
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
     * Removes a movie from the software application
     * @param movie
     * @implNote removes movie from Movies
     */
    public boolean removeMovie()



    /**
     * Display the top 10 most frequently borrowed movies
     */
}
