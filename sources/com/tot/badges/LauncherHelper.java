package com.tot.badges;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LauncherHelper {
    public static final String GOOGLE = "google";
    public static final String HUAWEI = "huawei";
    public static final String MEIZU = "meizu";
    public static final String OPPO = "oppo";
    public static final String SAMSUNG = "samsung";
    public static final String VIVO = "vivo";
    public static final String XIAOMI = "xiaomi";
    private static Map<String, String> launcherMap = new HashMap();

    static {
        launcherMap.put("com.huawei.android.launcher", "huawei");
        launcherMap.put("com.miui.home", "xiaomi");
        launcherMap.put("com.sec.android.app.launcher", "samsung");
        launcherMap.put("com.google.android.apps.nexuslauncher", "google");
        launcherMap.put("com.bbk.launcher2", "vivo");
        launcherMap.put("com.oppo.launcher", "oppo");
    }

    @Nullable
    public String getLauncherTypeByName(@NonNull String str) {
        return launcherMap.get(str);
    }

    @Nullable
    public String getLauncherPackageName(@NonNull Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity.activityInfo == null || resolveActivity.activityInfo.packageName.equals("android")) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }
}
