package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SystemInfo extends HarvestableObject {
    private static InterfaceC6393e log = C6394f.m10150a();
    private int cpuUtilizationRateSystem;
    private int cpuUtilizationRateTotal;
    private int cpuUtilizationRateUser;
    private float memory;
    private double sessionDurationInSec;
    private long sessionRequestCount;

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("mem", new JsonPrimitive((Number) Float.valueOf(this.memory)));
        jsonObject.add("cpu_sys", new JsonPrimitive((Number) Integer.valueOf(this.cpuUtilizationRateSystem)));
        jsonObject.add("cpu_user", new JsonPrimitive((Number) Integer.valueOf(this.cpuUtilizationRateUser)));
        jsonObject.add("cpu", new JsonPrimitive((Number) Integer.valueOf(this.cpuUtilizationRateTotal)));
        if (C6638h.f17112l) {
            jsonObject.add("sd", new JsonPrimitive((Number) Double.valueOf((C6638h.f17109i - C6638h.f17108h) / 1.0E9d)));
            this.sessionRequestCount = C6638h.f17107g;
            jsonObject.add("req", new JsonPrimitive((Number) Long.valueOf(this.sessionRequestCount)));
            C6638h.f17107g = 0;
        }
        return jsonObject;
    }

    public double getSessionDurationInSec() {
        return this.sessionDurationInSec;
    }

    public void setSessionDurationInSec(double d) {
        this.sessionDurationInSec = d;
    }

    public long getSessionRequestCount() {
        return this.sessionRequestCount;
    }

    public void setSessionRequestCount(long j) {
        this.sessionRequestCount = j;
    }

    public void setMemory(float f) {
        this.memory = f;
    }

    public float getMemory() {
        return this.memory;
    }

    public void setCpuUtilizationRateSystem(int i) {
        this.cpuUtilizationRateSystem = i;
    }

    public int getCpuUtilizationRateSystem() {
        return this.cpuUtilizationRateSystem;
    }

    public void setCpuUtilizationRateUser(int i) {
        this.cpuUtilizationRateUser = i;
    }

    public int getCpuUtilizationRateUser() {
        return this.cpuUtilizationRateUser;
    }

    public void setCpuUtilizationRateTotal(int i) {
        this.cpuUtilizationRateTotal = i;
    }

    public int getCpuUtilizationRateTotal() {
        return this.cpuUtilizationRateTotal;
    }
}
