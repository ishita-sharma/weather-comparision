package de.ishitasharma.wc.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ishitasharma.wc.api.entity.ExternalWeatherDataDump;
import de.ishitasharma.wc.api.entity.Message;
import de.ishitasharma.wc.service.IExternalWeatherDataService;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Inject
	private IExternalWeatherDataService externalWeatherDataService;

	@RequestMapping(value = "/fetch", method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<String> fetchData(
			@RequestParam(value = "firstCity", required = true) String firstCity,
			@RequestParam(value = "secondCity", required = true) String secondCity)
			throws IOException {
		
		String response = externalWeatherDataService.fetchData(firstCity, secondCity);
		
		Message message = new Message(response);
		return new ResponseEntity<String>(
				objectMapper.writeValueAsString(message), HttpStatus.OK);
	}
}
