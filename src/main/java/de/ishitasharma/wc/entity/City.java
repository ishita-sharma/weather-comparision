package de.ishitasharma.wc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.ishitasharma.wc.api.entity.CityCoordinates;

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
}
