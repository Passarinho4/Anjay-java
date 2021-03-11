package com.avsystem.anjay.airqualitymeter.services;

public class RandomizerService {

    public static WeatherAndQualityData randomize(WeatherAndQualityData data) {
        float tempRandomModifier = (float) (Math.random() * 2);
        float pm10RandomModifier = (float) (Math.random() * 5);
        float pm25RandomModifier = (float) (Math.random() * 5);
        return new WeatherAndQualityData(data.getTemperatureInCel() + tempRandomModifier,
                data.getPm10() + pm10RandomModifier, data.getPm25() + pm25RandomModifier);
    }

}
