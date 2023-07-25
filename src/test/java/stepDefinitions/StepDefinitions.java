package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;

import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class StepDefinitions extends Utils{

    ResponseSpecification resSpec;
    RequestSpecification response;
    Response res;
    String resp;
    JsonPath js;
    TestDataBuilder testData = new TestDataBuilder();

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws Throwable {

        // request builder used
        response=given().spec(requestSpecification())
                .body(testData.addPlacePayload(name,language,address));

    }

    @When("User calls {string} with {string} http request")
    public void user_calls_something_with_post_http_request(String resource, String method) throws Throwable {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        resSpec = (ResponseSpecification) new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("Post")) {
            res = response.when().post(resourceAPI.getResource());
        }
        else if(method.equalsIgnoreCase("get")){
            res = response.when().get(resourceAPI.getResource());
        }
               // .then().spec(resSpec).extract().response();
    }

    @Then("^The API call with success status code 200$")
    public void the_api_call_with_success_status_code_200() throws Throwable {
        assertEquals(res.getStatusCode(),200);
    }

    @And("^\"([^\"]*)\" call should be \"([^\"]*)\"$")
    public void something_call_should_be_something(String key, String value) throws Throwable {

        assertEquals(getJsonPath(res,key),value);
    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws Throwable {

        String place_id = getJsonPath(res,"place_id");
        response=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_something_with_post_http_request(resourceName,"GET");
        String actualName = getJsonPath(res,"name");
        assertEquals(expectedName,actualName);

    }


}