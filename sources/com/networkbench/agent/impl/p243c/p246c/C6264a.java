package com.networkbench.agent.impl.p243c.p246c;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.c.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6264a {

    /* renamed from: a */
    public int f15608a = 60;

    /* renamed from: b */
    public ArrayList<C6267d> f15609b = new ArrayList<>();

    /* renamed from: a */
    public C6264a m10746a(JSONObject jSONObject) {
        try {
            try {
                this.f15608a = jSONObject.getInt("uploadInterval");
                if (this.f15608a <= 0) {
                    this.f15608a = 60;
                }
            } catch (Throwable unused) {
                this.f15608a = 60;
            }
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("tasks");
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        C6267d c6267d = new C6267d();
                        if (c6267d.m10735a((JSONObject) jSONArray.get(i))) {
                            this.f15609b.add(c6267d);
                        }
                    }
                }
            } catch (Throwable unused2) {
            }
            return this;
        } catch (Throwable unused3) {
            return this;
        }
    }
}
