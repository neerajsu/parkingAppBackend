package com.costco.parkingapp.properties;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app-data.properties")
@ConfigurationProperties(prefix = "app-data")
public class ParkingStatusProperties {
	private Map<String, String> parkingStatus;

	public Map<String, String> getParkingStatus() {
		return parkingStatus;
	}

	public void setParkingStatus(Map<String, String> parkingStatus) {
		this.parkingStatus = parkingStatus;
	}

	public int getParkingStatusCodeFromStatus(String parkingStatus) {
		for (Entry<String, String> entry : this.parkingStatus.entrySet()) {
			if (entry.getValue().equals(parkingStatus)) {
				return Integer.parseInt(entry.getKey());
			}
		}
		return 0;
	}
	
	public String getParkingStatusFromStatusCode(int statusCode) {
		return	this.parkingStatus.get(String.valueOf(statusCode));
	}
}
