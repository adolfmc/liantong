package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0728e extends AbstractC0747d {

    /* renamed from: b */
    public static final InterfaceC3321a f2226b = C0749a.f2299a;

    /* renamed from: c */
    public long f2227c;

    /* renamed from: d */
    public JSONObject f2228d = new JSONObject();

    /* renamed from: a */
    public void m22311a(String str, Object obj) {
        try {
            this.f2228d.put(str, obj);
        } catch (JSONException e) {
            f2226b.mo17426a("Caught error while addResourceValue: ", e);
            C0735a.m22302a(e);
        }
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", this.f2227c);
            jSONObject.put("value", this.f2228d);
        } catch (JSONException e) {
            f2226b.mo17426a("Caught error while ResourceData asJSONObject: ", e);
            C0735a.m22302a(e);
        }
        return jSONObject;
    }
}
