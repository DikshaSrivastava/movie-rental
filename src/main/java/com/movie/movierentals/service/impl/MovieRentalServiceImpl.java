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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MovieRentalServiceImpl implements MovieRentalService {

    @Autowired
    private RentalRecordRepository repository;

    public RentalRecord getStatement(Customer customer) {
        Map<String, Movie> movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
        RentalRecordEntity previousRentalRecord = repository.findByCustomerName(customer.getName());

        double totalAmount = 0;
        int frequentEnterPoints = 0;
        List<MovieAmount> movieAmountList = new ArrayList<>();
        if(previousRentalRecord != null) {
            totalAmount = previousRentalRecord.getOwedAmount();
            frequentEnterPoints = previousRentalRecord.getFrequentPoints();
            movieAmountList = previousRentalRecord.getMovieAmountList();
            repository.delete(previousRentalRecord);
        }
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCustomerName(customer.getName());

        for (MovieRental r : customer.getRentals()) {
            if(r.getDays() < 0) {
                throw new BadRequestException(MovieRentalsConstants.DAYS);
            }
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
        RentalRecordEntity rentalRecordEntity = convertModelToEntity(rentalRecord);
        repository.save(rentalRecordEntity);
        return rentalRecord;
    }

    private RentalRecordEntity convertModelToEntity(RentalRecord rentalRecord) {
        RentalRecordEntity rentalRecordEntity = new RentalRecordEntity();
        rentalRecordEntity.setCustomerName(rentalRecord.getCustomerName());
        rentalRecordEntity.setMovieAmountList(rentalRecord.getMovieAmountList());
        rentalRecordEntity.setOwedAmount(rentalRecord.getOwedAmount());
        rentalRecordEntity.setFrequentPoints(rentalRecord.getFrequentPoints());
        return rentalRecordEntity;
    }

    public List<RentalRecord> getRentalRecords(){
        List<RentalRecordEntity> rentalRecordEntities = repository.findAll();
        return rentalRecordEntities.stream()
                .map(entity -> convertEntityToModel(entity))
                .collect(Collectors.toList());
    }

    private RentalRecord convertEntityToModel(RentalRecordEntity rentalRecordEntity) {
        RentalRecord rentalRecord = new RentalRecord();
        rentalRecord.setCustomerName(rentalRecordEntity.getCustomerName());
        rentalRecord.setMovieAmountList(rentalRecordEntity.getMovieAmountList());
        rentalRecord.setOwedAmount(rentalRecordEntity.getOwedAmount());
        rentalRecord.setFrequentPoints(rentalRecordEntity.getFrequentPoints());
        return rentalRecord;
    }

    public RentalRecord getRentalRecord(String customerName) {
        return convertEntityToModel(repository.findByCustomerName(customerName));
    }
}
