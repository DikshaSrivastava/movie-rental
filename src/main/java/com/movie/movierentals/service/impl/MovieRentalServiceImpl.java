package com.movie.movierentals.service.impl;

import com.movie.movierentals.constants.MovieRentalsConstants;
import com.movie.movierentals.entity.RentalRecordEntity;
import com.movie.movierentals.exception.BadRequestException;
import com.movie.movierentals.model.*;
import com.movie.movierentals.repository.RentalRecordRepository;
import com.movie.movierentals.service.MovieRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This service class handles all the business logic of calculating amount owed for a movie rental.
 * It also stores these information in DB.
 */
@Service
public class MovieRentalServiceImpl implements MovieRentalService {

    @Autowired
    private RentalRecordRepository repository;

    /**
     * This method applies business logic to calculate the rental amount for each movie and the total amount owed.
     * @param customer contains customer information and movies the customer wants to rent.
     * @return customer information and movie rental details.
     */
    public RentalRecord getStatement(Customer customer) {
        // This map stores movie information like id, name and category.
        Map<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        // Preparing the response.
        List<MovieAmount> movieAmountList = new ArrayList<>();
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCustomerName(customer.getName());

        // Iterating through each movie rented by customer.
        for (MovieRental r : customer.getRentals()) {
            // if days for ny movie rented is less than 0 then raise an exception.
            if(r.getDays() < 0) {
                throw new BadRequestException(MovieRentalsConstants.DAYS);
            }
            // the current owed amount for the current movie.
            double thisAmount = 0;

            // determine amount for each movie
            if (movies.get(r.getMovieId()).getCode().equals("regular")) {
                thisAmount = 2;
                if (r.getDays() > 2) {
                    thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
                }
            }
            if (movies.get(r.getMovieId()).getCode().equals("new")) {
                thisAmount = r.getDays() * 3;
                // add bonus for a two day new release rental
                if(r.getDays() > 2) {
                    frequentEnterPoints++;
                }
            }
            if (movies.get(r.getMovieId()).getCode().equals("childrens")) {
                thisAmount = 1.5;
                if (r.getDays() > 3) {
                    thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
                }
            }
            //add frequent bonus points
            frequentEnterPoints++;
            //print figures for this rental
            movieAmountList.add(new MovieAmount(movies.get(r.getMovieId()).getTitle(),
                    thisAmount));

            totalAmount = totalAmount + thisAmount;
        }
        // add footer lines
        rentalRecord.setOwedAmount(totalAmount);
        rentalRecord.setMovieAmountList(movieAmountList);
        rentalRecord.setFrequentPoints(frequentEnterPoints);
        // converting the response to an entity to be saved in database.
        RentalRecordEntity rentalRecordEntity = convertModelToEntity(rentalRecord);
        // storing the customer and rental information in DB.
        repository.save(rentalRecordEntity);
        return rentalRecord;
    }

    /**
     * This method converts the reponse model to an entity to be stored in database.
     * @param rentalRecord the reponse model.
     * @return the entity stored in DB.
     */
    private RentalRecordEntity convertModelToEntity(RentalRecord rentalRecord) {
        RentalRecordEntity rentalRecordEntity = new RentalRecordEntity();
        rentalRecordEntity.setCustomerName(rentalRecord.getCustomerName());
        rentalRecordEntity.setMovieAmountList(rentalRecord.getMovieAmountList());
        rentalRecordEntity.setOwedAmount(rentalRecord.getOwedAmount());
        rentalRecordEntity.setFrequentPoints(rentalRecord.getFrequentPoints());
        return rentalRecordEntity;
    }

    /**
     * This method fetches the movie rental information for all customers.
     * @return list of movie rental information.
     */
    public List<RentalRecord> getRentalRecords(){
        List<RentalRecordEntity> rentalRecordEntities = repository.findAll();
        return rentalRecordEntities.stream()
                .map(entity -> convertEntityToModel(entity))
                .collect(Collectors.toList());
    }

    /**
     * This method transforms the entity to response model to be returned to the controller.
     * @param rentalRecordEntity the entity stored in DB.
     * @return the response model
     */
    private RentalRecord convertEntityToModel(RentalRecordEntity rentalRecordEntity) {
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCustomerName(rentalRecordEntity.getCustomerName());
        rentalRecord.setMovieAmountList(rentalRecordEntity.getMovieAmountList());
        rentalRecord.setOwedAmount(rentalRecordEntity.getOwedAmount());
        rentalRecord.setFrequentPoints(rentalRecordEntity.getFrequentPoints());
        return rentalRecord;
    }

    /**
     * This method fetches rental record for a particular user.
     * @param customerName The customer for whom rental records are fetched.
     * @return list of rental records as one customer can visit and rent movies multiple times.
     */
    public List<RentalRecord> getRentalRecord(String customerName) {
        List<RentalRecordEntity> rentalRecordEntities = repository.findByCustomerName(customerName);
        return rentalRecordEntities.stream()
                .map(entity -> convertEntityToModel(entity))
                .collect(Collectors.toList());
    }
}
