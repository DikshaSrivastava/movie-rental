package com.movie.movierentals.entity;

import com.movie.movierentals.model.MovieAmount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rentalRecord")
public class RentalRecordEntity {

    @Id
    private String id;
    private String customerName;
    private List<MovieAmount> movieAmountList;
    private double owedAmount;
    private int frequentPoints;

    public RentalRecordEntity() {
    }

    public RentalRecordEntity(String id, String customerName, List<MovieAmount> movieAmountList, double owedAmount, int frequentPoints) {
        this.id = id;
        this.customerName = customerName;
        this.movieAmountList = movieAmountList;
        this.owedAmount = owedAmount;
        this.frequentPoints = frequentPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<MovieAmount> getMovieAmountList() {
        return movieAmountList;
    }

    public void setMovieAmountList(List<MovieAmount> movieAmountList) {
        this.movieAmountList = movieAmountList;
    }

    public double getOwedAmount() {
        return owedAmount;
    }

    public void setOwedAmount(double owedAmount) {
        this.owedAmount = owedAmount;
    }

    public int getFrequentPoints() {
        return frequentPoints;
    }

    public void setFrequentPoints(int frequentPoints) {
        this.frequentPoints = frequentPoints;
    }
}
