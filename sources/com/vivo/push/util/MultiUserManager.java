package com.vivo.push.util;

import android.os.Build;
import android.os.UserHandle;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class MultiUserManager {

    /* renamed from: a */
    private static int f21244a = -1;

    /* renamed from: a */
    public static int m5336a() {
        if (Build.VERSION.SDK_INT < 17) {
            return 0;
        }
        int i = f21244a;
        if (i != -1) {
            return i;
        }
        try {
            Method declaredMethod = UserHandle.class.getDeclaredMethod("myUserId", new Class[0]);
            declaredMethod.setAccessible(true);
            f21244a = ((Integer) declaredMethod.invoke(null, null)).intValue();
            LogUtil.m5341d("MultiUserManager", "getMyUserId = " + f21244a);
            return f21244a;
        } catch (Exception e) {
            LogUtil.m5354a("MultiUserManager", "getMyUserId error " + e.getMessage());
            return 0;
        }
    }
}
