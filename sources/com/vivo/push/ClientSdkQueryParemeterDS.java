package com.vivo.push;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.PushParcelable;
import org.json.JSONException;

/* renamed from: com.vivo.push.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ClientSdkQueryParemeterDS implements PushParcelable {

    /* renamed from: a */
    public static PushParcelable.InterfaceC10974a<ClientSdkQueryParemeterDS> f20958a = new C10944f();

    /* renamed from: b */
    private int f20959b;

    /* renamed from: c */
    private String f20960c;

    /* renamed from: d */
    private String f20961d;

    /* renamed from: e */
    private String f20962e;

    /* renamed from: f */
    private String f20963f;

    public ClientSdkQueryParemeterDS(int i, String str, String str2, String str3, String str4) {
        this.f20959b = i;
        this.f20960c = str;
        this.f20961d = str2;
        this.f20962e = str3;
        this.f20963f = str4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientSdkQueryParemeterDS(JsonParcel jsonParcel) throws JSONException {
        this.f20959b = jsonParcel.m5517a();
        this.f20960c = jsonParcel.m5510c();
        this.f20961d = jsonParcel.m5510c();
        this.f20962e = jsonParcel.m5510c();
        this.f20963f = jsonParcel.m5510c();
    }

    @Override // com.vivo.push.restructure.request.p379a.p380a.PushParcelable
    /* renamed from: a */
    public final String mo5508a() {
        JsonParcel jsonParcel = new JsonParcel();
        jsonParcel.m5516a(this.f20959b);
        jsonParcel.m5513a(this.f20960c);
        jsonParcel.m5513a(this.f20961d);
        jsonParcel.m5513a(this.f20962e);
        jsonParcel.m5513a(this.f20963f);
        return jsonParcel.m5509d();
    }
}
