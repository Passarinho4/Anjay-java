package com.avsystem.anjay.airqualitymeter.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorLocationService {

    public static Location getLocation() {
        Random random = new Random();
        try {
            Scanner scanner = new Scanner(new File("./cities.csv"));
            List<Location> locations = new ArrayList<>();
            while (scanner.hasNext()) {
                try {
                    String line = scanner.nextLine();
                    //Logger.getAnonymousLogger().log(Level.INFO, line);
                    String[] split = line.split(",");
                    Location loc = new Location(Float.parseFloat(split[1]), Float.parseFloat(split[2]), split[0]);
                    locations.add(loc);
                } catch (Exception a) {
                    //
                }
            }
            Logger.getAnonymousLogger().log(Level.INFO, "Loaded " + locations.size() + " cities.");
            Location location = locations.get(random.nextInt(locations.size()));
            Logger.getAnonymousLogger().log(Level.INFO, "Selected city = " + location.getCity());
            return location;
        } catch (Exception e) {
            Logger.getAnonymousLogger()
                    .log(Level.WARNING, "File with cities not found ", e);
            return new Location(0f,0f, "default-city");
        }
    }
}
