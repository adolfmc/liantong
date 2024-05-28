package com.vivo.push.restructure.request.p379a.p380a;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.vivo.push.restructure.request.p379a.p380a.PushParcelable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

@NBSInstrumented
/* renamed from: com.vivo.push.restructure.request.a.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class JsonParcel {

    /* renamed from: a */
    private int f21130a;

    /* renamed from: b */
    private int f21131b;

    /* renamed from: c */
    private JSONArray f21132c;

    public JsonParcel() {
        this.f21130a = 0;
        this.f21132c = new JSONArray();
    }

    public JsonParcel(String str) throws JSONException {
        this.f21130a = 0;
        this.f21132c = new JSONArray(str);
        this.f21130a = 0;
        this.f21131b = this.f21132c.length();
    }

    /* renamed from: a */
    public final void m5516a(int i) {
        this.f21132c.put(i);
    }

    /* renamed from: a */
    public final void m5515a(long j) {
        this.f21132c.put(j);
    }

    /* renamed from: a */
    public final void m5513a(String str) {
        this.f21132c.put(str);
    }

    /* renamed from: a */
    public final <T extends PushParcelable> void m5512a(List<T> list) {
        if (list != null) {
            this.f21132c.put(list.size());
            for (T t : list) {
                this.f21132c.put(t.mo5508a());
            }
            return;
        }
        this.f21132c.put((Object) null);
    }

    /* renamed from: a */
    public final int m5517a() throws JSONException {
        int i = this.f21130a;
        if (i < this.f21131b) {
            JSONArray jSONArray = this.f21132c;
            this.f21130a = i + 1;
            return jSONArray.getInt(i);
        }
        return 0;
    }

    /* renamed from: b */
    public final long m5511b() throws JSONException {
        int i = this.f21130a;
        if (i < this.f21131b) {
            JSONArray jSONArray = this.f21132c;
            this.f21130a = i + 1;
            return jSONArray.getLong(i);
        }
        return 0L;
    }

    /* renamed from: c */
    public final String m5510c() throws JSONException {
        int i = this.f21130a;
        if (i < this.f21131b) {
            JSONArray jSONArray = this.f21132c;
            this.f21130a = i + 1;
            return jSONArray.getString(i);
        }
        return null;
    }

    /* renamed from: d */
    public final String m5509d() {
        JSONArray jSONArray = this.f21132c;
        return jSONArray != null ? !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray) : "";
    }

    /* renamed from: a */
    public final <T extends PushParcelable> void m5514a(PushParcelable.InterfaceC10974a<T> interfaceC10974a, List<T> list) throws JSONException {
        T t;
        int i = this.f21130a;
        if (i < this.f21131b ? this.f21132c.isNull(i) : true) {
            this.f21130a++;
            return;
        }
        JSONArray jSONArray = this.f21132c;
        int i2 = this.f21130a;
        this.f21130a = i2 + 1;
        int i3 = jSONArray.getInt(i2);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = this.f21130a;
            if (i5 < this.f21131b) {
                JSONArray jSONArray2 = this.f21132c;
                this.f21130a = i5 + 1;
                t = interfaceC10974a.mo5507a(jSONArray2.getString(i5));
            } else {
                t = null;
            }
            list.add(t);
        }
    }
}
