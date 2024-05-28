package com.huawei.hms.hatool;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.g0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4985g0 {

    /* renamed from: c */
    private static C4985g0 f11387c;

    /* renamed from: a */
    private Context f11388a;

    /* renamed from: b */
    private final Object f11389b = new Object();

    private C4985g0() {
    }

    /* renamed from: a */
    public static C4985g0 m14735a() {
        if (f11387c == null) {
            m14729b();
        }
        return f11387c;
    }

    /* renamed from: a */
    private JSONObject m14730a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            } catch (JSONException unused) {
                C5029v.m14458b("hmsSdk", "Exception occured when transferring bundle to json");
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    private static synchronized void m14729b() {
        synchronized (C4985g0.class) {
            if (f11387c == null) {
                f11387c = new C4985g0();
            }
        }
    }

    /* renamed from: a */
    public void m14734a(Context context) {
        synchronized (this.f11389b) {
            if (this.f11388a != null) {
                return;
            }
            this.f11388a = context;
            C4978e.m14760a().m14759a(context);
        }
    }

    /* renamed from: a */
    public void m14733a(String str, int i) {
        C4978e.m14760a().m14758a(str, i);
    }

    /* renamed from: a */
    public void m14732a(String str, int i, String str2, LinkedHashMap<String, String> linkedHashMap) {
        C4978e.m14760a().m14757a(str, i, str2, m14730a(linkedHashMap));
    }

    /* renamed from: a */
    public void m14731a(String str, Context context, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_constants", str3);
            C4978e.m14760a().m14757a(str, 0, str2, jSONObject);
        } catch (JSONException unused) {
            C5029v.m14451f("hmsSdk", "onEvent():JSON structure Exception!");
        }
    }

    /* renamed from: b */
    public void m14728b(String str, int i, String str2, LinkedHashMap<String, String> linkedHashMap) {
        C4978e.m14760a().m14756a(str, i, str2, m14730a(linkedHashMap), System.currentTimeMillis());
    }
}
