package com.costco.parkingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.costco.parkingapp.domain.ParkingSpot;
import com.costco.parkingapp.properties.ParkingStatusProperties;
import com.costco.parkingapp.repo.ParkingSpotRepository;

@Service
public class ParkingSpotService {
	private ParkingSpotRepository parkingSpotRepository;
	
	private final ParkingStatusProperties parkingStatusProperties;
	
	@Autowired
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, ParkingStatusProperties parkingStatusProperties) {
		this.parkingSpotRepository = parkingSpotRepository;
		this.parkingStatusProperties = parkingStatusProperties;
	}
	
	public ParkingSpot createParkingSpot(String spotNo, String spotOwner, Date releasedOn,  Date revokeOn, String parkingStatus) {
		return createParkingSpot(spotNo, spotOwner, releasedOn,  revokeOn, parkingStatusProperties.getParkingStatusCodeFromStatus(parkingStatus));
	}
	
	public String getParkingStatusFromStatusCode(int statusCode) {
		return parkingStatusProperties.getParkingStatusFromStatusCode(statusCode);
	}
	
	public ParkingSpot createParkingSpot(String spotNo, String spotOwner, Date releasedOn,  Date revokeOn, int statusCode) {
		if(spotNo != null) {
			ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNo(spotNo);
			if(parkingSpot != null) {
				parkingSpot.setSpotOwner(spotOwner);
				parkingSpot.setSpotNo(spotNo);
				parkingSpot.setStatusCode(statusCode);
				parkingSpot.setReleasedOn(releasedOn);
				parkingSpot.setRevokeOn(revokeOn);
				return parkingSpotRepository.save(parkingSpot);
			}
            return parkingSpotRepository.save(new ParkingSpot(spotNo, spotOwner, releasedOn, revokeOn, statusCode));
		} else {
            return null;
        }
	}
}
