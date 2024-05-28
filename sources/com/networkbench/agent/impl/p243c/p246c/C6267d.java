package com.networkbench.agent.impl.p243c.p246c;

import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.c.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6267d {

    /* renamed from: a */
    public String f15613a;

    /* renamed from: b */
    public String f15614b;

    /* renamed from: c */
    public String f15615c;

    /* renamed from: d */
    public JSONObject f15616d;

    /* renamed from: e */
    public String f15617e;

    /* renamed from: g */
    private AtomicBoolean f15619g = new AtomicBoolean(false);

    /* renamed from: f */
    private boolean f15618f = true;

    /* renamed from: a */
    public boolean m10736a() {
        return this.f15618f;
    }

    /* renamed from: a */
    public void m10734a(boolean z) {
        this.f15619g.set(z);
    }

    /* renamed from: b */
    public boolean m10733b() {
        return this.f15619g.get();
    }

    /* renamed from: a */
    public boolean m10735a(JSONObject jSONObject) {
        try {
            this.f15613a = jSONObject.optString("taskId");
            this.f15614b = jSONObject.optString("scene");
            this.f15615c = jSONObject.optString("action");
            try {
                this.f15616d = jSONObject.optJSONObject("argument");
                try {
                    this.f15617e = jSONObject.optString("key", null);
                } catch (Throwable unused) {
                }
                try {
                    if (this.f15617e == null) {
                        this.f15618f = false;
                        this.f15617e = "";
                        return true;
                    }
                    return true;
                } catch (Throwable unused2) {
                    return false;
                }
            } catch (Throwable unused3) {
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    public String toString() {
        return "NBSSceneAction{taskId='" + this.f15613a + "', scene='" + this.f15614b + "', action='" + this.f15615c + "', arguments=" + this.f15616d + ", key='" + this.f15617e + "'}";
    }
}
