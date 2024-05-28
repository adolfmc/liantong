package com.alipay.sdk.util;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2039b {
    /* renamed from: a */
    public static JSONObject m20719a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject[] jSONObjectArr;
        JSONObject jSONObject3 = new JSONObject();
        try {
            for (JSONObject jSONObject4 : new JSONObject[]{jSONObject, jSONObject2}) {
                if (jSONObject4 != null) {
                    Iterator<String> keys = jSONObject4.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject3.put(next, jSONObject4.get(next));
                    }
                }
            }
        } catch (JSONException e) {
            C2040c.m20715a(e);
        }
        return jSONObject3;
    }
}
