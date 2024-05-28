package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.RequiresApi;

@RequiresApi(api = 26)
/* renamed from: com.ss.android.socialbase.appdownloader.mb.ox */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10136ox extends AbstractC10134mb {
    public C10136ox(Context context) {
        super(context, null, null);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + this.f19542mb.getPackageName()));
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        intent.addFlags(268435456);
        return intent;
    }
}
