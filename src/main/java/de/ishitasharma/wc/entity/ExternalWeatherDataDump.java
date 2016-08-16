package de.ishitasharma.wc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ExternalWeatherDataDump {

	@JsonProperty("coord")
	private CityCoordinates cityCoordinates;
	
	@JsonProperty("weather")
	private WeatherInfo weatherInfoList;
	
	@JsonProperty("base")
	private String base;
	
	@JsonProperty("main")
	private Main main;
	
	@JsonProperty("wind")
	private Wind wind;
	
	@JsonProperty("clouds")
	private Clouds clouds;
	
	@JsonProperty("dt")
	private int dt;
	
	@JsonProperty("sys")
	private Sys sys;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("cod")
	private int cod;

	public ExternalWeatherDataDump() {
		super();
	}

	public CityCoordinates getCityCoordinates() {
		return cityCoordinates;
	}

	public void setCityCoordinates(CityCoordinates cityCoordinates) {
		this.cityCoordinates = cityCoordinates;
	}

	public WeatherInfo getWeatherInfoList() {
		return weatherInfoList;
	}

	public void setWeatherInfoList(WeatherInfo weatherInfoList) {
		this.weatherInfoList = weatherInfoList;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
}
