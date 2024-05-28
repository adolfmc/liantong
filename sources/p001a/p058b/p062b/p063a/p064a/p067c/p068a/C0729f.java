package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0729f extends AbstractC0747d {

    /* renamed from: b */
    public static final InterfaceC3321a f2229b = C0749a.f2299a;

    /* renamed from: c */
    public static final UAQ f2230c = UAQ.getInstance();

    /* renamed from: d */
    public final List<C0728e> f2231d = new ArrayList();

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        List<C0728e> m22310a = m22310a();
        JSONArray jSONArray = new JSONArray();
        for (C0728e c0728e : m22310a) {
            jSONArray.put(c0728e.mo17432az());
        }
        return jSONArray;
    }

    /* renamed from: a */
    public final List<C0728e> m22310a() {
        synchronized (this) {
            if (this.f2231d.size() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(this.f2231d);
            this.f2231d.clear();
            return arrayList;
        }
    }

    /* renamed from: a */
    public synchronized void m22309a(C0728e c0728e) {
        this.f2231d.add(c0728e);
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cycle", 10);
            List<C0728e> m22310a = m22310a();
            JSONArray jSONArray = new JSONArray();
            for (C0728e c0728e : m22310a) {
                jSONArray.put(c0728e.mo17432az());
            }
            jSONObject.put("resourcearray", jSONArray);
        } catch (JSONException e) {
            f2229b.mo17426a("Caught error while ResourceDatas asJSONObject: ", e);
            C0735a.m22302a(e);
        }
        return jSONObject;
    }
}
