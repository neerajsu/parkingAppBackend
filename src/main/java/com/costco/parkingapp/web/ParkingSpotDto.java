package com.costco.parkingapp.web;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ParkingSpotDto {
	public ParkingSpotDto(@NotNull @Pattern(regexp = "[a-zA-Z0-9- ]+") @NotEmpty String parkingSpot,
			String parkingSpotOwner, Date releasedOn, Date revokeOn, String parkingStatus) {
		super();
		this.parkingSpot = parkingSpot;
		this.parkingSpotOwner = parkingSpotOwner;
		this.releasedOn = releasedOn;
		this.revokeOn = revokeOn;
		this.parkingStatus = parkingStatus;
	}

	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9- ]+")
	@NotEmpty
	private String parkingSpot;

	private String parkingSpotOwner;

	private Date releasedOn;

	private Date revokeOn;

	private String parkingStatus;

	public String getParkingSpot() {
		return parkingSpot;
	}

	public void setParkingSpot(String parkingSpot) {
		this.parkingSpot = parkingSpot;
	}

	public String getParkingSpotOwner() {
		return parkingSpotOwner;
	}

	public void setParkingSpotOwner(String parkingSpotOwner) {
		this.parkingSpotOwner = parkingSpotOwner;
	}

	public Date getReleasedOn() {
		return releasedOn;
	}

	public void setReleasedOn(Date releasedOn) {
		this.releasedOn = releasedOn;
	}

	public Date getRevokeOn() {
		return revokeOn;
	}

	public void setRevokeOn(Date revokeOn) {
		this.revokeOn = revokeOn;
	}

	public String getParkingStatus() {
		return parkingStatus;
	}

	public void setParkingStatus(String parkingStatus) {
		this.parkingStatus = parkingStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parkingSpot == null) ? 0 : parkingSpot.hashCode());
		result = prime * result + ((parkingSpotOwner == null) ? 0 : parkingSpotOwner.hashCode());
		result = prime * result + ((parkingStatus == null) ? 0 : parkingStatus.hashCode());
		result = prime * result + ((releasedOn == null) ? 0 : releasedOn.hashCode());
		result = prime * result + ((revokeOn == null) ? 0 : revokeOn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingSpotDto other = (ParkingSpotDto) obj;
		if (parkingSpot == null) {
			if (other.parkingSpot != null)
				return false;
		} else if (!parkingSpot.equals(other.parkingSpot))
			return false;
		if (parkingSpotOwner == null) {
			if (other.parkingSpotOwner != null)
				return false;
		} else if (!parkingSpotOwner.equals(other.parkingSpotOwner))
			return false;
		if (parkingStatus == null) {
			if (other.parkingStatus != null)
				return false;
		} else if (!parkingStatus.equals(other.parkingStatus))
			return false;
		if (releasedOn == null) {
			if (other.releasedOn != null)
				return false;
		} else if (!releasedOn.equals(other.releasedOn))
			return false;
		if (revokeOn == null) {
			if (other.revokeOn != null)
				return false;
		} else if (!revokeOn.equals(other.revokeOn))
			return false;
		return true;
	}

}
