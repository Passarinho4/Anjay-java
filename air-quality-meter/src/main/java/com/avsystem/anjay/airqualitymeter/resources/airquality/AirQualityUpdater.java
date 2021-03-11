package com.avsystem.anjay.airqualitymeter.resources.airquality;

import com.avsystem.anjay.airqualitymeter.services.RandomizerService;
import com.avsystem.anjay.airqualitymeter.services.WeatherAndQualityData;
import com.avsystem.anjay.airqualitymeter.services.WeatherAndQualityService;

import java.util.function.Consumer;

public class AirQualityUpdater implements Consumer<AirQuality> {

    private final WeatherAndQualityService service;

    public AirQualityUpdater(WeatherAndQualityService service) {
        this.service = service;
    }

    @Override
    public void accept(AirQuality airQuality) {
        WeatherAndQualityData randomized = RandomizerService.randomize(service.getData());
        airQuality.setPm10(randomized.getPm10());
        airQuality.setPm25(randomized.getPm25());
    }
}
