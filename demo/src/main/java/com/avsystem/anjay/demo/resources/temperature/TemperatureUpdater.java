package com.avsystem.anjay.demo.resources.temperature;

import com.avsystem.anjay.demo.services.RandomizerService;
import com.avsystem.anjay.demo.services.WeatherAndQualityData;
import com.avsystem.anjay.demo.services.WeatherAndQualityService;

import java.util.function.Consumer;

public class TemperatureUpdater implements Consumer<Temperature> {

    private final WeatherAndQualityService service;

    public TemperatureUpdater(WeatherAndQualityService service) {
        this.service = service;
    }

    @Override
    public void accept(Temperature temperature) {
        WeatherAndQualityData randomized = RandomizerService.randomize(service.getData());
        temperature.setTemp(randomized.getTemperatureInCel());
    }
}
