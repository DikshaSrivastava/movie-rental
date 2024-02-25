package com.movie.movierentals.model;

/**
 * This model is returned in the response containing movie name and
 * amount owed for each movie.
 */
public class MovieAmount {

    private String movieName;
    private double amount;

    public MovieAmount(String movieName, double amount) {
        this.movieName = movieName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "movieName='" + movieName + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getMovieName() {
        return movieName;
    }

    public double getAmount() {
        return amount;
    }
}
