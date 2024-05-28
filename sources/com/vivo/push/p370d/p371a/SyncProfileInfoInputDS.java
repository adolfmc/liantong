package com.vivo.push.p370d.p371a;

import android.text.TextUtils;
import com.vivo.push.p370d.ProfileInfoDS;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* renamed from: com.vivo.push.d.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SyncProfileInfoInputDS implements JsonParcelable {

    /* renamed from: a */
    public static final JsonParcelable.InterfaceC10973a<SyncProfileInfoInputDS> f20938a = new C10936c();

    /* renamed from: b */
    private String f20939b;

    /* renamed from: c */
    private List<ProfileInfoDS> f20940c;

    /* renamed from: d */
    private int f20941d;

    public SyncProfileInfoInputDS(String str, List<ProfileInfoDS> list, int i) {
        this.f20940c = new ArrayList();
        this.f20939b = str;
        this.f20941d = i;
        this.f20940c = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SyncProfileInfoInputDS(JsonParcel jsonParcel) throws JSONException {
        this.f20940c = new ArrayList();
        this.f20939b = jsonParcel.m5510c();
        this.f20941d = jsonParcel.m5517a();
        jsonParcel.m5514a(ProfileInfoDS.f20942a, this.f20940c);
    }

    @Override // com.vivo.push.restructure.request.p379a.p380a.JsonParcelable
    /* renamed from: a */
    public final void mo5506a(JsonParcel jsonParcel) {
        jsonParcel.m5513a(this.f20939b);
        jsonParcel.m5516a(this.f20941d);
        jsonParcel.m5512a(this.f20940c);
    }

    /* renamed from: a */
    public final List<String> m5719a() {
        ArrayList arrayList = new ArrayList();
        for (ProfileInfoDS profileInfoDS : this.f20940c) {
            String m5717b = profileInfoDS.m5717b();
            if (!TextUtils.isEmpty(m5717b)) {
                arrayList.add(m5717b);
            }
        }
        return arrayList;
    }
}
