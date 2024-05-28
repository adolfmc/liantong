package p001a.p058b.p062b.p063a.p064a.p067c.p069b;

import com.baidu.uaq.agent.android.UAQ;
import org.json.JSONArray;
import p001a.p058b.p062b.p063a.p064a.C0762h;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0751c;

/* renamed from: a.b.b.a.a.c.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0735a extends AbstractC0746c {

    /* renamed from: b */
    public static final UAQ f2244b;

    /* renamed from: c */
    public final C0737c f2245c = new C0737c();

    static {
        C0751c c0751c = C0749a.f2299a;
        f2244b = UAQ.getInstance();
    }

    /* renamed from: a */
    public static void m22302a(Exception exc) {
        if (f2244b.getConfig().isCollectAgentHealth() && exc != null) {
            C0762h.m22248a(new C0736b(exc));
        }
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        JSONArray jSONArray = new JSONArray();
        if (!this.f2245c.f2252b.isEmpty()) {
            jSONArray.put(this.f2245c.mo17429z());
        }
        return jSONArray;
    }
}
