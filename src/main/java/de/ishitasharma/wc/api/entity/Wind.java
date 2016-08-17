package de.ishitasharma.wc.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wind {

	@JsonProperty("speed")
	private double speed;
	
	@JsonProperty("deg")
	private double deg;
	
	@JsonProperty("gust")
	private int gust;

	public Wind() {
		super();
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDeg() {
		return deg;
	}

	public void setDeg(double deg) {
		this.deg = deg;
	}

	public int getGust() {
		return gust;
	}

	public void setGust(int gust) {
		this.gust = gust;
	}
}
