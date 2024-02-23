package com.movie.movierentals.service.impl;

import com.movie.movierentals.model.*;
import com.movie.movierentals.service.MovieRentalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieRentalServiceImpl implements MovieRentalService {

    public RentalRecord getStatement(Customer customer) {
        Map<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCustomerName(customer.getName());
        List<MovieAmount> movieAmountList = new ArrayList<>();
        for (MovieRental r : customer.getRentals()) {
            double thisAmount = 0;

            // determine amount for each movie
            if (movies.get(r.getMovieId()).getCode().equals("regular")) {
                thisAmount = 2;
                if (r.getDays() > 2) {
                    thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                }
            }
            if (movies.get(r.getMovieId()).getCode().equals("new")) {
                thisAmount = r.getDays() * 3;
            }
            if (movies.get(r.getMovieId()).getCode().equals("childrens")) {
                thisAmount = 1.5;
                if (r.getDays() > 3) {
                    thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
                }
            }

            //add frequent bonus points
            frequentEnterPoints++;
            // add bonus for a two day new release rental
            if (movies.get(r.getMovieId()).getCode().equals("new") && r.getDays() > 2) frequentEnterPoints++;

            //print figures for this rental
            movieAmountList.add(new MovieAmount(movies.get(r.getMovieId()).getTitle(),
                    thisAmount));
            rentalRecord.setMovieAmountList(movieAmountList);
            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        rentalRecord.setOwedAmount(totalAmount);
        rentalRecord.setFrequentPoints(frequentEnterPoints);

        return rentalRecord;
    }
}
