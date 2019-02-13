package de.ishitasharma.wc.service;


import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

//@RunWith(SpringJUnit4ClassRunner.class)
public class ExternalWeatherDataServiceImplTest {

    /*private static final String CITYNAME1 = "testCity1";
    private static final String CITYNAME2 = "testCity2";

    private ExternalWeatherDataService externalWeatherDataService;

    @Mock
    private WeatherResponseHelper weatherResponseHelper;

    private ComparisionResult expectedComparisionResult;
    private  ObjectNode nodeCity1;
    private  ObjectNode nodeCity2;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(weatherResponseHelper);
        expectedComparisionResult = new ComparisionResult(22.6,12, CITYNAME1, CITYNAME2);
        List<String> remarks = new ArrayList<>();
        remarks.add("more humid");
        expectedComparisionResult.setRemarks(remarks);

        nodeCity1 = JsonNodeFactory.instance.objectNode();
        nodeCity1.put("main", "{temp:42.6,humidity:34}");
        nodeCity1.put("name", CITYNAME1);

        nodeCity2 = JsonNodeFactory.instance.objectNode();
        nodeCity2.put("main", "{temp:20,humidity:22}");
        nodeCity2.put("name", CITYNAME2);

        //externalWeatherDataService = new ExternalWeatherDataServiceImpl(weatherResponseHelper);
    }*/

    @Ignore
    @Test
    public void testWeatherComparision() throws IOException {
        /*when(weatherResponseHelper.buildUrl(contains(CITYNAME1))).thenReturn(CITYNAME1);
        when(weatherResponseHelper.buildUrl(contains(CITYNAME2))).thenReturn(CITYNAME2);
        when(weatherResponseHelper.getWeatherDataFromApi(contains(CITYNAME1))).thenReturn(nodeCity1);
        when(weatherResponseHelper.getWeatherDataFromApi(contains(CITYNAME2))).thenReturn(nodeCity2);

        ComparisionResult actualComparisionResult = externalWeatherDataService.compare(CITYNAME1, CITYNAME2);

        assertEquals(expectedComparisionResult,actualComparisionResult);*/
    }

}
