package com.costco.parkingapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingSpot {
	public ParkingSpot(String spotNo, String spotOwner, Date releasedOn, Date revokeOn, int statusCode) {
		super();
		this.spotNo = spotNo;
		this.spotOwner = spotOwner;
		this.releasedOn = releasedOn;
		this.revokeOn = revokeOn;
		this.statusCode = statusCode;
	}

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private String spotNo;
	
	@Column
	private String spotOwner;
	
	@Column
	private Date releasedOn;
	
	@Column
	private Date revokeOn;
	
	@Column
	private int statusCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpotNo() {
		return spotNo;
	}

	public void setSpotNo(String spotNo) {
		this.spotNo = spotNo;
	}

	public String getSpotOwner() {
		return spotOwner;
	}

	public void setSpotOwner(String spotOwner) {
		this.spotOwner = spotOwner;
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

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((releasedOn == null) ? 0 : releasedOn.hashCode());
		result = prime * result + ((revokeOn == null) ? 0 : revokeOn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((spotNo == null) ? 0 : spotNo.hashCode());
		result = prime * result + ((spotOwner == null) ? 0 : spotOwner.hashCode());
		result = prime * result + statusCode;
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
		ParkingSpot other = (ParkingSpot) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (spotNo == null) {
			if (other.spotNo != null)
				return false;
		} else if (!spotNo.equals(other.spotNo))
			return false;
		if (spotOwner == null) {
			if (other.spotOwner != null)
				return false;
		} else if (!spotOwner.equals(other.spotOwner))
			return false;
		if (statusCode != other.statusCode)
			return false;
		return true;
	}
	
	
}
