package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.a.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0732i extends AbstractC0747d {

    /* renamed from: b */
    public static final InterfaceC3321a f2237b = C0749a.f2299a;

    /* renamed from: c */
    public String f2238c;

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: a */
    public JSONObject mo17432az() {
        try {
            return new JSONObject(this.f2238c);
        } catch (JSONException e) {
            f2237b.mo17426a("Caught error while Transmission asJSONObject: ", e);
            C0735a.m22302a(e);
            return null;
        }
    }
}
