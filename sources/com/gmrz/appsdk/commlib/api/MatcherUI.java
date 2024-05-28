package com.gmrz.appsdk.commlib.api;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MatcherUI {
    private int maxMiss;
    private String title;

    public MatcherUI fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.title = jSONObject.optString("title");
            this.maxMiss = jSONObject.optInt("maxMiss", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getMaxMiss() {
        return this.maxMiss;
    }

    public String getTitle() {
        return this.title;
    }

    public void setMaxMiss(int i) {
        this.maxMiss = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("title", this.title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jSONObject.put("maxMiss", this.maxMiss);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("title", this.title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jSONObject.put("maxMiss", this.maxMiss);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
