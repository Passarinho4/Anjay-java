package com.avsystem.anjay.airqualitymeter.resources.temperature;

import com.avsystem.anjay.airqualitymeter.services.RandomizerService;
import com.avsystem.anjay.airqualitymeter.services.WeatherAndQualityData;
import com.avsystem.anjay.airqualitymeter.services.WeatherAndQualityService;

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
