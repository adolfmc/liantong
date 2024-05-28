package com.gmrz.appsdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EnvObserver {
    private static final String ENV_SPEC_BUILD_NUMBER = "build_number";
    private static final String ENV_SPEC_RELEASE = "release";
    private static final String ENV_SPEC_SECURITY_PATCH = "security_patch";
    private static final String FIDO_CLIENT_ASM_INFO = "fidoInfo";
    private static final String SDK_SPEC_AUTHENTICATOR_NUMBER = "AuthenticatorVersion";
    private static final String SP_NAME = "env";
    private static final String TAG = "EnvObserver";

    private static String fidoComponentInfo(Activity activity) {
        StringBuilder sb = new StringBuilder();
        ResolveInfo queryCapableActivity = CapableComponentUtil.getInstance(activity).queryCapableActivity();
        ResolveInfo queryCapableService = CapableComponentUtil.getInstance(activity).queryCapableService();
        ResolveInfo queryCapableASMActivity = CapableComponentUtil.getInstance(activity).queryCapableASMActivity();
        if (queryCapableActivity != null) {
            try {
                Log.wtf("EnvObserver", "** communication use Intent with Activity **");
                String str = queryCapableActivity.activityInfo.name;
                sb.append(queryCapableActivity.activityInfo.packageName + "#" + str);
                sb.append("#");
            } catch (Exception e) {
                Log.wtf("EnvObserver", e.getMessage());
                return "unknown";
            }
        }
        if (queryCapableService != null) {
            Log.wtf("EnvObserver", "**can communication use AIDL with Service **");
            String str2 = queryCapableService.serviceInfo.name;
            sb.append(queryCapableService.serviceInfo.packageName + "#" + str2);
            sb.append("#");
        }
        if (queryCapableASMActivity != null) {
            Log.wtf("EnvObserver", "**ASM communication use Intent with Activity **");
            String str3 = queryCapableASMActivity.activityInfo.name;
            sb.append(queryCapableASMActivity.activityInfo.packageName + "#" + str3);
        }
        Log.wtf("EnvObserver", "**Intent with Activity **    ------" + ((Object) sb));
        return TextUtils.isEmpty(sb.toString()) ? Base64.encodeToString(UACUtil.getSHA256(sb.toString().getBytes()), 2) : "unknown";
    }

    private static String[] getEnvSpec(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("env", 0);
        return new String[]{sharedPreferences.getString("release", ""), sharedPreferences.getString("security_patch", ""), sharedPreferences.getString("build_number", ""), sharedPreferences.getString("AuthenticatorVersion", ""), sharedPreferences.getString("fidoInfo", "")};
    }

    public static boolean isEnvChange(Context context) {
        String[] envSpec = getEnvSpec(context);
        String str = Build.VERSION.RELEASE;
        String str2 = Build.DISPLAY;
        String str3 = Build.VERSION.SDK_INT >= 23 ? Build.VERSION.SECURITY_PATCH : "";
        String fidoComponentInfo = fidoComponentInfo((Activity) context);
        if (str.equals(envSpec[0]) && str3.equals(envSpec[1]) && str2.equals(envSpec[2]) && "1.3".equals(envSpec[3]) && fidoComponentInfo.equalsIgnoreCase(envSpec[4])) {
            return false;
        }
        Logger.wtf("EnvObserver", "Android OS Env or fido info has changed.");
        saveEnvSpec(context, str, str3, str2, "1.3", fidoComponentInfo);
        return true;
    }

    private static void saveEnvSpec(Context context, String str, String str2, String str3, String str4, String str5) {
        context.getSharedPreferences("env", 0).edit().putString("release", str).putString("security_patch", str2).putString("build_number", str3).putString("AuthenticatorVersion", str4).putString("fidoInfo", str5).apply();
    }
}
