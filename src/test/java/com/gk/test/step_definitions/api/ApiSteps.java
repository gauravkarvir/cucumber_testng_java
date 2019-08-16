package com.gk.test.step_definitions.api;

import com.gk.test.framework.helpers.ApiHelper;
import com.gk.test.models.api.ItemModel;
import com.gk.test.models.api.ResponseModel;
import com.gk.test.services.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class ApiSteps extends ApiHelper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Response response;


    /*   Perform a HTTP GET request for a endpoint*/

    @When("^I perform GET request for \"([^\"]*)\" endpoint$")
    public void I_perform_GET_request_for_endpoint(String endpoint) {
        response = Api.getList(endpoint);
    }

    /*   Verify HTTP Status code from response*/

    @Then("^I get a (\\d+) http status code$")
    public void I_get_a_http_status_code(int statusCodeExpected) {
        assertThat(response.statusCode()).isEqualTo(statusCodeExpected);
    }
    /* Example with JsonPath to extract names of colour form JSON response

     * Convert Response Object to asString(), which is Json Representation
     * use JsonPath "from" to convert the Response Object to Json String Representation
     * Access converted JSON String representation using locator e.g "colors.name"
     *  Example with XmlPath
     * String xml = post("/shopping").andReturn().body().asString()
     * Node category = from(xml).get("shopping.category[0]");

     */

    @Then("^the colour collections contains colour name$")
    public void the_colour_collections_contains_colour_name() {
        //Example with simple JsonPath
        List<String> colourNames = from(response.asString()).get("colors.name");
        assertThat(colourNames.size()).isGreaterThan(0);

        //Example with an Object Mapper using JsonPath
//        ResponseModel responseModelJson = from(response.asString()).get();
//        assertThat(responseModelJson.getColors().size()).isGreaterThan(0);
//        assertThat(responseModelJson.getHues().size()).isGreaterThan(0);

        List<ResponseModel.Colors> colors = from(response.asString()).get("colors");

        List<ResponseModel.Hues> hues = from(response.asString()).get("hues");
        assertThat(colors.size()).isGreaterThan(0);
        assertThat(hues.size()).isGreaterThan(0);

        //Example with Gson
        Gson gson = new GsonBuilder().create();

        ResponseModel responseModel = gson.fromJson(response.asString(), ResponseModel.class);

        assertThat(responseModel.getColors().size()).isGreaterThan(0);
        assertThat(responseModel.getHues().size()).isGreaterThan(0);

    }


    @When("I add an Item")
    public void i_add_an_Item(List<ItemModel> items) {
        response = Api.postDetails(items);
    }


    @When("I update an Item$")
    public void I_update_an_Item(List<ItemModel> items) {
        response = Api.updateDetails(items);
    }

    @When("^I delete an Item \"([^\"]*)\"$")
    public void I_delete_an_Item(String uniqueId) throws Throwable {
        response = Api.deleteItem(uniqueId);
    }


    @Then("^the Item is \"([^\"]*)\"$")
    public void the_Item_is(String result) throws Throwable {
        if (result.equals("created")) {
            assertThat(response.getStatusCode()).isEqualTo(201);
        } else if (result.equals("updated")) {
            assertThat(response.getStatusCode()).isEqualTo(200);
        } else if (result.equals("deleted")) {
            assertThat(response.getStatusCode()).isEqualTo(200);
        }

    }
}