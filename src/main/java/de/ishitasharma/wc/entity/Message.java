package de.ishitasharma.wc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	
	@JsonProperty
	private String responseForFirstCity;
	
	@JsonProperty
	private String responseForSecondCity;

	public Message(String responseForFirstCity, String responseForSecondCity) {
		super();
		this.responseForFirstCity = responseForFirstCity;
		this.responseForSecondCity = responseForSecondCity;
	}

	public String getResponseForFirstCity() {
		return responseForFirstCity;
	}

	public void setResponseForFirstCity(String responseForFirstCity) {
		this.responseForFirstCity = responseForFirstCity;
	}

	public String getResponseForSecondCity() {
		return responseForSecondCity;
	}

	public void setResponseForSecondCity(String responseForSecondCity) {
		this.responseForSecondCity = responseForSecondCity;
	}

}
