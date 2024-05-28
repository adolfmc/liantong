package com.baidu.mapapi;

import android.content.Context;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comapi.Initializer;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SDKInitializer {
    public static final String SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR = "network error";
    public static final String SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR = "permission check error";
    public static final String SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK = "permission check ok";
    public static final String SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE = "error_code";
    public static final String SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_MESSAGE = "error_message";

    /* renamed from: a */
    private static CoordType f5845a = CoordType.BD09LL;

    public static void setHttpsEnable(boolean z) {
    }

    private SDKInitializer() {
    }

    public static void initialize(Context context) {
        Initializer.m18477a(context, false, null, null, null);
    }

    public static void initialize(String str, Context context) {
        Initializer.m18477a(context, false, null, str, null);
    }

    public static void initialize(Context context, boolean z, String str, String str2) {
        Initializer.m18477a(context, z, str, str2, null);
    }

    public static void setApiKey(String str) {
        PermissionCheck.setApiKey(str);
    }

    public static void setCoordType(CoordType coordType) {
        f5845a = coordType;
    }

    public static CoordType getCoordType() {
        return f5845a;
    }

    public static boolean isHttpsEnable() {
        return HttpClient.isHttpsEnable;
    }

    public static void setAgreePrivacy(Context context, boolean z) {
        Initializer.m18478a(context, z);
    }

    public static boolean getAgreePrivacy() {
        return Initializer.m18475b();
    }
}
