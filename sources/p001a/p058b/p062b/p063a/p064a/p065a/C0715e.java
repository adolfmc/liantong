package p001a.p058b.p062b.p063a.p064a.p065a;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0726c;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0727d;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;

/* renamed from: a.b.b.a.a.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0715e extends AbstractC0747d {

    /* renamed from: b */
    public long f2162b;

    /* renamed from: c */
    public int f2163c;

    /* renamed from: d */
    public String f2164d;

    /* renamed from: e */
    public long[] f2165e;

    /* renamed from: f */
    public String f2166f;

    /* renamed from: g */
    public String f2167g;

    /* renamed from: h */
    public String f2168h;

    /* renamed from: i */
    public String f2169i;

    /* renamed from: j */
    public String f2170j;

    /* renamed from: k */
    public String f2171k;

    /* renamed from: l */
    public String f2172l;

    /* renamed from: m */
    public String f2173m;

    public C0715e() {
    }

    public C0715e(C0726c c0726c, C0727d c0727d) {
        this.f2162b = c0727d.f2222a;
        this.f2163c = c0727d.f2223b;
        this.f2164d = c0727d.f2224c;
        this.f2165e = c0727d.f2225d;
        this.f2166f = c0726c.f2210d;
        this.f2167g = c0726c.f2211e;
        this.f2168h = c0726c.f2218l;
        this.f2169i = c0726c.f2219m;
        this.f2170j = c0726c.f2212f;
        this.f2171k = c0726c.f2216j;
        this.f2172l = c0726c.f2215i;
        this.f2173m = c0726c.f2220n;
    }

    /* renamed from: a */
    public static C0715e m22330a(JSONObject jSONObject) {
        C0715e c0715e = new C0715e();
        try {
            c0715e.f2162b = jSONObject.getLong("memoryUsage");
            c0715e.f2163c = jSONObject.getInt("orientation");
            c0715e.f2164d = jSONObject.getString("networkStatus");
            JSONArray jSONArray = jSONObject.getJSONArray("diskAvailable");
            long[] jArr = new long[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    jArr[i] = jSONArray.getLong(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            c0715e.f2165e = jArr;
            c0715e.f2166f = jSONObject.getString("OSVersion");
            c0715e.f2167g = jSONObject.getString("deviceName");
            c0715e.f2168h = jSONObject.getString("OSBuild");
            c0715e.f2169i = jSONObject.getString("architecture");
            c0715e.f2173m = jSONObject.getString("runTime");
            c0715e.f2170j = jSONObject.getString("modelNumber");
            c0715e.f2171k = jSONObject.getString("screenResolution");
            c0715e.f2172l = jSONObject.getString("deviceUuid");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return c0715e;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("memoryUsage", Long.valueOf(this.f2162b));
            jSONObject.put("orientation", Integer.valueOf(this.f2163c));
            jSONObject.put("networkStatus", this.f2164d);
            JSONArray jSONArray = new JSONArray();
            for (long j : this.f2165e) {
                jSONArray.put(Long.valueOf(j));
            }
            jSONObject.put("diskAvailable", jSONArray);
            jSONObject.put("OSVersion", this.f2166f);
            jSONObject.put("deviceName", this.f2167g);
            jSONObject.put("OSBuild", this.f2168h);
            jSONObject.put("architecture", this.f2169i);
            jSONObject.put("runTime", this.f2173m);
            jSONObject.put("modelNumber", this.f2170j);
            jSONObject.put("screenResolution", this.f2171k);
            jSONObject.put("deviceUuid", this.f2172l);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
