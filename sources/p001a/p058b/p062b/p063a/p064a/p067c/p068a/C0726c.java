package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0726c extends AbstractC0746c {

    /* renamed from: b */
    public static final InterfaceC3321a f2208b = C0749a.f2299a;

    /* renamed from: c */
    public String f2209c;

    /* renamed from: d */
    public String f2210d;

    /* renamed from: e */
    public String f2211e;

    /* renamed from: f */
    public String f2212f;

    /* renamed from: g */
    public String f2213g;

    /* renamed from: h */
    public String f2214h;

    /* renamed from: i */
    public String f2215i;

    /* renamed from: j */
    public String f2216j;

    /* renamed from: k */
    public String f2217k;

    /* renamed from: l */
    public String f2218l;

    /* renamed from: m */
    public String f2219m;

    /* renamed from: n */
    public String f2220n;

    /* renamed from: o */
    public boolean f2221o = false;

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        JSONArray jSONArray = new JSONArray();
        try {
            m22272a(this.f2209c);
            jSONArray.put(0, this.f2209c);
            m22272a(this.f2210d);
            jSONArray.put(1, this.f2210d);
            m22272a(this.f2211e);
            m22272a(this.f2212f);
            StringBuilder sb = new StringBuilder();
            sb.append(this.f2211e);
            sb.append(" ");
            sb.append(this.f2212f);
            jSONArray.put(2, sb.toString());
            m22272a(this.f2213g);
            jSONArray.put(3, this.f2213g);
            m22272a(this.f2214h);
            jSONArray.put(4, this.f2214h);
            m22272a(this.f2215i);
            jSONArray.put(5, this.f2215i);
            jSONArray.put(6, "");
            jSONArray.put(7, "");
            jSONArray.put(8, this.f2211e);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("size", this.f2216j);
            jSONObject.put("CUID", this.f2217k);
            jSONArray.put(9, jSONObject);
        } catch (JSONException e) {
            f2208b.mo17426a("Caught error while DeviceInformation asJSONArray: ", e);
            C0735a.m22302a(e);
        }
        return jSONArray;
    }
}
