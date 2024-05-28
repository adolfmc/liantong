package p001a.p058b.p062b.p063a.p064a.p073e;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.e.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0754a extends AbstractC0747d {

    /* renamed from: b */
    public static final InterfaceC3321a f2303b = C0749a.f2299a;

    /* renamed from: c */
    public String f2304c;

    /* renamed from: e */
    public Double f2306e;

    /* renamed from: f */
    public Double f2307f;

    /* renamed from: g */
    public Double f2308g;

    /* renamed from: h */
    public Double f2309h;

    /* renamed from: i */
    public Double f2310i;

    /* renamed from: d */
    public String f2305d = null;

    /* renamed from: j */
    public long f2311j = 0;

    public C0754a(String str) {
        this.f2304c = str;
    }

    /* renamed from: a */
    public void m22269a(double d) {
        Double valueOf;
        this.f2311j++;
        Double d2 = this.f2308g;
        if (d2 == null) {
            this.f2308g = Double.valueOf(d);
            valueOf = Double.valueOf(d * d);
        } else {
            this.f2308g = Double.valueOf(d2.doubleValue() + d);
            valueOf = Double.valueOf((d * d) + this.f2309h.doubleValue());
        }
        this.f2309h = valueOf;
        Double valueOf2 = Double.valueOf(d);
        if (valueOf2 != null && (this.f2306e == null || valueOf2.doubleValue() < this.f2306e.doubleValue())) {
            this.f2306e = valueOf2;
        }
        Double valueOf3 = Double.valueOf(d);
        if (valueOf3 == null) {
            return;
        }
        if (this.f2307f != null && valueOf3.doubleValue() <= this.f2307f.doubleValue()) {
            return;
        }
        this.f2307f = valueOf3;
    }

    public String toString() {
        StringBuilder m24437a = outline.m24437a("Metric{name='");
        m24437a.append(this.f2304c);
        m24437a.append('\'');
        m24437a.append(", scope='");
        m24437a.append(this.f2305d);
        m24437a.append('\'');
        m24437a.append(", min=");
        m24437a.append(this.f2306e);
        m24437a.append(", max=");
        m24437a.append(this.f2307f);
        m24437a.append(", total=");
        m24437a.append(this.f2308g);
        m24437a.append(", sumOfSquares=");
        m24437a.append(this.f2309h);
        m24437a.append(", exclusive=");
        m24437a.append(this.f2310i);
        m24437a.append(", count=");
        m24437a.append(this.f2311j);
        m24437a.append('}');
        return m24437a.toString();
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("count", this.f2311j);
            if (this.f2308g != null) {
                jSONObject.put("total", this.f2308g);
            }
            if (this.f2306e != null) {
                jSONObject.put("min", this.f2306e);
            }
            if (this.f2307f != null) {
                jSONObject.put("max", this.f2307f);
            }
            if (this.f2309h != null) {
                jSONObject.put("sum_of_squares", this.f2309h);
            }
            if (this.f2310i != null) {
                jSONObject.put("exclusive", this.f2310i);
            }
        } catch (JSONException e) {
            f2303b.mo17426a("Caught error while Metric asJSONObject: ", e);
            C0735a.m22302a(e);
        }
        return jSONObject;
    }
}
