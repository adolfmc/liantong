package p001a.p058b.p062b.p063a.p064a.p078i;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONArray;
import org.json.JSONException;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.i.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0764a extends AbstractC0746c {

    /* renamed from: b */
    public static final InterfaceC3321a f2366b = C0749a.f2299a;

    /* renamed from: c */
    public long f2367c = System.currentTimeMillis();

    /* renamed from: d */
    public C0766b f2368d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.b.b.a.a.i.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC0765a {
        MEMORY,
        CPU
    }

    public C0764a(EnumC0765a enumC0765a) {
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        Object valueOf;
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, this.f2367c);
            C0766b c0766b = this.f2368d;
            if (c0766b.f2373b) {
                valueOf = c0766b.f2372a;
            } else {
                valueOf = Long.valueOf(c0766b.f2372a.longValue());
            }
            jSONArray.put(1, valueOf);
        } catch (JSONException e) {
            f2366b.mo17426a("Caught error while Sample asJSONArray: ", e);
            C0735a.m22302a(e);
        }
        return jSONArray;
    }

    /* renamed from: a */
    public void m22246a(double d) {
        this.f2368d = new C0766b(d);
    }
}
