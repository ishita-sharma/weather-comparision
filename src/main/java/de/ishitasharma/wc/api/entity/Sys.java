package de.ishitasharma.wc.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys {

	@JsonProperty("type")
	private int type;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("message")
	private double message;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("sunrise")
	private int sunrise;
	
	@JsonProperty("sunset")
	private int sunset;

	public Sys() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMessage() {
		return message;
	}

	public void setMessage(double message) {
		this.message = message;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getSunrise() {
		return sunrise;
	}

	public void setSunrise(int sunrise) {
		this.sunrise = sunrise;
	}

	public int getSunset() {
		return sunset;
	}

	public void setSunset(int sunset) {
		this.sunset = sunset;
	}
}
