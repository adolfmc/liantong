package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DialogRedirectImpl extends DialogRedirect {

    /* renamed from: a */
    private final Activity f11149a;

    /* renamed from: b */
    private final int f11150b;

    /* renamed from: c */
    private final Intent f11151c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogRedirectImpl(Intent intent, Activity activity, int i) {
        this.f11151c = intent;
        this.f11149a = activity;
        this.f11150b = i;
    }

    @Override // com.huawei.hms.common.internal.DialogRedirect
    public final void redirect() {
        Intent intent = this.f11151c;
        if (intent != null) {
            this.f11149a.startActivityForResult(intent, this.f11150b);
        }
    }
}
