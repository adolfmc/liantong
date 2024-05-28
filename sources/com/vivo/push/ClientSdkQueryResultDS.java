package com.vivo.push;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.PushParcelable;
import org.json.JSONException;

/* renamed from: com.vivo.push.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ClientSdkQueryResultDS implements PushParcelable {

    /* renamed from: a */
    public static PushParcelable.InterfaceC10974a<ClientSdkQueryResultDS> f21014a = new C10961h();

    /* renamed from: b */
    private int f21015b;

    /* renamed from: c */
    private String f21016c;

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientSdkQueryResultDS(JsonParcel jsonParcel) throws JSONException {
        this.f21015b = jsonParcel.m5517a();
        this.f21016c = jsonParcel.m5510c();
    }

    /* renamed from: b */
    public final String m5665b() {
        return this.f21016c;
    }

    @Override // com.vivo.push.restructure.request.p379a.p380a.PushParcelable
    /* renamed from: a */
    public final String mo5508a() {
        JsonParcel jsonParcel = new JsonParcel();
        jsonParcel.m5516a(this.f21015b);
        jsonParcel.m5513a(this.f21016c);
        return jsonParcel.m5509d();
    }
}
