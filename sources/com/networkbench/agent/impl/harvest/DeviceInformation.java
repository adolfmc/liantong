package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeviceInformation extends HarvestableArray {
    private String agentName;
    private String agentVersion;
    private String brsAgentMd5;
    private String countryCode;
    private String deviceId;
    private String manufacturer;
    private Map<String, Object> misc = new HashMap();
    private String model;
    private String osName;
    private String osVersion;
    private String regionCode;
    private double screenSize;

    public String getBrsAgentMd5() {
        return this.brsAgentMd5;
    }

    public void setBrsAgentMd5(String str) {
        this.brsAgentMd5 = str;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        m9932g("" + this.screenSize);
        jsonArray.add(new JsonPrimitive(this.screenSize + ""));
        m9932g(this.manufacturer);
        jsonArray.add(new JsonPrimitive(this.manufacturer));
        m9932g(this.model);
        jsonArray.add(new JsonPrimitive(this.model));
        m9932g(this.osName);
        jsonArray.add(new JsonPrimitive(this.osName));
        m9932g(this.osVersion);
        jsonArray.add(new JsonPrimitive(this.osVersion));
        m9932g(this.agentName);
        jsonArray.add(new JsonPrimitive(this.agentName));
        m9932g(this.agentVersion);
        jsonArray.add(new JsonPrimitive(this.agentVersion));
        Map<String, Object> map = this.misc;
        if (map == null || map.isEmpty()) {
            this.misc = Collections.emptyMap();
        }
        jsonArray.add(new Gson().toJsonTree(this.misc, this.f16329k));
        DeviceData.userId = C6638h.m8963w().m8964v();
        return jsonArray;
    }

    public JsonArray asSocketJsonArray() {
        JsonArray jsonArray = new JsonArray();
        m9932g("" + this.screenSize);
        jsonArray.add(new JsonPrimitive(this.screenSize + ""));
        m9932g(this.manufacturer);
        jsonArray.add(new JsonPrimitive(this.manufacturer));
        m9932g(this.model);
        jsonArray.add(new JsonPrimitive(this.model));
        m9932g(this.osName);
        jsonArray.add(new JsonPrimitive(this.osName));
        m9932g(this.osVersion);
        jsonArray.add(new JsonPrimitive(this.osVersion));
        Map<String, Object> map = this.misc;
        if (map == null || map.isEmpty()) {
            this.misc = Collections.emptyMap();
        }
        jsonArray.add(new Gson().toJsonTree(this.misc, this.f16329k));
        DeviceData.userId = C6638h.m8963w().m8964v();
        return jsonArray;
    }

    public void setOsName(String str) {
        this.osName = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setRegionCode(String str) {
        this.regionCode = str;
    }

    public void setAgentName(String str) {
        this.agentName = str;
    }

    public void setAgentVersion(String str) {
        this.agentVersion = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setMisc(Map<String, Object> map) {
        this.misc = new HashMap(map);
    }

    public void addMisc(String str, Object obj) {
        this.misc.put(str, obj);
    }

    public String getOsName() {
        return this.osName;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getModel() {
        return this.model;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public String getAgentVersion() {
        return this.agentVersion;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public double getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(double d) {
        this.screenSize = d;
    }

    public String initUserHeaderValue() {
        return MessageFormat.format("NBS Newlens Agent/{0} ({1} {2})", NBSAgent.getVersion(), this.osName, this.osVersion);
    }

    @Override // com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public String toJsonString() {
        return "DeviceInformation{manufacturer='" + this.manufacturer + "', osName='" + this.osName + "', osVersion='" + this.osVersion + "', model='" + this.model + "', agentName='" + this.agentName + "', agentVersion='" + this.agentVersion + "', deviceId='" + this.deviceId + "', countryCode='" + this.countryCode + "', regionCode='" + this.regionCode + "'}";
    }
}
