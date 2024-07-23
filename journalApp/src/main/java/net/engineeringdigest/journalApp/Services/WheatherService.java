package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.ApiResponse.WheatherResponsePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WheatherService {
    private static final String API_KEY="8d0331236b234348be4122925242307";

    private static final String API="http://api.weatherapi.com/v1/current.json?key=APIKEY&q=CITY&aqi=no";

    @Autowired
    RestTemplate restTemplate;

    public WheatherResponsePojo getWheather(String city){
        String finalAPI=API.replace("CITY",city).replace("APIKEY",API_KEY);
        ResponseEntity<WheatherResponsePojo> responsePojoResponseEntity = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WheatherResponsePojo.class);
        WheatherResponsePojo body = responsePojoResponseEntity.getBody();
        return body;
    }

}
