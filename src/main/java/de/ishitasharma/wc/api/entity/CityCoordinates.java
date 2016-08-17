package de.ishitasharma.wc.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityCoordinates {
	
	@JsonProperty("lon")
	private double lon;
	
	@JsonProperty("lat")
	private double lat;
	
	//Introducing the dummy constructor
	public CityCoordinates() {
		
	}		
	
	public CityCoordinates(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
}