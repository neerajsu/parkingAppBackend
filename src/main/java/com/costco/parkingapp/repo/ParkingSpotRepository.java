package com.costco.parkingapp.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.costco.parkingapp.domain.ParkingSpot;

public interface ParkingSpotRepository extends PagingAndSortingRepository<ParkingSpot, String> {
	ParkingSpot findBySpotNo(@Param("spotNo") String spotNo);
}
