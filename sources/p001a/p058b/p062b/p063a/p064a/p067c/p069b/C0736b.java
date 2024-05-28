package p001a.p058b.p062b.p063a.p064a.p067c.p069b;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;

/* renamed from: a.b.b.a.a.c.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0736b extends AbstractC0746c {

    /* renamed from: b */
    public String f2246b;

    /* renamed from: c */
    public String f2247c;

    /* renamed from: d */
    public String f2248d;

    /* renamed from: e */
    public StackTraceElement[] f2249e;

    /* renamed from: f */
    public final AtomicLong f2250f;

    /* renamed from: g */
    public Map<String, String> f2251g;

    public C0736b(Exception exc) {
        String name = Thread.currentThread().getName();
        String name2 = exc.getClass().getName();
        String message = exc.getMessage();
        StackTraceElement[] stackTrace = exc.getStackTrace();
        this.f2250f = new AtomicLong(1L);
        this.f2246b = name2;
        this.f2247c = message;
        this.f2248d = name;
        this.f2249e = stackTrace;
        this.f2251g = null;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, this.f2246b);
            jSONArray.put(1, this.f2247c != null ? this.f2247c : "");
            jSONArray.put(2, this.f2248d);
            JSONArray jSONArray2 = new JSONArray();
            for (StackTraceElement stackTraceElement : this.f2249e) {
                jSONArray2.put(stackTraceElement.toString());
            }
            jSONArray.put(3, jSONArray2);
            jSONArray.put(4, this.f2250f.get());
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.f2251g != null) {
                    for (Map.Entry<String, String> entry : this.f2251g.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(5, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONArray;
    }
}
