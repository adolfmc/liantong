package com.networkbench.agent.impl.p268n;

import com.networkbench.com.google.gson.GsonBuilder;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6517d {

    /* renamed from: a */
    String f16609a;

    /* renamed from: b */
    String f16610b;

    /* renamed from: c */
    String f16611c;

    /* renamed from: d */
    Map f16612d;

    public C6517d(String str) throws JSONException {
        this.f16610b = str;
        JSONObject jSONObject = new JSONObject(str).getJSONObject("params").getJSONObject("ri");
        this.f16609a = jSONObject.getString("url");
        this.f16611c = jSONObject.getString("body");
        this.f16612d = m9563a(jSONObject.getJSONObject("header").toString());
    }

    /* renamed from: a */
    public static Map<String, Object> m9563a(String str) {
        return (Map) new GsonBuilder().create().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.networkbench.agent.impl.n.d.1
        }.getType());
    }
}
