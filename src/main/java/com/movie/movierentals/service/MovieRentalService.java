package com.movie.movierentals.service;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.RentalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface for service class.
 */
@Service
public interface MovieRentalService {

    RentalRecord getStatement(Customer customer);

    List<RentalRecord> getRentalRecords();

    List<RentalRecord> getRentalRecord(String customerName);
}
