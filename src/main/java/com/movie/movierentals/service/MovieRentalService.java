package com.movie.movierentals.service;

import com.movie.movierentals.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface MovieRentalService {

    String getStatement(Customer customer);
}
