package com.avsystem.anjay.demo.resources.airQuality;

import com.avsystem.anjay.AnjayObject;
import com.avsystem.anjay.AnjayOutputContext;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class AirQuality implements AnjayObject {

    private Optional<Float> pm25 = Optional.empty();
    private Optional<Float> pm10 = Optional.empty();


    public void setPm25(Float value) {
        this.pm25 = Optional.of(value);
    }

    public void setPm10(Float value) {
        this.pm10 = Optional.of(value);
    }

    @Override
    public SortedSet<ResourceDef> resources(int iid) {
        TreeSet<ResourceDef> defs = new TreeSet<>();
        defs.add(new ResourceDef(1, ResourceKind.R, pm10.isPresent()));
        defs.add(new ResourceDef(3, ResourceKind.R, pm25.isPresent()));
        return defs;
    }

    @Override
    public void resourceRead(int iid, int rid, AnjayOutputContext context) throws Exception {
        switch (rid) {
            case 1:
                context.retFloat(pm10.get());
                break;
            case 3:
                context.retFloat(pm25.get());
                break;
            default:
                throw new IllegalArgumentException("Unsupported resource " + rid);
        }
    }

    @Override
    public int oid() {
        return 3428;
    }

    @Override
    public void instanceReset(int iid) throws Exception {
        pm10 = Optional.empty();
        pm25 = Optional.empty();
    }

    @Override
    public SortedSet<Integer> instances() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        return treeSet;
    }

}
