package com.huawei.hms.framework.common;

import android.annotation.SuppressLint;
import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ContextHolder {
    private static final String TAG = "ContextHolder";
    @SuppressLint({"StaticFieldLeak"})
    private static Context sAppContext;
    @SuppressLint({"StaticFieldLeak"})
    private static Context sKitContext;

    public static Context getAppContext() {
        return sAppContext;
    }

    public static Context getResourceContext() {
        if (getKitContext() != null) {
            return getKitContext();
        }
        return getAppContext();
    }

    public static Context getKitContext() {
        return sKitContext;
    }

    public static void setAppContext(Context context) {
        CheckParamUtils.checkNotNull(context, "sAppContext == null");
        sAppContext = context.getApplicationContext();
    }

    public static void setKitContext(Context context) {
        CheckParamUtils.checkNotNull(context, "sKitContext == null");
        sKitContext = context;
    }
}
