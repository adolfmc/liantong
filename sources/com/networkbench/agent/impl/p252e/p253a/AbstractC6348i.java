package com.networkbench.agent.impl.p252e.p253a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6348i {

    /* renamed from: b */
    protected static final InterfaceC6393e f15968b = C6394f.m10150a();

    /* renamed from: a */
    protected Activity f15969a;

    /* renamed from: a */
    public abstract boolean mo10324a();

    /* renamed from: b */
    public abstract Intent mo10322b();

    public AbstractC6348i(Activity activity) {
        this.f15969a = activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @TargetApi(19)
    /* renamed from: a */
    public boolean m10323a(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke((AppOpsManager) this.f15969a.getSystemService("appops"), Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), this.f15969a.getPackageName())).intValue() == 0;
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = f15968b;
                interfaceC6393e.mo10122a("error ignore:" + e.getMessage());
            }
        } else {
            f15968b.mo10122a("Below API 19 cannot invoke!");
        }
        return false;
    }
}
