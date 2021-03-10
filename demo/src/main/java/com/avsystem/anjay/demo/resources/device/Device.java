package com.avsystem.anjay.demo.resources.device;

import com.avsystem.anjay.AnjayObject;
import com.avsystem.anjay.AnjayOutputContext;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class Device implements AnjayObject {

    private Optional<String> modelNumber;

    public Device(String modelNumber) {
        this.modelNumber = Optional.ofNullable(modelNumber);
    }

    @Override
    public SortedSet<ResourceDef> resources(int iid) {
        TreeSet<ResourceDef> defs = new TreeSet<>();
        defs.add(new ResourceDef(1, ResourceKind.R, modelNumber.isPresent()));
        return defs;
    }

    @Override
    public void resourceRead(int iid, int rid, AnjayOutputContext context) throws Exception {
        switch (rid) {
            case 1:
                context.retString(modelNumber.get());
                break;
            default:
                throw new IllegalArgumentException("Unsupported resource " + rid);
        }
    }

    @Override
    public int oid() {
        return 3;
    }

    @Override
    public SortedSet<Integer> instances() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        return set;
    }

    @Override
    public void instanceReset(int iid) throws Exception {
        modelNumber = Optional.empty();
    }

}
