package de.ishitasharma.wc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ishitasharma.wc.entity.Message;

@Controller
@RequestMapping(value="/")
public class MainController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping(value="/fetch",
			method = { RequestMethod.GET },
			produces = { "application/json;charset=UTF-8" })
	public ResponseEntity <String> fetchData() throws JsonProcessingException {
		Message message = new Message("Hello",HttpStatus.OK);
		return new ResponseEntity<String>(objectMapper.writeValueAsString(message),HttpStatus.OK); 
	}
}
