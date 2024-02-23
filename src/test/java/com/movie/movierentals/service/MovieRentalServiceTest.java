package com.movie.movierentals.service;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.MovieAmount;
import com.movie.movierentals.model.MovieRental;
import com.movie.movierentals.model.RentalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieRentalServiceTest {

    @Autowired
    private MovieRentalService service;

    @Test
    public void getStatementTest() {
        RentalRecord expected = new RentalRecord();
        expected.setCustomerName("C. U. Stomer");
        expected.setFrequentPoints(2);
        expected.setMovieAmountList(List.of(new MovieAmount(
                        "You've Got Mail",
                        3.5
                ),
                new MovieAmount("Matrix",
                        2.0) ));
        expected.setOwedAmount(5.5);
        Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F001", 3),
                new MovieRental("F002", 1)));
        RentalRecord actual = service.getStatement(customer);
        assertEquals(expected.getCustomerName(), actual.getCustomerName());
        assertEquals(expected.getOwedAmount(), actual.getOwedAmount());
    }
}
