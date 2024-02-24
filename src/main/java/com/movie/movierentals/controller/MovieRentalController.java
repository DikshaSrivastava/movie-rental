package com.movie.movierentals.controller;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.RentalRecord;
import com.movie.movierentals.service.MovieRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieRentalController {

    @Autowired
    private MovieRentalService service;

    @PostMapping("/rentalRecord")
    public ResponseEntity<RentalRecord> calculateRentalRecord(@RequestBody Customer customer) {
        return ResponseEntity.ok(service.getStatement(customer));
    }

    @GetMapping("/rentalRecords")
    public ResponseEntity<List<RentalRecord>> getRentalRecords(){
        return ResponseEntity.ok(service.getRentalRecords());
    }

    @GetMapping("/rentalRecord/{customerName}")
    public ResponseEntity<RentalRecord> getRentalRecord(@PathVariable String customerName) {
        return ResponseEntity.ok(service.getRentalRecord(customerName));
    }
}
