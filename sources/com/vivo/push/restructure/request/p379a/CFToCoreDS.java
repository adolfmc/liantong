package com.vivo.push.restructure.request.p379a;

import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcelable;
import org.json.JSONException;

/* renamed from: com.vivo.push.restructure.request.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class CFToCoreDS implements JsonParcelable {

    /* renamed from: a */
    public static final JsonParcelable.InterfaceC10973a<CFToCoreDS> f21133a = new C10976d();

    /* renamed from: b */
    private String f21134b;

    /* renamed from: c */
    private int f21135c;

    /* renamed from: d */
    private long f21136d;

    /* renamed from: e */
    private int f21137e;

    public CFToCoreDS(int i, int i2) {
        this.f21134b = PushClientController.m5593a().m5591b().getPackageName();
        this.f21135c = i;
        this.f21136d = 341L;
        this.f21137e = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CFToCoreDS(JsonParcel jsonParcel) {
        try {
            this.f21134b = jsonParcel.m5510c();
            this.f21135c = jsonParcel.m5517a();
            this.f21136d = jsonParcel.m5511b();
            this.f21137e = jsonParcel.m5517a();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.vivo.push.restructure.request.p379a.p380a.JsonParcelable
    /* renamed from: a */
    public final void mo5506a(JsonParcel jsonParcel) {
        jsonParcel.m5513a(this.f21134b);
        jsonParcel.m5516a(this.f21135c);
        jsonParcel.m5515a(this.f21136d);
        jsonParcel.m5516a(this.f21137e);
    }
}
