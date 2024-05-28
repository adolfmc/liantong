package android.support.p083v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.app.AppLaunchChecker */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AppLaunchChecker {
    private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";
    private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";

    public static boolean hasStartedFromLauncher(@NonNull Context context) {
        return context.getSharedPreferences("android.support.AppLaunchChecker", 0).getBoolean("startedFromLauncher", false);
    }

    public static void onActivityCreate(@NonNull Activity activity) {
        Intent intent;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("android.support.AppLaunchChecker", 0);
        if (sharedPreferences.getBoolean("startedFromLauncher", false) || (intent = activity.getIntent()) == null || !"android.intent.action.MAIN".equals(intent.getAction())) {
            return;
        }
        if (intent.hasCategory("android.intent.category.LAUNCHER") || intent.hasCategory("android.intent.category.LEANBACK_LAUNCHER")) {
            sharedPreferences.edit().putBoolean("startedFromLauncher", true).apply();
        }
    }
}
