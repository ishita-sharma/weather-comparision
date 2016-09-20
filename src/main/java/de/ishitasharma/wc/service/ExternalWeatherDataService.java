package de.ishitasharma.wc.service;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.ishitasharma.wc.api.entity.ExternalWeatherDataDump;
import de.ishitasharma.wc.helper.JsonHelperUtil;
import de.ishitasharma.wc.helper.WeatherResponseHelper;

@Service
public class ExternalWeatherDataService implements IExternalWeatherDataService {

	private static final long CACHE_EXPIRY = 9000000;

	private JsonHelperUtil<ExternalWeatherDataDump> jsonHelper = new JsonHelperUtil<ExternalWeatherDataDump>();

	@Inject
	private WeatherResponseHelper weatherResponseHelper;

	private Map<String, ExternalWeatherDataDump> cachedWeatherData = new HashMap<String, ExternalWeatherDataDump>();

	@Override
	public String compare(String firstCity, String secondCity, String appId)
			throws JsonParseException, JsonMappingException,
			ClientProtocolException, IOException {

		ExternalWeatherDataDump externalWeatherDataDump1 = getWeatherDataFromApi(firstCity, appId);

		ExternalWeatherDataDump externalWeatherDataDump2 = getWeatherDataFromApi(secondCity, appId);

		String response = weatherResponseHelper.compareWeatherDataFromApi(
				externalWeatherDataDump1, externalWeatherDataDump2);

		return response;
	}

	private boolean isDataCachedAndAlive(String cityName) {
		return (cachedWeatherData.containsKey(cityName) && isCacheALive(cityName));
	}

	private boolean isCacheALive(String cityName) {
		return ((new GregorianCalendar().getTime().getTime()) / 1000L - cachedWeatherData
				.get(cityName).getDt()) < CACHE_EXPIRY;
	}

	private ExternalWeatherDataDump getWeatherDataFromApi(String cityName, String appId)
			throws ClientProtocolException, IOException {
		if (isDataCachedAndAlive(cityName)) {
			return cachedWeatherData.get(cityName);
		} else {
			String requestUrl = weatherResponseHelper.buildUrl(cityName,appId);
			ExternalWeatherDataDump weatherDataResponse = jsonHelper
					.deSerializeJsonToObject(weatherResponseHelper
							.getWeatherDataFromApi(requestUrl),
							ExternalWeatherDataDump.class);
			cacheWeatherData(cityName, weatherDataResponse);
			return weatherDataResponse;
		}
	}

	private void cacheWeatherData(String cityName,
			ExternalWeatherDataDump weatherData) {
		cachedWeatherData.put(cityName, weatherData);
	}
}
