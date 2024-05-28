package p001a.p058b.p062b.p063a.p064a.p067c;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.C0719b;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0724a;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0725b;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0726c;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0729f;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0731h;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0733j;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p073e.C0754a;
import p001a.p058b.p062b.p063a.p064a.p075g.C0760a;
import p001a.p058b.p062b.p063a.p064a.p076h.p077a.C0763a;
import p001a.p058b.p062b.p063a.p064a.p079j.p080a.C0767a;

/* renamed from: a.b.b.a.a.c.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0723a extends AbstractC0746c {

    /* renamed from: b */
    public static final UAQ f2192b = UAQ.getInstance();

    /* renamed from: c */
    public static final InterfaceC3321a f2193c = C0749a.f2299a;

    /* renamed from: e */
    public C0726c f2195e;

    /* renamed from: g */
    public C0724a f2197g;

    /* renamed from: d */
    public C0725b f2194d = new C0725b();

    /* renamed from: f */
    public C0735a f2196f = new C0735a();

    /* renamed from: h */
    public C0733j f2198h = new C0733j(new C0767a());

    /* renamed from: i */
    public C0731h f2199i = new C0731h(new C0763a());

    /* renamed from: j */
    public C0729f f2200j = new C0729f();

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        Object jSONArray;
        JSONArray jSONArray2 = new JSONArray();
        try {
            jSONArray2.put(0, this.f2194d.mo17432az());
            jSONArray2.put(1, f2192b.isNeedBasicInfo() ? m22312b().mo17432az() : new JSONArray());
            jSONArray2.put(2, this.f2199i.mo17432az());
            jSONArray2.put(3, new JSONArray());
            JSONArray jSONArray3 = new JSONArray();
            for (Map.Entry<String, C0754a> entry : C0760a.f2350a.f2352c.entrySet()) {
                JSONArray jSONArray4 = new JSONArray();
                C0754a value = entry.getValue();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", value.f2304c);
                String str = value.f2305d;
                if (str == null) {
                    str = "";
                }
                jSONObject.put("scope", str);
                jSONArray4.put(jSONObject);
                jSONArray4.put(value.mo17429z());
                jSONArray3.put(jSONArray4);
            }
            jSONArray2.put(4, jSONArray3);
            jSONArray2.put(5, new JSONObject());
            jSONArray2.put(6, new JSONArray());
            jSONArray2.put(7, this.f2196f.mo17432az());
            if (f2192b.isNeedBasicInfo()) {
                if (this.f2197g == null) {
                    this.f2197g = C0719b.m22326a().mo17441f();
                }
                jSONArray = this.f2197g.mo17432az();
            } else {
                jSONArray = new JSONArray();
            }
            jSONArray2.put(8, jSONArray);
            jSONArray2.put(9, new JSONArray());
            jSONArray2.put(10, this.f2198h.mo17432az());
            jSONArray2.put(11, m22314a());
        } catch (JSONException e) {
            f2193c.mo17426a("Caught error while HarvestData asJSONArray: ", e);
            C0735a.m22302a(e);
        }
        return jSONArray2;
    }

    /* renamed from: a */
    public final JSONObject m22314a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("wanType", C0719b.m22326a().mo17440g());
        jSONObject.put("carrier", C0719b.m22326a().mo17439h());
        jSONObject.put("timestamp", System.currentTimeMillis());
        f2192b.getConfig().getChannel();
        if (f2192b.getConfig().isThingsMonitor()) {
            if (this.f2200j.f2231d.size() > 0) {
                jSONObject.put("resourceData", this.f2200j.mo17432az());
            }
            jSONObject.put("gateToken", f2192b.getConfig().getAPIKey());
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void m22313a(C0725b c0725b) {
        if (c0725b == null) {
            return;
        }
        this.f2194d = c0725b;
    }

    /* renamed from: b */
    public C0726c m22312b() {
        if (this.f2195e == null) {
            this.f2195e = C0719b.m22326a().mo17442e();
        }
        return this.f2195e;
    }
}
