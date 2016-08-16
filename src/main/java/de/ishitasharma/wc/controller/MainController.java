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

import de.ishitasharma.wc.entity.Message;
import de.ishitasharma.wc.service.ExternalWeatherDataService;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private ObjectMapper objectMapper = new ObjectMapper();


	@Inject
	private ExternalWeatherDataService externalWeatherDataService;

	@RequestMapping(value = "/fetch", method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<String> fetchData(
			@RequestParam(value = "firstCity", required = true) String firstCity,
			@RequestParam(value = "secondCity", required = true) String secondCity)
			throws JsonProcessingException {
		
		externalWeatherDataService.fetchData(firstCity, secondCity);
		
		Message message = new Message(responseForFirstCity,
				responseForSecondCity);
		return new ResponseEntity<String>(
				objectMapper.writeValueAsString(message), HttpStatus.OK);
	}
}
