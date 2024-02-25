package com.movie.movierentals.controller;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.RentalRecord;
import com.movie.movierentals.service.MovieRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller class stores and gets movie rental information for customers.
 */
@RestController
public class MovieRentalController {

    @Autowired
    private MovieRentalService service;

    /**
     * This method gets customer movie rental information and generates the statement of amount owed by the customer.
     * @param customer contains customer information and movie to rent.
     * @return customer information along with amount owed for each movie and total owed amount.
     */
    @PostMapping("/rentalRecord")
    public ResponseEntity<RentalRecord> calculateRentalRecord(@RequestBody Customer customer) {
        return ResponseEntity.ok(service.getStatement(customer));
    }

    /**
     * This GET API fetches all the customers from the database.
     * @return List of customers and movie rentals.
     */
    @GetMapping("/rentalRecords")
    public ResponseEntity<List<RentalRecord>> getRentalRecords(){
        return ResponseEntity.ok(service.getRentalRecords());
    }

    /**
     * This GET API fetches the movie rental information for a particular customer.
     * @param customerName for which the movie rental information needs to be fetched.
     * @return customer information and movie rental information.
     */
    @GetMapping("/rentalRecord/{customerName}")
    public ResponseEntity<List<RentalRecord>> getRentalRecord(@PathVariable String customerName) {
        return ResponseEntity.ok(service.getRentalRecord(customerName));
    }
}
