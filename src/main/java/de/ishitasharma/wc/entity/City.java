package de.ishitasharma.wc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
	
	@JsonProperty("_id")
	private int cityId;
	
	@JsonProperty("name")
	private String cityName;
	
	@JsonProperty("country")
	private String countryName;
	
	@JsonProperty("coord")
	private CityCoordinates cityCoordinates;
	
	//Introducing the dummy constructor
	public City() {
	}
	
	public City(int cityId, String cityName, String countryName, CityCoordinates cityCoordinates) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.countryName = countryName;
		this.cityCoordinates = cityCoordinates;
	}
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public CityCoordinates getCityCoordinates() {
		return cityCoordinates;
	}
	public void setCityCoordinates(CityCoordinates cityCoordinates) {
		this.cityCoordinates = cityCoordinates;
	}


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
}
