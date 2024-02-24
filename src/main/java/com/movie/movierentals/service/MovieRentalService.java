package com.movie.movierentals.service;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.RentalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieRentalService {

    RentalRecord getStatement(Customer customer);

    List<RentalRecord> getRentalRecords();
}
