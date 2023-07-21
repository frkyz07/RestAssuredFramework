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

import resources.TestDataBuilder;
import resources.Utils;

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

    @Given("^Add place payload$")
    public void add_place_payload() throws Throwable {


        // response builder build
        resSpec = (ResponseSpecification) new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();

        // request builder used
        response=given().spec(requestSpecification())
                .body(testData.addPlacePayload());

    }

    @When("^User calls \"([^\"]*)\"  with post http request$")
    public void user_calls_something_with_post_http_request(String strArg1) throws Throwable {

        res = response.when().post("/maps/api/place/add/json")
                .then().spec(resSpec).extract().response();
    }

    @Then("^The API call with success status code 200$")
    public void the_api_call_with_success_status_code_200() throws Throwable {
        assertEquals(res.getStatusCode(),200);
    }

    @And("^\"([^\"]*)\" call should be \"([^\"]*)\"$")
    public void something_call_should_be_something(String key, String value) throws Throwable {
        resp = res.asString();
        js = new JsonPath(resp);
        assertEquals(js.get(key).toString(),value);
    }


}