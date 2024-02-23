package com.movie.movierentals.model;

import java.util.List;

public class RentalRecord {

    private String customerName;
    private List<MovieAmount> movieAmountList;
    private double owedAmount;
    private int frequentPoints;

    public RentalRecord() {
    }

    public RentalRecord(String customerName, List<MovieAmount> movieAmountList, double owedAmount, int frequentPoints) {
        this.customerName = customerName;
        this.movieAmountList = movieAmountList;
        this.owedAmount = owedAmount;
        this.frequentPoints = frequentPoints;
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
