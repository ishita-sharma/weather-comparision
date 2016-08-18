package de.ishitasharma.wc.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	
	@JsonProperty
	private String text;

	public Message(String response) {
		super();
		this.text = response;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
