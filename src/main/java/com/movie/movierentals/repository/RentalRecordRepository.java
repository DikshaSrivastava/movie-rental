package com.movie.movierentals.repository;

import com.movie.movierentals.entity.RentalRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB repository is used here to store the details of the customer and the movie rented by him/her.
 */
@Repository
public interface RentalRecordRepository extends MongoRepository<RentalRecordEntity, String> {

    List<RentalRecordEntity> findByCustomerName(String customerName);

}
