package com.unionpay.tsmservice.p364mi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.unionpay.tsmservice.p364mi.ITsmActivityCallback;

/* renamed from: com.unionpay.tsmservice.mi.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class BinderC10802a extends ITsmActivityCallback.Stub {

    /* renamed from: a */
    private Context f20826a;

    public BinderC10802a(Context context) {
        this.f20826a = context;
    }

    @Override // com.unionpay.tsmservice.p364mi.ITsmActivityCallback
    public final void startActivity(String str, String str2, int i, Bundle bundle) {
        ComponentName componentName = new ComponentName(str, str2);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (i != -1) {
            intent.setFlags(i);
        }
        intent.setComponent(componentName);
        this.f20826a.startActivity(intent);
    }
}
