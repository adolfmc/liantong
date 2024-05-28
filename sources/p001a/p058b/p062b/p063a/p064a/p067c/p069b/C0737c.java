package p001a.p058b.p062b.p063a.p064a.p067c.p069b;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;

/* renamed from: a.b.b.a.a.c.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0737c extends AbstractC0747d {

    /* renamed from: b */
    public final Map<String, C0736b> f2252b = new ConcurrentHashMap();

    /* renamed from: a */
    public void m22301a() {
        synchronized (this.f2252b) {
            this.f2252b.clear();
        }
    }

    /* renamed from: a */
    public void m22300a(C0736b c0736b) {
        String str = c0736b.f2246b + c0736b.f2249e[0].toString();
        synchronized (this.f2252b) {
            C0736b c0736b2 = this.f2252b.get(str);
            if (c0736b2 == null) {
                this.f2252b.put(str, c0736b);
            } else {
                c0736b2.f2250f.getAndIncrement();
            }
        }
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            for (C0736b c0736b : this.f2252b.values()) {
                jSONArray.put(c0736b.mo17433U());
            }
            jSONObject.put("Type", "AgentErrors");
            jSONObject.put("Data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
