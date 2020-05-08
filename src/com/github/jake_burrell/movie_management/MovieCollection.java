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
     * Removes a movie from the MovieCollection if it exists
     * @param movie The movie to be deleted.
     * @return
     */
    public boolean removeMovie(Movie movie){
        return movies.removeNode(movie);
    }

    /**
     * Display the top 10 most frequently borrowed movies
     */
    public String[] top10Borrowed() {
        BinarySearchTree<MoviePair> orderedMovies = new BinarySearchTree<>();
        String[] titleTopTen = new String[10];
        for (Movie movie : movies) {
            MoviePair moviePair = new MoviePair(movie.getTitle(), movie.getNumBorrows());
            orderedMovies.addNode(moviePair);
        }

        int index = 0;
        for (MoviePair moviePair: orderedMovies) {
            titleTopTen[index] = moviePair.movieName;
            if (index == 10) break;
            index++;
        }
        return titleTopTen;

//        int[] numBorrows = new int[10];
//        String[] movieNames = new String[10];
//        for (Movie movie: movies) {
//            for (int index = 0; index < 10; index++) {
//                if (movieNames[index] != null || movie.getNumBorrows() > numBorrows[index]) {
//                    numBorrows[index] = movie.getNumBorrows();
//                    movieNames[index] = movie.getTitle();
//                }
//            }
//        }

    }

    /**
     * Class for storing movie names alongside their number of borrows. It also overrides the compareTo method so
     * that MovieParis are ordered based on their num borrows in reverse order. This allows them to be inserted into
     * the Binary Search Tree and then traverse the tree using ordered traversal thus returning a list of movies based
     * on their numBorrows from most to least
     */
    private class MoviePair implements Comparable<MoviePair>{
        private String movieName;
        private Integer numBorrows;

        public MoviePair(String movieName, int numBorrows) {
            this.movieName = movieName;
            this.numBorrows = numBorrows;
        }

        @Override
        public int compareTo(MoviePair otherPair) {
            int numBorrows = this.numBorrows;
            if (numBorrows > otherPair.numBorrows) return -1;
            else if (numBorrows < otherPair.numBorrows) return 1;
            else return 0;
        }

        public String getMovieName() {
            return this.movieName;
        }

    }

    public static void main(String[] args) {

    }
}
