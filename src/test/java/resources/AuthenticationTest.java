package resources;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class AuthenticationTest {

        public static void main(String[] args){

            String response = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                    formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
                    formParams("grant_type","client_credentials").
                    formParams("scope","trust").
                    when().log().all().
                    post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

            System.out.println(response);
            JsonPath jsonPath = new JsonPath(response);
            String accessToken = jsonPath.getString("access_token");

            String authentication = given().queryParam("access_token",accessToken).
                    when().log().all().
                    get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

            System.out.println(authentication);
        }
}
