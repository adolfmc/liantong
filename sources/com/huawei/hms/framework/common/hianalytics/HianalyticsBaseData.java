package com.huawei.hms.framework.common.hianalytics;

import com.huawei.hms.framework.common.Logger;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class HianalyticsBaseData {
    public static final String EVENT_ID = "url_request";
    public static final String SDK_NAME = "sdk_name";
    public static final String SDK_TYPE = "sdk_type";
    public static final String SDK_VERSION = "sdk_version";
    private static final String TAG = "HianalyticsBaseData";
    private LinkedHashMap<String, String> data = new LinkedHashMap<>();

    public HianalyticsBaseData() {
        this.data.put("sdk_type", "UxPP");
        this.data.put(SDK_NAME, "networkkit");
    }

    public HianalyticsBaseData put(String str, String str2) {
        if (str == null || str2 == null) {
            Logger.m15047v(TAG, "key = " + str + " : value = " + str2);
        } else {
            this.data.put(str, str2);
        }
        return this;
    }

    public HianalyticsBaseData put(String str, long j) {
        if (str == null) {
            Logger.m15047v(TAG, "key = null : value = " + j);
        } else {
            LinkedHashMap<String, String> linkedHashMap = this.data;
            linkedHashMap.put(str, "" + j);
        }
        return this;
    }

    public HianalyticsBaseData put(LinkedHashMap<String, String> linkedHashMap) {
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            Logger.m15047v(TAG, "data is null");
        } else {
            this.data.putAll(linkedHashMap);
        }
        return this;
    }

    public HianalyticsBaseData putIfNotDefault(String str, long j, long j2) {
        return j == j2 ? this : put(str, j);
    }

    public LinkedHashMap<String, String> get() {
        return this.data;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : get().entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            Logger.m15044w(TAG, "catch JSONException", e);
        }
        return jSONObject.toString();
    }
}
