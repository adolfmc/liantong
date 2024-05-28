package com.bytedance.pangle.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import java.io.File;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3942b {

    /* renamed from: a */
    private static final char[] f9367a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    private static String f9368b;

    /* renamed from: a */
    public static boolean m16646a(@NonNull Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ComponentName componentName;
        String packageName = context.getPackageName();
        return (TextUtils.isEmpty(packageName) || (runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1)) == null || runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null || !packageName.equals(componentName.getPackageName())) ? false : true;
    }

    /* renamed from: b */
    public static String m16645b(Context context) {
        if (f9368b == null) {
            String[] m16644a = C3943c.m16644a(new File(context.getApplicationInfo().sourceDir));
            String str = m16644a[0];
            f9368b = str;
            if (TextUtils.isEmpty(str)) {
                ZeusLogger.m16788w("Zeus/init_pangle", "getHostIdentity failed. Reason: " + m16644a[2]);
            }
        }
        return f9368b;
    }

    /* renamed from: a */
    public static boolean m16647a() {
        try {
            return (Zeus.getAppApplication().getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
