package com.mob.tools.utils;

import android.text.TextUtils;
import com.mob.tools.MobLog;

/* renamed from: com.mob.tools.utils.i */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC6218i extends Thread {
    /* renamed from: a */
    protected abstract void mo10997a() throws Throwable;

    public AbstractC6218i() {
    }

    public AbstractC6218i(String str) {
        if (TextUtils.isEmpty("M-")) {
            return;
        }
        setName("M-" + str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            mo10997a();
        } catch (Throwable th) {
            mo10996a(th);
        }
    }

    /* renamed from: a */
    protected void mo10996a(Throwable th) {
        MobLog.getInstance().m11341d(th);
    }
}
