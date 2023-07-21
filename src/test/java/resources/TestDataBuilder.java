package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public AddPlace addPlacePayload(){

        AddPlace a = new AddPlace();
        a.setAccuracy(50);
        a.setAddress("29, side layout, cohen 09");
        a.setLanguage("French-IN");
        a.setPhone("(+91) 983 893 3937");
        a.setName("Frontline house");
        a.setWebsite("http:/google.com");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        a.setType(myList);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        a.setLocation(l);

        return a;
    }

}
