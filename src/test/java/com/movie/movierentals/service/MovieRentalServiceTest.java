package com.movie.movierentals.service;

import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.MovieRental;
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
        String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";
        Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F001", 3),
                new MovieRental("F002", 1)));
        assertEquals(expected, service.getStatement(customer));
    }
}
