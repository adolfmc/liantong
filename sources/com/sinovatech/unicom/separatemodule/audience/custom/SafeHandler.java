package com.sinovatech.unicom.separatemodule.audience.custom;

import android.os.Handler;
import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SafeHandler extends Handler {
    private static final SafeHandler instance = new SafeHandler();

    private SafeHandler() {
        super(Looper.getMainLooper());
    }

    public static final SafeHandler getInst() {
        return instance;
    }
}
