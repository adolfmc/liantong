package p001a.p058b.p062b.p063a.p064a.p065a;

import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;

/* renamed from: a.b.b.a.a.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0716f extends AbstractC0747d {

    /* renamed from: b */
    public String f2174b;

    /* renamed from: c */
    public String f2175c;

    public C0716f() {
    }

    public C0716f(Throwable th) {
        this.f2174b = th.getClass().getName();
        this.f2175c = th.getMessage() != null ? th.getMessage() : "";
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f2174b);
            jSONObject.put("cause", this.f2175c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
