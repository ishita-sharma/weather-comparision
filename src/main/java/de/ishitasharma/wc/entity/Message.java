package de.ishitasharma.wc.entity;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	
	@JsonProperty
	private String text;
	
	@JsonProperty
	private HttpStatus status;

	public Message(String text, HttpStatus status) {
		super();
		this.text = text;
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
