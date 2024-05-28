package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.u */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10137u extends AbstractC10134mb {
    public C10137u(Context context) {
        super(context, null, null);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        Intent intent = new Intent("android.settings.SECURITY_SETTINGS");
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        return intent;
    }
}
