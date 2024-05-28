package com.unionpay.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.utils.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10922i {
    /* renamed from: a */
    public static Object m5835a(JSONArray jSONArray, int i) {
        if (jSONArray != null && i < jSONArray.length() && i >= 0) {
            try {
                return jSONArray.get(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m5834a(JSONObject jSONObject, String str) {
        if (m5832c(jSONObject, str)) {
            try {
                return jSONObject.getString(str);
            } catch (Exception unused) {
                C10923j.m5828c("uppay", C10922i.class.toString() + " get " + str + " failed!!");
                return "";
            }
        }
        return "";
    }

    /* renamed from: b */
    public static JSONArray m5833b(JSONObject jSONObject, String str) {
        if (m5832c(jSONObject, str)) {
            try {
                return jSONObject.getJSONArray(str);
            } catch (Exception unused) {
                C10923j.m5828c("uppay", C10922i.class.toString() + " get " + str + " failed!!");
            }
        }
        return null;
    }

    /* renamed from: c */
    private static boolean m5832c(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }
}
