package com.vivo.push.restructure.request;

import android.content.Intent;
import android.os.Bundle;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.request.p379a.CFToCoreDS;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcelable;

/* renamed from: com.vivo.push.restructure.request.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class BaseCommand<I extends JsonParcelable, O extends JsonParcelable> {

    /* renamed from: a */
    private int f21121a = 2020;

    /* renamed from: b */
    private I f21122b;

    /* renamed from: a */
    public abstract O mo5521a(JsonParcel jsonParcel);

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseCommand(I i) {
        this.f21122b = i;
    }

    /* renamed from: a */
    public final Intent m5522a(int i) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("client_pkgname", PushClientController.m5593a().m5591b().getPackageName());
        bundle.putBoolean("support_rf", true);
        CFToCoreDS cFToCoreDS = new CFToCoreDS(this.f21121a, i);
        JsonParcel jsonParcel = new JsonParcel();
        cFToCoreDS.mo5506a(jsonParcel);
        bundle.putString("cf_content", jsonParcel.m5509d());
        JsonParcel jsonParcel2 = new JsonParcel();
        this.f21122b.mo5506a(jsonParcel2);
        bundle.putString("content", jsonParcel2.m5509d());
        intent.putExtras(bundle);
        return intent;
    }
}
