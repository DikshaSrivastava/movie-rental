package com.movie.movierentals.service;

import com.movie.movierentals.entity.RentalRecordEntity;
import com.movie.movierentals.exception.BadRequestException;
import com.movie.movierentals.model.Customer;
import com.movie.movierentals.model.MovieAmount;
import com.movie.movierentals.model.MovieRental;
import com.movie.movierentals.model.RentalRecord;
import com.movie.movierentals.repository.RentalRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MovieRentalServiceTest {

    @Autowired
    private MovieRentalService service;

    @MockBean
    private RentalRecordRepository repository;

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
        when(repository.save(Mockito.any(RentalRecordEntity.class))).thenReturn(null);
        RentalRecord actual = service.getStatement(customer);
        assertEquals(expected.getCustomerName(), actual.getCustomerName());
        assertEquals(expected.getOwedAmount(), actual.getOwedAmount());
    }

    @Test
    public void getStatementForNewAndChildrenMovieTest() {
        RentalRecord expected = new RentalRecord();
        expected.setCustomerName("C. U. Stomer");
        expected.setFrequentPoints(3);
        expected.setMovieAmountList(List.of(new MovieAmount(
                        "Fast & Furious X",
                        9.0
                ),
                new MovieAmount("Cars",
                        3.0) ));
        expected.setOwedAmount(12.0);
        Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F004", 3),
                new MovieRental("F003", 4)));
        when(repository.save(Mockito.any(RentalRecordEntity.class))).thenReturn(null);
        RentalRecord actual = service.getStatement(customer);
        assertEquals(expected.getCustomerName(), actual.getCustomerName());
        assertEquals(expected.getOwedAmount(), actual.getOwedAmount());
        assertEquals(expected.getFrequentPoints(), actual.getFrequentPoints());
    }

    @Test
    public void getStatementIfCustomerVisitedAgainTest() {
        RentalRecord expected = new RentalRecord();
        expected.setCustomerName("C. U. Stomer");
        expected.setFrequentPoints(4);
        expected.setMovieAmountList(List.of(
                new MovieAmount("Cars",
                        1.5),
                new MovieAmount("Fast & Furious X",
                        3.0)));
        expected.setOwedAmount(4.5);

        Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F003", 3),
                new MovieRental("F004", 1)));
        when(repository.save(Mockito.any(RentalRecordEntity.class))).thenReturn(null);
        RentalRecord actual = service.getStatement(customer);
        assertEquals(expected.getCustomerName(), actual.getCustomerName());
        assertEquals(expected.getOwedAmount(), actual.getOwedAmount());
    }

    @Test
    public void getStatementDaysLessThanZeroTest() {
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
        Customer customer = new Customer("C. U. Stomer", List.of(new MovieRental("F001", -3),
                new MovieRental("F002", 1)));
        when(repository.save(Mockito.any(RentalRecordEntity.class))).thenReturn(null);
        assertThrows(BadRequestException.class, () -> {service.getStatement(customer);});
    }

    @Test
    public void getRentalRecordsTest() {
        RentalRecordEntity rentalRecordEntity = new RentalRecordEntity();
        rentalRecordEntity.setCustomerName("C. U. Stomer");
        rentalRecordEntity.setFrequentPoints(2);
        rentalRecordEntity.setMovieAmountList(List.of(new MovieAmount(
                        "You've Got Mail",
                        3.5
                ),
                new MovieAmount("Matrix",
                        2.0) ));
        rentalRecordEntity.setOwedAmount(5.5);
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
        when(repository.findAll()).thenReturn(List.of(rentalRecordEntity));
        List<RentalRecord> rentalRecords = service.getRentalRecords();
        assertEquals(expected.getCustomerName(), rentalRecords.get(0).getCustomerName());
        assertEquals(expected.getOwedAmount(), rentalRecords.get(0).getOwedAmount());
    }

    @Test
    public void getRentalRecordTest() {
        RentalRecordEntity rentalRecordEntity = new RentalRecordEntity();
        rentalRecordEntity.setCustomerName("C. U. Stomer");
        rentalRecordEntity.setFrequentPoints(2);
        rentalRecordEntity.setMovieAmountList(List.of(new MovieAmount(
                        "You've Got Mail",
                        3.5
                ),
                new MovieAmount("Matrix",
                        2.0) ));
        rentalRecordEntity.setOwedAmount(5.5);
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
        when(repository.findByCustomerName("C. U. Stomer")).thenReturn(List.of(rentalRecordEntity));
        List<RentalRecord> rentalRecord = service.getRentalRecord("C. U. Stomer");
        assertEquals(expected.getCustomerName(), rentalRecord.get(0).getCustomerName());
        assertEquals(expected.getOwedAmount(), rentalRecord.get(0).getOwedAmount());
    }
}
