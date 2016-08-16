package de.ishitasharma.wc.controller;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ishitasharma.wc.entity.Message;

@Controller
@RequestMapping(value="/")
public class MainController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String APP_ID_PARAM = "&appid=";
	private static final String APP_ID = "4bfe910efecbc99e6539b119c6be5770";
	
	private String resposeFromWeatherApi;
	
	@RequestMapping(value="/fetch",
			method = { RequestMethod.GET },
			produces = { "application/json;charset=UTF-8" })
	public ResponseEntity <String> fetchData(
			@RequestParam(value = "firstCity", required = true) String firstCity,
			@RequestParam(value = "secondCity", required = true) String secondCity) 
					throws JsonProcessingException {
		String request_Url_1 = buildUrl(firstCity);
		String request_Url_2 = buildUrl(firstCity);
		
		try {
			resposeFromWeatherApi = getWeatherDataFromApi(request_Url_1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Message message = new Message(resposeFromWeatherApi,HttpStatus.OK);
		return new ResponseEntity<String>(objectMapper.writeValueAsString(message),HttpStatus.OK); 
	}

	private String buildUrl(String cityName) {
		StringBuilder sb = new StringBuilder(REQUEST_URL);
		return sb.append(cityName).append(APP_ID_PARAM).append(APP_ID).toString();
	}
	
	private String getWeatherDataFromApi(String request_url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(request_url);
		getRequest.addHeader("accept", "application/xml");
		HttpResponse response = httpClient.execute(getRequest);
		int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) 
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        
        httpClient.getConnectionManager().shutdown();
        
        return apiOutput;
	}
}
