package de.ishitasharma.wc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ishitasharma.wc.entity.ComparisionResult;
import de.ishitasharma.wc.service.ExternalWeatherDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class MainController {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Inject
	private ExternalWeatherDataService externalWeatherDataService;

	@RequestMapping(value = "/compare", method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<String> compare(
			@RequestParam(value = "firstCity", required = true) String firstCity,
			@RequestParam(value = "secondCity", required = true) String secondCity)
			throws IOException {

		ComparisionResult response = externalWeatherDataService.compare(firstCity, secondCity, "");

		return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.OK);
	}
}
