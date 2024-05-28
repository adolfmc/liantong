package com.vivo.push.restructure.request.p379a;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcelable;
import com.vivo.push.util.LogUtil;
import org.json.JSONException;

/* renamed from: com.vivo.push.restructure.request.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CFToClientDS implements JsonParcelable {

    /* renamed from: a */
    public static final JsonParcelable.InterfaceC10973a<CFToClientDS> f21123a = new C10975b();

    /* renamed from: b */
    private String f21124b;

    /* renamed from: c */
    private int f21125c;

    /* renamed from: d */
    private int f21126d;

    /* renamed from: e */
    private long f21127e;

    /* renamed from: f */
    private int f21128f;

    /* renamed from: g */
    private int f21129g;

    /* renamed from: a */
    public final int m5520a() {
        return this.f21125c;
    }

    /* renamed from: b */
    public final int m5519b() {
        return this.f21128f;
    }

    /* renamed from: c */
    public final int m5518c() {
        return this.f21129g;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFToClientDS(JsonParcel jsonParcel) {
        try {
            this.f21124b = jsonParcel.m5510c();
            this.f21125c = jsonParcel.m5517a();
            this.f21127e = jsonParcel.m5511b();
            this.f21128f = jsonParcel.m5517a();
            this.f21126d = jsonParcel.m5517a();
            this.f21129g = jsonParcel.m5517a();
        } catch (JSONException e) {
            LogUtil.m5352a("CFToClientDS", e);
        }
    }

    @Override // com.vivo.push.restructure.request.p379a.p380a.JsonParcelable
    /* renamed from: a */
    public final void mo5506a(JsonParcel jsonParcel) {
        jsonParcel.m5513a(this.f21124b);
        jsonParcel.m5516a(this.f21125c);
        jsonParcel.m5515a(this.f21127e);
        jsonParcel.m5516a(this.f21128f);
        jsonParcel.m5516a(this.f21126d);
        jsonParcel.m5516a(this.f21129g);
    }
}
