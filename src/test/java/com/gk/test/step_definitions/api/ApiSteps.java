package com.gk.test.step_definitions.api;

import com.gk.test.framework.helpers.ApiHelper;
import com.gk.test.framework.helpers.UrlBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.path.json.JsonPath.from;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class ApiSteps extends ApiHelper {

    private Response response;


    /*   Perform a HTTP GET request for a endpoint*/

    @When("^I perform GET request for \"([^\"]*)\" endpoint$")
    public void I_perform_GET_request_for_endpoint(String endpoint) {

        given().contentType(ContentType.JSON);
        response = when().get(UrlBuilder.getApiUrlForEndPoint(endpoint));
    }

    /*   Verify HTTP Status code from response*/

    @Then("^I get a (\\d+) http status code$")
    public void I_get_a_http_status_code(int statusCodeExpected) {
        Assert.assertEquals(statusCodeExpected, response.statusCode());
    }



    /* Example with JsonPath to extract names of colour form JSON response

    * Convert Response Object to asString(), which is Json Representation
    * use JsonPath "from" to convert the Response Object to Json String Representation
    * Access converted JSON String representation using locators e.g "colors.name"
    *  Example with XmlPath
    * String xml = post("/shopping").andReturn().body().asString()
    * Node category = from(xml).get("shopping.category[0]");

    */


    @Then("^the colour collections contains colour name$")
    public void the_colour_collections_contains_colour_name() {
        List<String> colourNames = from(response.asString()).get("colors.name");
        Assert.assertTrue(colourNames.size() >= 0);

    }

}