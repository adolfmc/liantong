package com.huawei.secure.android.common.ssl.util;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ContextUtil {

    /* renamed from: a */
    private static Context f12106a;

    public static Context getInstance() {
        return f12106a;
    }

    public static void setContext(Context context) {
        if (context == null || f12106a != null) {
            return;
        }
        f12106a = context.getApplicationContext();
    }
}
