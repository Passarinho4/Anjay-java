package com.avsystem.anjay.demo.resources.location;

import com.avsystem.anjay.AnjayObject;
import com.avsystem.anjay.AnjayOutputContext;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class Location implements AnjayObject {

    private Optional<Float> lat;
    private Optional<Float> lon;

    public Location(Float lat, Float lon) {
        this.lat = Optional.ofNullable(lat);
        this.lon = Optional.ofNullable(lon);
    }

    @Override
    public void resourceRead(int iid, int rid, AnjayOutputContext context) throws Exception {
        switch (rid) {
            case 0:
                context.retFloat(lat.get());
                break;
            case 1:
                context.retFloat(lon.get());
                break;
            default:
                throw new IllegalArgumentException("Unsupported resource " + rid);
        }
    }

    @Override
    public SortedSet<ResourceDef> resources(int iid) {
        TreeSet<ResourceDef> defs = new TreeSet<>();
        defs.add(new ResourceDef(0, ResourceKind.R, lat.isPresent()));
        defs.add(new ResourceDef(1, ResourceKind.R, lon.isPresent()));
        return defs;
    }

    @Override
    public int oid() {
        return 6;
    }

    @Override
    public SortedSet<Integer> instances() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        return set;
    }

    @Override
    public void instanceReset(int iid) throws Exception {
        lat = Optional.empty();
        lon = Optional.empty();
    }

}
