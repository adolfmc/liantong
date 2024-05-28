package com.alipay.sdk.packet.impl;

import android.content.Context;
import com.alipay.sdk.packet.AbstractC2024e;
import com.alipay.sdk.packet.C2021b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.packet.impl.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2028d extends AbstractC2024e {

    /* renamed from: t */
    public static final String f3800t = "log_v";

    @Override // com.alipay.sdk.packet.AbstractC2024e
    /* renamed from: a */
    public String mo20792a(String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.packet.AbstractC2024e
    /* renamed from: a */
    public JSONObject mo20789a() throws JSONException {
        return null;
    }

    @Override // com.alipay.sdk.packet.AbstractC2024e
    /* renamed from: a */
    public Map<String, String> mo20791a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msp-gzip", String.valueOf(z));
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put("des-mode", "CBC");
        return hashMap;
    }

    @Override // com.alipay.sdk.packet.AbstractC2024e
    /* renamed from: c */
    public String mo20790c() throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("api_name", "/sdk/log");
        hashMap.put("api_version", "1.0.0");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("log_v", "1.0");
        return m20795a(hashMap, hashMap2);
    }

    @Override // com.alipay.sdk.packet.AbstractC2024e
    /* renamed from: a */
    public C2021b mo20793a(Context context, String str) throws Throwable {
        return m20800a(context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
