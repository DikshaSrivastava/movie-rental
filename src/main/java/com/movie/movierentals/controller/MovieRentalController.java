package com.movie.movierentals.controller;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.RentalRecord;
import com.movie.movierentals.service.MovieRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRentalController {

    @Autowired
    private MovieRentalService service;

    @PostMapping("/rentalRecord")
    public ResponseEntity<RentalRecord> calculateRentalRecord(@RequestBody Customer customer) {
        return ResponseEntity.ok(service.getStatement(customer));

    }
}
