package springboot.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import springboot.configuration.Connection;
import springboot.model.response.ResponseWrapper;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bakti.pratama on 1/18/2017.
 */
public class HttpHelperTest {

  private static final String BASE_URL = "/test/wiremock";
  HttpHelper httpHelper;


  @Rule
  public WireMockRule wireMockRule =  new WireMockRule(wireMockConfig().port(8080));

  @Before
  public void setup() {
    httpHelper = new HttpHelper(OkHttpFactory.create(new Connection()));

    stubFor(post(urlMatching (BASE_URL))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{ \"code\": 200, \"message\": \"OK\", \"errors\": null, \"value\":true }")));

    stubFor(get(urlMatching (BASE_URL+"?.*"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{ \"code\": 200, \"message\": \"OK\", \"errors\": null, \"value\":true }")));
  }

  @Test
  public void getTestSuccess() throws Exception {
    ResponseWrapper<Boolean> result = httpHelper.callGetApi(BASE_URL,new TypeReference<ResponseWrapper<Boolean>>() {});
    assertTrue(200 == result.getCode());
    assertTrue("OK".equals(result.getMessage()));
    assertTrue(result.getValue());
  }

  @Test
  public void postTestSuccess() throws Exception {
    ResponseWrapper<Boolean> result = httpHelper.callPostApi(BASE_URL,true,new
        TypeReference<ResponseWrapper<Boolean>>() {});
    assertTrue(200 == result.getCode());
    assertTrue("OK".equals(result.getMessage()));
    assertTrue(result.getValue());
  }

}
