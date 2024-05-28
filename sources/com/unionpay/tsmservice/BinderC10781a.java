package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.unionpay.tsmservice.ITsmActivityCallback;

/* renamed from: com.unionpay.tsmservice.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class BinderC10781a extends ITsmActivityCallback.Stub {

    /* renamed from: a */
    private Context f20777a;

    public BinderC10781a(Context context) {
        this.f20777a = context;
    }

    @Override // com.unionpay.tsmservice.ITsmActivityCallback
    public final void startActivity(String str, String str2, int i, Bundle bundle) {
        ComponentName componentName = new ComponentName(str, str2);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (i != -1) {
            intent.setFlags(i);
        }
        intent.setComponent(componentName);
        this.f20777a.startActivity(intent);
    }
}
