package de.ishitasharma.wc.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clouds {

	@JsonProperty("all")
	private int all;

	public Clouds() {
		super();
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

}
