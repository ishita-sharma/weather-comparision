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
import de.ishitasharma.wc.util.JsonHelperUtil;

@Component
public class ExternalWeatherDataService {

	private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String APP_ID_PARAM = "&appid=";
	private static final String APP_ID = "4bfe910efecbc99e6539b119c6be5770";

	private String responseForFirstCity;
	private String responseForSecondCity;
	private ExternalWeatherDataDump externalWeatherDataDump1;
	private ExternalWeatherDataDump externalWeatherDataDump2;
	private JsonHelperUtil<ExternalWeatherDataDump> jsonHelper = new JsonHelperUtil<ExternalWeatherDataDump>();

	public ExternalWeatherDataDump fetchData(String firstCity, String secondCity) throws JsonParseException, JsonMappingException, IOException {
		String request_Url_1 = buildUrl(firstCity);
		String request_Url_2 = buildUrl(secondCity);

		try {
			responseForFirstCity = getWeatherDataFromApi(request_Url_1);
			responseForSecondCity = getWeatherDataFromApi(request_Url_2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		externalWeatherDataDump1 = jsonHelper.deSerializeJsonToObject(
				responseForFirstCity, ExternalWeatherDataDump.class);
		
		externalWeatherDataDump2 = jsonHelper.deSerializeJsonToObject(
				responseForSecondCity, ExternalWeatherDataDump.class);

		return externalWeatherDataDump1;
	}

	public String getWeatherDataFromApi(String request_url)
			throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(request_url);
		getRequest.addHeader("accept", "application/xml");
		HttpResponse response = httpClient.execute(getRequest);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : "
					+ statusCode);
		}
		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		httpClient.getConnectionManager().shutdown();

		return apiOutput;
	}

	private String buildUrl(String cityName) {
		StringBuilder sb = new StringBuilder(REQUEST_URL);
		return sb.append(cityName).append(APP_ID_PARAM).append(APP_ID)
				.toString();
	}

}
