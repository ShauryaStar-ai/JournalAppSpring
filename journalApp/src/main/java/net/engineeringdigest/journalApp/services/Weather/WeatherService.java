package net.engineeringdigest.journalApp.services.Weather;

import net.engineeringdigest.journalApp.cashe.AppCahse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
RestTemplate restTemplate = new RestTemplate();
        private String envVarName = "WeatherStack";
        @Autowired
        private AppCahse appCahse;
        // Load the API key from the environment
        private String apiKey = System.getenv(envVarName);

        // Construct the API URL dynamically
        public WeatherResponse getWeather(String city) {
                // Construct the URL dynamically using both the API key and the city parameter
                String unfomattedurl =  appCahse.app_cahse.get("weatherAPI");
                String formattedUrl = unfomattedurl
                        .replace("<apiKey>", apiKey)
                        .replace("<city>", city);

                ResponseEntity<WeatherResponse> response = restTemplate.exchange(formattedUrl, HttpMethod.GET,null, WeatherResponse.class);
                WeatherResponse body = response.getBody();
                return body;
        }


}
