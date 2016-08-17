package de.ishitasharma.wc.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	
	@JsonProperty
	private ExternalWeatherDataDump text;

	public Message(ExternalWeatherDataDump response) {
		super();
		this.text = response;
	}

	public ExternalWeatherDataDump getText() {
		return text;
	}

	public void setText(ExternalWeatherDataDump text) {
		this.text = text;
	}
}
