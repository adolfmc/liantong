package com.bytedance.sdk.openadsdk.api.plugin;

import android.os.SystemClock;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3977h {

    /* renamed from: b */
    private long f9503b;

    /* renamed from: hj */
    private Map<String, Long> f9504hj = new HashMap();

    /* renamed from: mb */
    private String f9505mb;

    /* renamed from: ox */
    private long f9506ox;

    private C3977h(String str, long j) {
        this.f9505mb = str;
        this.f9506ox = j;
        this.f9503b = this.f9506ox;
    }

    /* renamed from: mb */
    public static C3977h m16535mb(String str) {
        return new C3977h(str, SystemClock.elapsedRealtime());
    }

    /* renamed from: mb */
    public long m16536mb() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f9506ox;
        this.f9504hj.put(this.f9505mb, Long.valueOf(elapsedRealtime));
        return elapsedRealtime;
    }

    /* renamed from: ox */
    public long m16533ox(String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f9503b;
        this.f9503b = SystemClock.elapsedRealtime();
        this.f9504hj.put(str, Long.valueOf(elapsedRealtime));
        return elapsedRealtime;
    }

    /* renamed from: mb */
    public void m16534mb(JSONObject jSONObject, long j) {
        if (jSONObject == null) {
            return;
        }
        for (Map.Entry<String, Long> entry : this.f9504hj.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            if (!TextUtils.isEmpty(key) && value.longValue() > j) {
                try {
                    jSONObject.put(key, value);
                } catch (JSONException unused) {
                }
            }
        }
    }
}
