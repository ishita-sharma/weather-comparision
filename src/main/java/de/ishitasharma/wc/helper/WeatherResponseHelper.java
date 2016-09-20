package de.ishitasharma.wc.helper;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import de.ishitasharma.wc.api.entity.ExternalWeatherDataDump;

@Component
public class WeatherResponseHelper {

	private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String APP_ID_PARAM = "&appid=";
	private static final String UNITS_PARAM = "&units=";
	private static final String UNITS_VALUE = "metric";
	private static final String ISWARMER = " is warmer than ";
	private static final String ISCOOLER = " is cooler than ";
	private static final String SPACE = " ";

	public String buildUrl(String cityName, String appId) {
		StringBuilder sb = new StringBuilder(REQUEST_URL);
		return sb.append(cityName).append(APP_ID_PARAM).append(appId)
				.append(UNITS_PARAM).append(UNITS_VALUE).toString();
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

	public String compareWeatherDataFromApi(
			ExternalWeatherDataDump externalWeatherDataDump1,
			ExternalWeatherDataDump externalWeatherDataDump2) {

		double tempFromCity1 = externalWeatherDataDump1.getMain().getTemp();
		double tempFromCity2 = externalWeatherDataDump2.getMain().getTemp();

		String nameOfFirstCity = externalWeatherDataDump1.getName();
		String nameOfSecondCity = externalWeatherDataDump2.getName();

		StringBuilder sb = new StringBuilder(nameOfFirstCity);

		if (tempFromCity1 > tempFromCity2) {
			return buildResponse(nameOfSecondCity, sb, ISWARMER, tempFromCity1,
					tempFromCity2);
		} else {
			return buildResponse(nameOfSecondCity, sb, ISCOOLER, tempFromCity1,
					tempFromCity2);
		}
	}

	private String buildResponse(String nameOfSecondCity, StringBuilder sb,
			String comparision, double tempFromCity1, double tempFromCity2) {
		return sb.append(SPACE).append(tempFromCity1).append(comparision)
				.append(nameOfSecondCity).append(SPACE).append(tempFromCity2)
				.toString();
	}

}
