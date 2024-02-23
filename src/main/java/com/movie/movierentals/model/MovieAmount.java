package com.movie.movierentals.model;

public class MovieAmount {

    private String movieName;
    private double amount;

    public MovieAmount(String movieName, double amount) {
        this.movieName = movieName;
        this.amount = amount;
    }

    public String getMovieName() {
        return movieName;
    }

    public double getAmount() {
        return amount;
    }
}
