package com.movie.movierentals.model;

/**
 * This is the input request which stores the movie id and days for which
 * the customer wants to rent the movie.
 */
public class MovieRental {
    private String movieId;
    private int days;

    public MovieRental(String movieId, int days) {
        this.movieId = movieId;
        this.days = days;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDays() {
        return days;
    }
}
