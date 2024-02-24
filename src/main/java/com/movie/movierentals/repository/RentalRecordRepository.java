package com.movie.movierentals.repository;

import com.movie.movierentals.entity.RentalRecordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRecordRepository extends MongoRepository<RentalRecordEntity, String> {

}
