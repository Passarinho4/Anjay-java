package com.avsystem.anjay.demo.services;

public class WeatherAndQualityData {

    private final Float temperatureInCel;
    private final Float pm10;
    private final Float pm25;

    public WeatherAndQualityData(Float temperatureInCel, Float pm10, Float pm25) {
        this.temperatureInCel = temperatureInCel;
        this.pm10 = pm10;
        this.pm25 = pm25;
    }

    public Float getPm25() {
        return pm25;
    }

    public Float getPm10() {
        return pm10;
    }

    public Float getTemperatureInCel() {
        return temperatureInCel;
    }
}
