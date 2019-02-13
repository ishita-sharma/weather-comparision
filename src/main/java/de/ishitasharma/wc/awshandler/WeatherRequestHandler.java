package de.ishitasharma.wc.awshandler;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ishitasharma.wc.entity.ComparisionResult;
import de.ishitasharma.wc.service.ExternalWeatherDataService;
import de.ishitasharma.wc.service.ExternalWeatherDataServiceImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.*;

/**
 * Created by ish on 08.01.19.
 */
public class WeatherRequestHandler implements RequestStreamHandler {
    JSONParser parser = new JSONParser();

    private ExternalWeatherDataService externalWeatherDataService = new ExternalWeatherDataServiceImpl();


    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");


        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject responseBody = new JSONObject();
        String city1 = null;
        String city2 = null;

        try {
            JsonNode event = objectMapper.readTree(inputStream);
            city1 = event.get("multiValueQueryStringParameters").get("firstCity").get(0).asText();
            city2 = event.get("multiValueQueryStringParameters").get("secondCity").get(0).asText();
            String appId = System.getenv("appId");


            ComparisionResult comparisionResult = externalWeatherDataService.compare(city1, city2, appId);


            JSONObject headerJson = new JSONObject();
            headerJson.put("Content-Type", "application/json");

            responseBody.put("isBase64Encoded", false);
            responseBody.put("statusCode", HttpStatus.OK);
            responseBody.put("headers", headerJson);
            responseBody.put("body", objectMapper.writeValueAsString(comparisionResult));

        }  catch(Exception pex) {
            responseBody.put("statusCode", "400");
            responseBody.put("exception", pex.getMessage());
        }

        logger.log(responseBody.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseBody.toJSONString());
        writer.close();
    }
}
