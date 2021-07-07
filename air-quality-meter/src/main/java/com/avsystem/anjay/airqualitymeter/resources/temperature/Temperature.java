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
package com.avsystem.anjay.airqualitymeter.resources.temperature;

import com.avsystem.anjay.AnjayObject;
import com.avsystem.anjay.AnjayOutputContext;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class Temperature implements AnjayObject {

    private Optional<Float> temp = Optional.empty();
    private Optional<String> unit = Optional.empty();

    public void setTemp(Float temp) {
        this.temp = Optional.of(temp);
    }

    public void setUnit(String unit) {
        this.unit = Optional.of(unit);
    }

    @Override
    public SortedSet<ResourceDef> resources(int iid) {
        TreeSet<ResourceDef> defs = new TreeSet<>();
        defs.add(new ResourceDef(5700, ResourceKind.R, temp.isPresent()));
        defs.add(new ResourceDef(5701, ResourceKind.R, unit.isPresent()));
        return defs;
    }

    @Override
    public void resourceRead(int iid, int rid, AnjayOutputContext context) throws Exception {
        switch (rid) {
            case 5700:
                context.retFloat(temp.get());
                break;
            case 5701:
                context.retString(unit.get());
                break;
            default:
                throw new IllegalArgumentException("Unsupported resource " + rid);
        }

    }

    @Override
    public int oid() {
        return 3303;
    }

    @Override
    public void instanceReset(int iid) throws Exception {
        temp = Optional.empty();
        unit = Optional.empty();
    }

    @Override
    public SortedSet<Integer> instances() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        return treeSet;
    }
}
