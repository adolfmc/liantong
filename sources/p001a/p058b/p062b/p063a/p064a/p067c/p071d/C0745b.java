package p001a.p058b.p062b.p063a.p064a.p067c.p071d;

import com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: a.b.b.a.a.c.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0745b implements InterfaceC3319b {

    /* renamed from: a */
    public final InterfaceC3319b.EnumC3320a f2291a;

    public C0745b(InterfaceC3319b.EnumC3320a enumC3320a) {
        this.f2291a = enumC3320a;
    }

    @Override // com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        return null;
    }

    /* renamed from: a */
    public void m22272a(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Missing Harvestable field.");
        }
    }

    @Override // com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: az */
    public Object mo17432az() {
        int i = C0744a.f2290a[this.f2291a.ordinal()];
        return i != 1 ? i != 2 ? "" : mo17433U() : mo17429z();
    }

    @Override // com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: bk */
    public InterfaceC3319b.EnumC3320a mo17431bk() {
        return this.f2291a;
    }

    @Override // com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: bl */
    public String mo17430bl() {
        return mo17432az().toString();
    }

    @Override // com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        return null;
    }
}
