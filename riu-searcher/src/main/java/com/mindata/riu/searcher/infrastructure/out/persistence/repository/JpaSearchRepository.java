package com.mindata.riu.searcher.infrastructure.out.persistence.repository;

import com.mindata.riu.searcher.infrastructure.out.persistence.entity.SearchEntity;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaSearchRepository extends Repository<SearchEntity, Long> {

    Optional<SearchEntity> findBySearchId(String searchId);

    List<SearchEntity> findAllByHotelIdAndCheckInEqualsAndCheckOutEquals(
        String hotelId,
        LocalDate checkIn,
        LocalDate checkOut
    );

}
