package p001a.p058b.p059a.p060a.p061a;

import android.content.Context;
import android.util.Log;
import java.util.Random;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;

/* renamed from: a.b.a.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0704b {

    /* renamed from: a */
    public String f2106a;

    /* renamed from: b */
    public String f2107b;

    /* renamed from: c */
    public int f2108c;

    /* renamed from: d */
    public int f2109d;

    /* renamed from: e */
    public int f2110e;

    /* renamed from: f */
    public int f2111f;

    /* renamed from: g */
    public C0705c f2112g;

    /* renamed from: h */
    public C0706d f2113h;

    /* renamed from: i */
    public C0703a f2114i;

    /* renamed from: j */
    public JSONObject f2115j;

    /* renamed from: k */
    public JSONObject f2116k;

    public C0704b(Context context, String str) {
        this.f2112g = new C0705c(context, str);
        this.f2113h = new C0706d(context);
        this.f2114i = new C0703a(context);
    }

    /* renamed from: a */
    public JSONObject m22344a() {
        if (this.f2116k == null) {
            this.f2116k = new JSONObject();
            try {
                this.f2116k.put("device", this.f2112g.m22341a());
                this.f2116k.put("network", this.f2113h.m22339b());
                this.f2116k.put("app", this.f2114i.m22345a());
            } catch (Exception e) {
                outline.m24438a(e, outline.m24437a(""), "BaseInfo");
            }
        }
        return this.f2116k;
    }

    /* renamed from: a */
    public void m22343a(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        this.f2106a = str;
        StringBuilder m24437a = outline.m24437a("");
        m24437a.append(System.currentTimeMillis());
        StringBuilder m24437a2 = outline.m24437a(m24437a.toString());
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz".charAt(random.nextInt(26)));
        }
        m24437a2.append(stringBuffer.toString());
        this.f2107b = m24437a2.toString();
        StringBuilder m24437a3 = outline.m24437a("Current vvid is:");
        m24437a3.append(this.f2107b);
        Log.i("BaseInfo", m24437a3.toString());
        this.f2115j = null;
    }

    /* renamed from: b */
    public JSONObject m22342b() {
        if (this.f2115j == null) {
            this.f2115j = new JSONObject();
            try {
                this.f2115j.put("url", this.f2106a);
                this.f2115j.put("videoFrameRate", this.f2108c);
                this.f2115j.put("videobitRate", this.f2109d);
                this.f2115j.put("videoHeight", this.f2110e);
                this.f2115j.put("videoWidth", this.f2111f);
            } catch (Exception e) {
                outline.m24438a(e, outline.m24437a(""), "BaseInfo");
            }
        }
        return this.f2115j;
    }
}
