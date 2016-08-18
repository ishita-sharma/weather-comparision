package de.ishitasharma.wc.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.ishitasharma.wc.api.entity.ExternalWeatherDataDump;
import de.ishitasharma.wc.helper.JsonHelperUtil;
import de.ishitasharma.wc.helper.WeatherResponseHelper;

@Component
public class ExternalWeatherDataService implements IExternalWeatherDataService{

	

	private String responseForFirstCity;
	private String responseForSecondCity;
	private ExternalWeatherDataDump externalWeatherDataDump1;
	private ExternalWeatherDataDump externalWeatherDataDump2;
	private JsonHelperUtil<ExternalWeatherDataDump> jsonHelper = new JsonHelperUtil<ExternalWeatherDataDump>();
	private WeatherResponseHelper weatherResponseHelper = new WeatherResponseHelper();

	@Override
	public String fetchData(String firstCity, String secondCity) throws JsonParseException, JsonMappingException, IOException {
		String request_Url_1 = weatherResponseHelper.buildUrl(firstCity);
		String request_Url_2 = weatherResponseHelper.buildUrl(secondCity);

		try {
			responseForFirstCity = weatherResponseHelper.getWeatherDataFromApi(request_Url_1);
			responseForSecondCity = weatherResponseHelper.getWeatherDataFromApi(request_Url_2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		externalWeatherDataDump1 = jsonHelper.deSerializeJsonToObject(
				responseForFirstCity, ExternalWeatherDataDump.class);
		
		externalWeatherDataDump2 = jsonHelper.deSerializeJsonToObject(
				responseForSecondCity, ExternalWeatherDataDump.class);
		
		String response = weatherResponseHelper.compareWeatherDataFromApi(externalWeatherDataDump1, externalWeatherDataDump2);

		return response;
	}



}
