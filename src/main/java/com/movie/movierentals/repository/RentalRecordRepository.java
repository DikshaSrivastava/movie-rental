package com.movie.movierentals.repository;

import com.movie.movierentals.entity.RentalRecordEntity;
import com.movie.movierentals.model.RentalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRecordRepository extends MongoRepository<RentalRecordEntity, String> {

    RentalRecordEntity findByCustomerName(String customerName);

}
