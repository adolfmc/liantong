package com.huawei.hms.activity.internal;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ForegroundInnerHeader {

    /* renamed from: a */
    private int f10911a;

    /* renamed from: b */
    private String f10912b;

    /* renamed from: c */
    private String f10913c;

    public void fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f10911a = JsonUtil.getIntValue(jSONObject, "apkVersion");
            this.f10912b = JsonUtil.getStringValue(jSONObject, "action");
            this.f10913c = JsonUtil.getStringValue(jSONObject, "responseCallbackKey");
        } catch (JSONException e) {
            HMSLog.m14112e("ForegroundInnerHeader", "fromJson failed: " + e.getMessage());
        }
    }

    public String getAction() {
        return this.f10912b;
    }

    public int getApkVersion() {
        return this.f10911a;
    }

    public String getResponseCallbackKey() {
        return this.f10913c;
    }

    public void setAction(String str) {
        this.f10912b = str;
    }

    public void setApkVersion(int i) {
        this.f10911a = i;
    }

    public void setResponseCallbackKey(String str) {
        this.f10913c = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("apkVersion", this.f10911a);
            jSONObject.put("action", this.f10912b);
            jSONObject.put("responseCallbackKey", this.f10913c);
        } catch (JSONException e) {
            HMSLog.m14112e("ForegroundInnerHeader", "ForegroundInnerHeader toJson failed: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "apkVersion:" + this.f10911a + ", action:" + this.f10912b + ", responseCallbackKey:" + this.f10913c;
    }
}
