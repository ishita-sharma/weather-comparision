package de.ishitasharma.wc.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ishitasharma.wc.entity.ComparisionResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherResponseHelper implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(WeatherResponseHelper.class);

	private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String APP_ID_PARAM = "&appid=";
	private static final String UNITS_PARAM = "&units=";
	private static final String UNITS_VALUE = "metric";
	private static final String ISWARMER = "warmer";
	private static final String ISCOOLER = "cooler";
	private static final String MORE_HUMID = "more humid";
	private static final String LESS_HUMID = "less humid";

	@Autowired
	private Environment environment;

	private String appId;

	public String buildUrl(String cityName) {
		StringBuilder sb = new StringBuilder(REQUEST_URL);
		return sb.append(cityName).append(APP_ID_PARAM).append(appId)
				.append(UNITS_PARAM).append(UNITS_VALUE).toString();
	}

	public JsonNode getWeatherDataFromApi(String request_url) {

		HttpGet getRequest = new HttpGet(request_url);
		getRequest.addHeader("accept", "application/xml");

		HttpResponse response = null;
		JsonNode node = null;

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

			response = httpClient.execute(getRequest);
			HttpEntity entity = response.getEntity();

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			try (InputStream is = entity.getContent()) {
				ObjectMapper mapper = new ObjectMapper();
				node = mapper.readTree(is);
			} catch (IOException e) {
				LOG.error("Problem reading  from inputstream ", e);
			}
		} catch (IOException e) {
			LOG.error("Problem establishing HTTP connection ", e);
		}

		return node;
	}


	public ComparisionResult compareWeatherDataFromApi(
			JsonNode externalWeatherDataDump1,
			JsonNode externalWeatherDataDump2) {

		double tempFromCity1 = externalWeatherDataDump1.get("main").get("temp").asDouble();
		double tempFromCity2 = externalWeatherDataDump2.get("main").get("temp").asDouble();
		double temp_diff = tempFromCity1-tempFromCity2;

		float humidityFromCity1 = externalWeatherDataDump1.get("main").get("humidity").floatValue();
		float humidityFromCity2 = externalWeatherDataDump2.get("main").get("humidity").floatValue();
		float humidity_diff = humidityFromCity1 - humidityFromCity2;

		String nameOfFirstCity = externalWeatherDataDump1.get("name").asText();
		String nameOfSecondCity = externalWeatherDataDump2.get("name").asText();

		ComparisionResult responseEntity = new ComparisionResult(temp_diff, humidity_diff, nameOfFirstCity,nameOfSecondCity);

		List<String> remarks = new ArrayList<>();

		if (tempFromCity1 > tempFromCity2) {
			remarks.add(ISWARMER);
		} else {
			remarks.add(ISCOOLER);
		}
		if (humidityFromCity1 > humidityFromCity2) {
			remarks.add(MORE_HUMID);
		} else {
			remarks.add(LESS_HUMID);
		}

		responseEntity.setRemarks(remarks);

		return responseEntity;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.appId = environment.getProperty("app_id");
	}
}
