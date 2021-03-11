/*
 * Copyright 2020 AVSystem <avsystem@avsystem.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.avsystem.anjay.airqualitymeter.resources.location;

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
