package com.movie.movierentals.service;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.RentalRecord;
import org.springframework.stereotype.Service;

@Service
public interface MovieRentalService {

    RentalRecord getStatement(Customer customer);
}
