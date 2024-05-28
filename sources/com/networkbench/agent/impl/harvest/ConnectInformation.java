package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ConnectInformation extends HarvestableObject {
    private ApplicationInformation applicationInformation;
    private String deviceId;
    private DeviceInformation deviceInformation;
    private final InterfaceC6393e log = C6394f.m10150a();
    public String userAgent;

    public ConnectInformation() {
        setApplicationInformation(NBSAgent.getApplicationInformation());
        setDeviceInformation(NBSAgent.getDeviceInformation());
        this.userAgent = initUserHeaderValue();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        m9933a(this.applicationInformation);
        jsonObject.add("app", this.applicationInformation.asJsonArray());
        m9933a(this.deviceInformation);
        jsonObject.add("dev", this.deviceInformation.asJsonArray());
        this.deviceId = NBSAgent.getImpl().m9148o();
        String str = this.deviceId;
        if (str == null) {
            str = "";
        }
        jsonObject.add("did", new JsonPrimitive(str));
        return jsonObject;
    }

    public JsonObject asSocketJsonObject() {
        JsonObject jsonObject = new JsonObject();
        this.deviceId = NBSAgent.getImpl().m9148o();
        String str = this.deviceId;
        if (str == null) {
            str = "";
        }
        jsonObject.add("did", new JsonPrimitive(str));
        m9933a(this.deviceInformation);
        jsonObject.add("dev", this.deviceInformation.asSocketJsonArray());
        m9933a(this.applicationInformation);
        jsonObject.add("app", this.applicationInformation.asSocketJsonArray());
        return jsonObject;
    }

    public void setApplicationInformation(ApplicationInformation applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    private String initUserHeaderValue() {
        return this.deviceInformation.initUserHeaderValue();
    }
}
