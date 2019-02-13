package de.ishitasharma.wc.handler;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;

import de.ishitasharma.wc.awshandler.WeatherRequestHandler;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class WeatherRequestHandlerTest {

    private static WeatherRequestHandler handler;
    private static Context lambdaContext;

    @BeforeClass
    public static void setUp() {
        handler = new WeatherRequestHandler();
        lambdaContext = new MockLambdaContext();
    }

    @Ignore
    @Test
    public void compareWeather() {
        InputStream requestStream = new AwsProxyRequestBuilder("/", HttpMethod.GET).queryString("firstCity", "Jaipur").queryString("secondCity", "London")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .buildStream();
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

        handle(requestStream, responseStream);

        AwsProxyResponse response = readResponse(responseStream);
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());

        assertFalse(response.isBase64Encoded());

        assertTrue(response.getBody().contains("tempDiff"));

        assertTrue(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
        assertTrue(response.getHeaders().get(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
    }

    private void handle(InputStream is, ByteArrayOutputStream os) {
        try {
            handler.handleRequest(is, os, lambdaContext);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private AwsProxyResponse readResponse(ByteArrayOutputStream responseStream) {
        try {
            return LambdaContainerHandler.getObjectMapper().readValue(responseStream.toByteArray(), AwsProxyResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error while parsing response: " + e.getMessage());
        }
        return null;
    }


}
