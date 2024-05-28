package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeviceData extends HarvestableArray {
    private static final InterfaceC6393e log = C6394f.m10150a();
    public static String oldUserId;
    public static String userId;
    private String carrier;
    private int connectType;
    private double latitude;
    private double longitude;
    private String netwrokType;

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.carrier));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.connectType)));
        jsonArray.add(new JsonPrimitive(this.netwrokType));
        setLocation();
        jsonArray.add(new JsonPrimitive((Number) Double.valueOf(this.latitude)));
        jsonArray.add(new JsonPrimitive((Number) Double.valueOf(this.longitude)));
        jsonArray.add(new JsonPrimitive(processValue()));
        return jsonArray;
    }

    private void setLocation() {
        if (C6638h.m8963w().m8967s() != null) {
            try {
                this.latitude = C6638h.m8963w().m8967s().getLatitude();
                this.longitude = C6638h.m8963w().m8967s().getLongitude();
            } catch (Exception e) {
                log.mo10121a("location is not null,but getLatitude() or getLongtitude() occur an error ", e);
            }
        }
    }

    public String processValue() {
        if (isSame()) {
            return "";
        }
        userId = C6638h.m8963w().m8964v();
        String str = userId;
        return str == null ? "" : str;
    }

    private boolean isSame() {
        return C6638h.m8963w().m8964v().equals(oldUserId);
    }

    public void setCarrier(String str) {
        this.carrier = str;
    }

    public void setConnectType(int i) {
        this.connectType = i;
    }

    public void setNetwrokType(String str) {
        this.netwrokType = str;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getNetwrokType() {
        return this.netwrokType;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public int getConnectType() {
        return this.connectType;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
