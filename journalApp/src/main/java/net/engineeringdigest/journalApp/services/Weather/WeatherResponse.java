package net.engineeringdigest.journalApp.services.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponse {

    private Current current;

    public Current getCurrent() {
        return current;
    }

    // ===== Current =====
    public static class Current {

        private int temperature;

        public int getTemperature() {
            return temperature;
        }
    }
}
