package stepDefinitions;


import io.cucumber.java.Before;

public class Hooks {


    @Before("@DeletePlace")
    public void beforeDeletePlace() throws Throwable {

        StepDefinitions sd= new StepDefinitions();

        if(StepDefinitions.place_id==null)
        {
            sd.add_place_payload("Faruk","English","Middle East");
            sd.user_calls_something_with_post_http_request("addPlaceAPI","Post");
            sd.verify_place_id_created_maps_to_using("Faruk","getPlaceAPI");
        }
    }
}
