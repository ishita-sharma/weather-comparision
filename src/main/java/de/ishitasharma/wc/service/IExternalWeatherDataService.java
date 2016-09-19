package de.ishitasharma.wc.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IExternalWeatherDataService {

	public String compare(String firstCity, String secondCity)
			throws JsonParseException, JsonMappingException, IOException;
}
