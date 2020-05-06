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
     * Returns if the movie name exists within the movie collection
     * @param movie Movie potentially in MovieCollection
     * @return
     */
    public boolean movieExist(Movie movie) {
        return false;
    }

    /**
     * Returns movie of a given name
     * @param movieTitle Movie name within the collection
     * @return Return movie within the collection else it returns null
     */
//    public Movie getMovie(String movieTitle) {
//
//    }

    /**
     * Add DVDs of a new movie to software application
     * @param movie
     * @implNote adds movie to Movies
     */
    public boolean addMovie(Movie movie) {
        System.out.println(movie.getTitle());
//        if (!movieExist(movie)) {
            movies.addNode(movie);
            return true;
//        } else return false;
    }

    /**
     * Removes a movie from the software application
     * @param movie
     * @implNote removes movie from Movies
     */

    /**
     * Display Information about all movies
     */

    /**
     * Display the top 10 most frequently borrowed movies
     */
}
