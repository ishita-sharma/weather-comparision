package de.ishitasharma.wc.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ishitasharma.wc.entity.ComparisionResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class WeatherResponseHelperTest {

    private static final String CITYNAME1 = "testCity1";
    private static final String CITYNAME2 = "testCity2";

    private ComparisionResult expectedComparisionResult;
    private JsonNode nodeCity1;
    private  JsonNode nodeCity2;
    private WeatherResponseHelper weatherResponseHelper = new WeatherResponseHelper();

    private String jsonRespSample1 = "{\"main\": {\"temp\":42.6,\"humidity\":34}, \"name\": \"testCity1\"}";
    private String jsonRespSample2 = "{\"main\": {\"temp\":20,\"humidity\":22}, \"name\": \"testCity2\"}";

    @Before
    public void init() throws IOException{
        expectedComparisionResult = new ComparisionResult(22.6,12, CITYNAME1, CITYNAME2);
        List<String> remarks = new ArrayList<>();
        remarks.add("warmer");
        remarks.add("more humid");

        expectedComparisionResult.setRemarks(remarks);


        ObjectMapper mapper = new ObjectMapper();

        nodeCity1 = mapper.readTree(jsonRespSample1);
        nodeCity2 = mapper.readTree(jsonRespSample2);
    }

    @Test
    public void testWeatherComparisionCalculations() throws IOException {
        ComparisionResult actualComparisionResult = weatherResponseHelper.compareWeatherDataFromApi(nodeCity1, nodeCity2);

        Assert.assertEquals("Remark should be as expected", expectedComparisionResult.getRemarks(),actualComparisionResult.getRemarks());
        Assert.assertTrue("Temperature difference should be as expected", Double.compare(expectedComparisionResult.getTemp_diff(), actualComparisionResult.getTemp_diff())<0.001d);
        Assert.assertTrue("Humidity difference should be as expected", Double.compare(expectedComparisionResult.getHumidity_diff(), actualComparisionResult.getHumidity_diff())<0.001d);
    }
}
