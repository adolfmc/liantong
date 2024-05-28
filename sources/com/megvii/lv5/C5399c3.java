package com.megvii.lv5;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.c3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5399c3 {

    /* renamed from: a */
    public static JSONArray f12424a = null;

    /* renamed from: b */
    public static String f12425b = "";

    /* renamed from: c */
    public static String f12426c = "";

    /* renamed from: d */
    public static JSONArray f12427d;

    /* renamed from: a */
    public static synchronized String m13607a() {
        synchronized (C5399c3.class) {
            JSONArray jSONArray = f12424a;
            if (jSONArray == null || jSONArray.length() <= 0) {
                JSONArray jSONArray2 = new JSONArray();
                return !(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2);
            }
            JSONArray jSONArray3 = f12424a;
            C5628v2.m12958b("dataPoint", !(jSONArray3 instanceof JSONArray) ? jSONArray3.toString() : NBSJSONArrayInstrumentation.toString(jSONArray3));
            JSONArray jSONArray4 = f12424a;
            String jSONArray5 = !(jSONArray4 instanceof JSONArray) ? jSONArray4.toString() : NBSJSONArrayInstrumentation.toString(jSONArray4);
            f12425b = jSONArray5;
            return jSONArray5;
        }
    }

    /* renamed from: a */
    public static synchronized void m13606a(JSONObject jSONObject) {
        synchronized (C5399c3.class) {
            if (f12424a == null) {
                f12424a = new JSONArray();
            }
            if (jSONObject != null) {
                f12424a.put(jSONObject);
            }
        }
    }

    /* renamed from: b */
    public static synchronized String m13605b() {
        synchronized (C5399c3.class) {
            JSONArray jSONArray = f12424a;
            if (jSONArray == null || jSONArray.length() <= 0) {
                JSONArray jSONArray2 = new JSONArray();
                return !(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2);
            }
            JSONArray jSONArray3 = f12424a;
            String jSONArray4 = !(jSONArray3 instanceof JSONArray) ? jSONArray3.toString() : NBSJSONArrayInstrumentation.toString(jSONArray3);
            f12426c = jSONArray4;
            return jSONArray4;
        }
    }

    /* renamed from: b */
    public static synchronized void m13604b(JSONObject jSONObject) {
        synchronized (C5399c3.class) {
            if (f12427d == null) {
                f12427d = new JSONArray();
            }
            if (jSONObject != null) {
                f12427d.put(jSONObject);
            }
        }
    }
}
