package de.ishitasharma.wc.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import de.ishitasharma.wc.entity.ComparisionResult;

public interface IExternalWeatherDataService {

	public ComparisionResult compare(String firstCity, String secondCity, String appId)
			throws JsonParseException, JsonMappingException, IOException;
}
