package com.bytedance.sdk.openadsdk.downloadnew.core;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TTDownloadEventModel {

    /* renamed from: b */
    private JSONObject f9630b;

    /* renamed from: hj */
    private JSONObject f9631hj;

    /* renamed from: mb */
    private String f9632mb;

    /* renamed from: ox */
    private String f9633ox;

    public static TTDownloadEventModel builder() {
        return new TTDownloadEventModel();
    }

    public TTDownloadEventModel setMaterialMeta(JSONObject jSONObject) {
        this.f9631hj = jSONObject;
        return this;
    }

    public TTDownloadEventModel setTag(String str) {
        this.f9632mb = str;
        return this;
    }

    public TTDownloadEventModel setLabel(String str) {
        this.f9633ox = str;
        return this;
    }

    public TTDownloadEventModel setExtJson(JSONObject jSONObject) {
        this.f9630b = jSONObject;
        return this;
    }

    public String getTag() {
        return this.f9632mb;
    }

    public String getLabel() {
        return this.f9633ox;
    }

    public JSONObject getExtJson() {
        return this.f9630b;
    }

    public JSONObject getMaterialMeta() {
        return this.f9631hj;
    }
}
