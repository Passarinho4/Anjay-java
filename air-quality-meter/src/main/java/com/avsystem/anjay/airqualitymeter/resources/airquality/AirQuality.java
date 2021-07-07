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
package com.avsystem.anjay.airqualitymeter.resources.airquality;

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
