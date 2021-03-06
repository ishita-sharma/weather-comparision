package de.ishitasharma.wc.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import de.ishitasharma.wc.entity.ComparisionResult;
import de.ishitasharma.wc.helper.WeatherResponseHelper;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;



@Service
public class ExternalWeatherDataServiceImpl implements ExternalWeatherDataService {

	private static final long CACHE_EXPIRY = 9000000;

	private WeatherResponseHelper weatherResponseHelper = new WeatherResponseHelper();

	private Map<String, JsonNode> cachedWeatherData = new HashMap<String, JsonNode>();

	@Override
	public ComparisionResult compare(String firstCity, String secondCity, String appId)
			throws JsonParseException, JsonMappingException,
			ClientProtocolException, IOException {

		JsonNode externalWeatherDataDump1 = getWeatherDataFromApi(firstCity, appId);

		JsonNode externalWeatherDataDump2 = getWeatherDataFromApi(secondCity, appId);

		ComparisionResult response = weatherResponseHelper.compareWeatherDataFromApi(
				externalWeatherDataDump1, externalWeatherDataDump2);

		return response;
	}

	private boolean isDataCachedAndAlive(String cityName) {
		return (cachedWeatherData.containsKey(cityName) && isCacheALive(cityName));
	}

	private boolean isCacheALive(String cityName) {
		return ((new GregorianCalendar().getTime().getTime()) / 1000L - cachedWeatherData
				.get(cityName).get("dt").asLong()) < CACHE_EXPIRY;
	}

	private JsonNode getWeatherDataFromApi(String cityName, String appId)
			throws ClientProtocolException, IOException {
		//if (isDataCachedAndAlive(cityName)) {
		//	return cachedWeatherData.get(cityName);
		//} else {
			String requestUrl = weatherResponseHelper.buildUrl(cityName, appId);

			JsonNode node = weatherResponseHelper.getWeatherDataFromApi(requestUrl);

			cacheWeatherData(cityName, node);
			return node;
		//}
	}

	private void cacheWeatherData(String cityName,
			JsonNode weatherData) {
		cachedWeatherData.put(cityName, weatherData);
	}
}
