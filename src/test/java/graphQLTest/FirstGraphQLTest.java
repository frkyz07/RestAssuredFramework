package graphQLTest;


import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
public class FirstGraphQLTest {

    public static void main(String[] args){

        // Mutations
        String mutationResponse = given().log().all().header("Content-type","application/json")
                .body("{\"query\":\"mutation\\n{  \\n  createLocation(location:{name:\\\"Aus\\\",type:\\\"nice\\\",dimension:\\\"good\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{name:\\\"Faruk\\\",type:\\\"turkish\\\",status:\\\"single\\\",\\n  \\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\tspecies:\\\"handsome\\\",gender:\\\"male\\\",image:\\\"to good\\\",\\n  \\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\t\\toriginId:7034,locationId:7034})\\n  {id}\\n}\",\"variables\":null}").
                when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();

        System.out.println(mutationResponse);
        JsonPath js = new JsonPath(mutationResponse);
        String id = js.getString("data.createCharacter.id");
        System.out.println(id);
        //Assert.assertEquals(id,"6199");

        //Query
        String response = given().log().all().header("Content-type","application/json")
                .body("{\"query\":\"query\\n{  \\n  \\n  charactersByIds(ids:6200){\\n    id\\n    name\\n    status\\n    species\\n    gender\\n    image\\n    \\n  }\\n\\n}\",\"variables\":null}\n").
                when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();

        System.out.println(response);
        JsonPath queryJs = new JsonPath(response);
        System.out.println(queryJs);


    }

}
