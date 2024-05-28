package com.baidu.platform.comapi.p150c;

import com.baidu.platform.comapi.p148a.C2978c;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.platform.comapi.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2985a {

    /* renamed from: a */
    public static C2985a f7547a;

    /* renamed from: b */
    private static JSONObject f7548b;

    /* renamed from: c */
    private C2978c f7549c = null;

    /* renamed from: a */
    public static C2985a m18050a() {
        if (f7547a == null) {
            f7547a = new C2985a();
        }
        if (f7548b == null) {
            f7548b = new JSONObject();
        }
        return f7547a;
    }

    /* renamed from: b */
    private void m18048b() {
        f7548b = null;
        f7548b = new JSONObject();
    }

    /* renamed from: a */
    public synchronized boolean m18049a(String str) {
        boolean z;
        boolean m18077a;
        if (this.f7549c == null) {
            this.f7549c = C2978c.m18078a();
        }
        z = false;
        if (this.f7549c != null) {
            if (f7548b == null || f7548b.length() <= 0) {
                m18077a = this.f7549c.m18077a(1100, 1, str, null);
            } else {
                C2978c c2978c = this.f7549c;
                JSONObject jSONObject = f7548b;
                m18077a = c2978c.m18077a(1100, 1, str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            }
            z = m18077a;
            m18048b();
        }
        return z;
    }
}
