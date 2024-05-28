package com.froad.froadeid.base.libs.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MiuiUtils {
    private static final int OPS_NFC = 10016;
    private static final int OPS_WIFI = 10001;
    private static final String TAG = "MiuiUtils";

    public static void gotoMIUIPermissionAct(Context context) {
        jumpToPermissionsEditorActivity(context);
    }

    public static boolean hasMIUINfcPermission(Context context) {
        return isHasPermission(context, 10016);
    }

    public static boolean hasMIUIWifiPermission(Context context) {
        return isHasPermission(context, 10001);
    }

    private static boolean isHasPermission(Context context, int i) {
        if (isMIUI()) {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
                Class<?> cls = appOpsManager.getClass();
                Class<?> cls2 = Integer.TYPE;
                Method declaredMethod = cls.getDeclaredMethod("checkOpNoThrow", cls2, cls2, String.class);
                declaredMethod.setAccessible(true);
                return 1 != ((Integer) declaredMethod.invoke(appOpsManager, Integer.valueOf(i), Integer.valueOf(Process.myUid()), context.getPackageName())).intValue();
            } catch (Exception e) {
                TMKeyLog.m16309e("MiuiUtils", "hasMIUIPermission: " + e.toString());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean isMIUI() {
        return "xiaomi".equalsIgnoreCase(Build.MANUFACTURER);
    }

    private static void jumpToPermissionsEditorActivity(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            intent.putExtra("extra_pkgname", context.getPackageName());
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            TMKeyLog.m16307i("MiuiUtils", "jumpToPermissionsEditorActivity: " + e.toString());
            try {
                Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent2.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                intent2.putExtra("extra_pkgname", context.getPackageName());
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            } catch (Exception unused) {
                TMKeyLog.m16307i("MiuiUtils", "jumpToPermissionsEditorActivity2: " + e.toString());
                Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                Uri fromParts = Uri.fromParts("package", context.getPackageName(), null);
                intent3.addFlags(268435456);
                intent3.setData(fromParts);
                context.startActivity(intent3);
            }
        }
    }
}
