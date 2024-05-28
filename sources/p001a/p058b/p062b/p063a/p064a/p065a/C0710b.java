package p001a.p058b.p062b.p063a.p064a.p065a;

import com.baidu.uaq.agent.android.InterfaceC3316b;
import com.baidu.uaq.agent.android.UAQ;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.C0719b;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0725b;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0742e;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;
import p001a.p058b.p062b.p063a.p064a.p081k.C0771c;
import p001a.p058b.p062b.p063a.p064a.p081k.C0775g;

/* renamed from: a.b.b.a.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0710b extends AbstractC0747d {

    /* renamed from: b */
    public static final UAQ f2139b = UAQ.getInstance();

    /* renamed from: c */
    public int f2140c;

    /* renamed from: d */
    public int f2141d;

    /* renamed from: e */
    public final UUID f2142e;

    /* renamed from: f */
    public final String f2143f;

    /* renamed from: g */
    public final long f2144g;

    /* renamed from: h */
    public final String f2145h;

    /* renamed from: i */
    public final String f2146i;

    /* renamed from: j */
    public C0715e f2147j;

    /* renamed from: k */
    public C0709a f2148k;

    /* renamed from: l */
    public C0716f f2149l;

    /* renamed from: m */
    public List<C0718h> f2150m;

    public C0710b(Throwable th) {
        this.f2140c = 1;
        InterfaceC3316b m22326a = C0719b.m22326a();
        Throwable cause = th.getCause();
        th = cause != null ? m22338a(cause) : th;
        this.f2142e = new UUID(C0775g.f2391a.nextLong(), C0775g.f2391a.nextLong());
        this.f2143f = "";
        this.f2144g = System.currentTimeMillis() / 1000;
        this.f2145h = f2139b.getConfig().getAPIKey();
        this.f2146i = f2139b.getConfig().getCuid();
        this.f2141d = f2139b.getConfig().getSourceType();
        this.f2147j = new C0715e(m22326a.mo17442e(), m22326a.mo17438i());
        this.f2148k = new C0709a(m22326a.mo17441f());
        this.f2149l = new C0716f(th);
        ArrayList arrayList = new ArrayList();
        C0718h c0718h = new C0718h(th);
        long j = c0718h.f2179c;
        arrayList.add(c0718h);
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();
            if (key.getId() != j) {
                arrayList.add(new C0718h(key, value));
            }
        }
        this.f2150m = arrayList;
    }

    public C0710b(UUID uuid, String str, long j) {
        this.f2140c = 1;
        this.f2142e = uuid;
        this.f2143f = str;
        this.f2144g = j;
        this.f2145h = f2139b.getConfig().getAPIKey();
        this.f2146i = f2139b.getConfig().getCuid();
        this.f2141d = f2139b.getConfig().getSourceType();
    }

    /* renamed from: a */
    public static Throwable m22338a(Throwable th) {
        Throwable cause = th.getCause();
        return cause == null ? th : m22338a(cause);
    }

    /* renamed from: b */
    public static C0710b m22337b(String str) {
        C0710b c0710b;
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
            String string = jSONObject.getString("uuid");
            c0710b = new C0710b(UUID.fromString(string), jSONObject.getString("buildId"), jSONObject.getLong("timestamp"));
        } catch (JSONException e) {
            e = e;
            c0710b = null;
        }
        try {
            c0710b.f2147j = C0715e.m22330a(jSONObject.getJSONObject("deviceInfo"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("appInfo");
            C0709a c0709a = new C0709a();
            try {
                c0709a.f2136b = jSONObject2.getString("appName");
                c0709a.f2137c = jSONObject2.getString("appVersion");
                c0709a.f2138d = jSONObject2.getString("bundleId");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            c0710b.f2148k = c0709a;
            JSONObject jSONObject3 = jSONObject.getJSONObject("exception");
            C0716f c0716f = new C0716f();
            try {
                c0716f.f2174b = jSONObject3.getString("name");
                c0716f.f2175c = jSONObject3.getString("cause");
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            c0710b.f2149l = c0716f;
            JSONArray jSONArray = jSONObject.getJSONArray("threads");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(C0718h.m22327a(jSONArray.getJSONObject(i)));
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
            c0710b.f2150m = arrayList;
        } catch (JSONException e5) {
            e = e5;
            e.printStackTrace();
            return c0710b;
        }
        return c0710b;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("protocolVersion", this.f2140c);
            jSONObject.put("uploadSource", this.f2141d);
            jSONObject.put("platform", "Android");
            jSONObject.put("uuid", this.f2142e.toString());
            jSONObject.put("buildId", this.f2143f);
            jSONObject.put("timestamp", Long.valueOf(this.f2144g));
            jSONObject.put("appToken", this.f2145h);
            jSONObject.put("cuid", C0771c.m22240a(this.f2146i));
            jSONObject.put("deviceInfo", this.f2147j.mo17429z());
            jSONObject.put("appInfo", this.f2148k.mo17429z());
            jSONObject.put("exception", this.f2149l.mo17429z());
            JSONArray jSONArray = new JSONArray();
            for (C0718h c0718h : this.f2150m) {
                jSONArray.put(c0718h.mo17429z());
            }
            jSONObject.put("threads", jSONArray);
            jSONObject.put("activityHistory", new JSONArray());
            C0725b c0725b = C0742e.f2275c.f2194d;
            if (c0725b != null) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    jSONArray2.put(0, c0725b.f2206c);
                    jSONArray2.put(1, c0725b.f2207d);
                } catch (JSONException e) {
                    C0725b.f2205b.mo17426a("Caught error while DataToken asJSONArray: ", e);
                    C0735a.m22302a(e);
                }
                jSONObject.put("dataToken", jSONArray2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
