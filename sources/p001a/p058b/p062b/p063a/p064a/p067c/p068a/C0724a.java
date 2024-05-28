package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONArray;
import org.json.JSONException;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0724a extends AbstractC0746c {

    /* renamed from: b */
    public static final InterfaceC3321a f2201b = C0749a.f2299a;

    /* renamed from: c */
    public String f2202c;

    /* renamed from: d */
    public String f2203d;

    /* renamed from: e */
    public String f2204e;

    public C0724a(String str, String str2, String str3) {
        this.f2202c = str;
        this.f2203d = str2;
        this.f2204e = str3;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        JSONArray jSONArray = new JSONArray();
        try {
            m22272a(this.f2202c);
            jSONArray.put(0, this.f2202c);
            m22272a(this.f2203d);
            jSONArray.put(1, this.f2203d);
            m22272a(this.f2204e);
            jSONArray.put(2, this.f2204e);
        } catch (JSONException e) {
            f2201b.mo17426a("Caught error while ApplicationInformation asJSONArray", e);
            C0735a.m22302a(e);
        }
        return jSONArray;
    }
}
