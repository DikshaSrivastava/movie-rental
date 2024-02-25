package com.movie.movierentals.model;

import java.util.List;

/**
 * This model is the input request of the API.
 * It stores customer name and Movie ID and number of days to rent the movie.
 */
public class Customer {
    private String name;
    private List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
