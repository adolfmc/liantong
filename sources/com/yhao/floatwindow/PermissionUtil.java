package com.yhao.floatwindow;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PermissionUtil {
    public static boolean hasPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        return hasPermissionBelowMarshmallow(context);
    }

    public static boolean hasPermissionOnActivityResult(Context context) {
        if (Build.VERSION.SDK_INT == 26) {
            return hasPermissionForO(context);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        return hasPermissionBelowMarshmallow(context);
    }

    public static boolean hasPermissionBelowMarshmallow(Context context) {
        try {
            return ((Integer) AppOpsManager.class.getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke((AppOpsManager) context.getSystemService("appops"), 24, Integer.valueOf(Binder.getCallingUid()), context.getApplicationContext().getPackageName())).intValue() == 0;
        } catch (Exception unused) {
            return false;
        }
    }

    @RequiresApi(api = 23)
    private static boolean hasPermissionForO(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            View view = new View(context);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(0, 0, Build.VERSION.SDK_INT >= 26 ? 2038 : 2003, 24, -2);
            view.setLayoutParams(layoutParams);
            windowManager.addView(view, layoutParams);
            windowManager.removeView(view);
            return true;
        } catch (Exception e) {
            LogUtil.m2253e("hasPermissionForO e:" + e.toString());
            return false;
        }
    }
}
