package de.ishitasharma.wc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ishitasharma.wc.entity.ComparisionResult;
import de.ishitasharma.wc.service.IExternalWeatherDataService;
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
	private IExternalWeatherDataService externalWeatherDataService;

	@RequestMapping(value = "/compare", method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<String> compare(
			@RequestParam(value = "firstCity", required = true) String firstCity,
			@RequestParam(value = "secondCity", required = true) String secondCity,
			@RequestParam(value = "appid", required = true) String appId)
			throws IOException {

		ComparisionResult response = externalWeatherDataService.compare(firstCity,
				secondCity, appId);

		return new ResponseEntity<String>(objectMapper.writeValueAsString(response), HttpStatus.OK);
	}
}
