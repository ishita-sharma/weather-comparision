package de.ishitasharma.wc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {

	@JsonProperty("temp")
	private double temp;
	
	@JsonProperty("pressure")
	private double pressure;
	
	@JsonProperty("humidity")
	private int humidity;
	
	@JsonProperty("temp_min")
	private double temp_min;
	
	@JsonProperty("temp_max")
	private double temp_max;

	public Main() {
		super();
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	
}
