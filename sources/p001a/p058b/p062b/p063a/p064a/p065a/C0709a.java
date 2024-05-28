package p001a.p058b.p062b.p063a.p064a.p065a;

import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0724a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;

/* renamed from: a.b.b.a.a.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0709a extends AbstractC0747d {

    /* renamed from: b */
    public String f2136b;

    /* renamed from: c */
    public String f2137c;

    /* renamed from: d */
    public String f2138d;

    public C0709a() {
        this.f2136b = "";
        this.f2137c = "";
        this.f2138d = "";
    }

    public C0709a(C0724a c0724a) {
        this.f2136b = "";
        this.f2137c = "";
        this.f2138d = "";
        this.f2136b = c0724a.f2202c;
        this.f2137c = c0724a.f2203d;
        this.f2138d = c0724a.f2204e;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appName", this.f2136b);
            jSONObject.put("appVersion", this.f2137c);
            jSONObject.put("bundleId", this.f2138d);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
