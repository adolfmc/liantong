package com.vivo.push.p370d;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.PushParcelable;
import org.json.JSONException;

/* renamed from: com.vivo.push.d.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ProfileInfoDS implements PushParcelable {

    /* renamed from: a */
    public static PushParcelable.InterfaceC10974a<ProfileInfoDS> f20942a = new C10937c();

    /* renamed from: b */
    private String f20943b;

    public ProfileInfoDS(String str) {
        this.f20943b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProfileInfoDS(JsonParcel jsonParcel) throws JSONException {
        this.f20943b = jsonParcel.m5510c();
    }

    @Override // com.vivo.push.restructure.request.p379a.p380a.PushParcelable
    /* renamed from: a */
    public final String mo5508a() {
        JsonParcel jsonParcel = new JsonParcel();
        jsonParcel.m5513a(this.f20943b);
        return jsonParcel.m5509d();
    }

    /* renamed from: b */
    public final String m5717b() {
        return this.f20943b;
    }
}
