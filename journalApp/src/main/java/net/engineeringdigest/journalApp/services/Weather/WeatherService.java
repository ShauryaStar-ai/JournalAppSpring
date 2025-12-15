package net.engineeringdigest.journalApp.services.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
RestTemplate restTemplate = new RestTemplate();
        private String envVarName = "WeatherStack";

        // Load the API key from the environment
        private String apiKey = System.getenv(envVarName);

        // Construct the API URL dynamically
        public WeatherResponse getWeather(String city) {
                // Construct the URL dynamically using both the API key and the city parameter
                String APIWeatherStackFormattedUrl = String.format(
                        "http://api.weatherstack.com/current?access_key=%s&query=%s",
                        apiKey, city // replaces %s placeholders with apiKey and city
                );
                ResponseEntity<WeatherResponse> response = restTemplate.exchange(APIWeatherStackFormattedUrl, HttpMethod.GET,null, WeatherResponse.class);
                WeatherResponse body = response.getBody();
                return body;
        }


}
