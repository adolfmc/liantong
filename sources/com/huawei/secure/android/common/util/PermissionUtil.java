package com.huawei.secure.android.common.util;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.huawei.secure.android.common.exception.NoPermissionCheckerException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PermissionUtil {

    /* renamed from: a */
    private static final String f12143a = "PermissionUtil";

    public static boolean checkCallingPermission(Context context, String str, String str2) throws NoPermissionCheckerException {
        if (Binder.getCallingPid() == Process.myPid()) {
            return false;
        }
        return checkPermission(context, str, Binder.getCallingPid(), Binder.getCallingUid(), str2);
    }

    public static boolean checkPermission(Context context, String str, int i, int i2, String str2) throws NoPermissionCheckerException {
        try {
            return context.getApplicationInfo().targetSdkVersion > 23 ? context.checkPermission(str, i, i2) == 0 : PermissionChecker.checkPermission(context, str, i, i2, str2) == 0;
        } catch (Throwable th) {
            String str3 = f12143a;
            Log.e(str3, "checkPermission: " + th.getMessage() + " , you should implementation support library or androidx library");
            throw new NoPermissionCheckerException("you should implementation support library or androidx library");
        }
    }

    public static boolean checkSelfPermission(Context context, String str) throws NoPermissionCheckerException {
        return checkPermission(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
