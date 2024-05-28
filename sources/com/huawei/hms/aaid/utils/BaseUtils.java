package com.huawei.hms.aaid.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.opendevice.CommFun;
import com.huawei.hms.opendevice.HttpClient;
import com.huawei.hms.opendevice.PushClientSp;
import com.huawei.hms.opendevice.PushDataEncrypterManager;
import com.huawei.hms.opendevice.QueryGrs;
import com.huawei.hms.support.log.HMSLog;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BaseUtils {
    private BaseUtils() {
    }

    public static void clearSubjectIds(Context context) {
        PushClientSp.m14368a(context).removeKey("subjectId");
    }

    public static void delLocalToken(Context context, String str) {
        PushClientSp.m14368a(context).m14363c(str);
    }

    public static void deleteAllTokenCache(Context context) {
        PushClientSp.m14368a(context).m14369a();
    }

    public static void deleteCacheData(Context context, String str) {
        PushClientSp.m14368a(context).removeKey(str);
    }

    public static String getBaseUrl(Context context, String str, String str2, String str3, String str4) {
        return QueryGrs.m14361a(context, str, str2, str3, str4);
    }

    public static String getCacheData(Context context, String str, boolean z) {
        if (z) {
            return PushClientSp.m14368a(context).m14367a(str);
        }
        return PushClientSp.m14368a(context).getString(str);
    }

    public static String getLocalToken(Context context, String str) {
        return PushClientSp.m14368a(context).m14365b(str);
    }

    public static boolean getProxyInit(Context context) {
        return PushClientSp.m14368a(context).getBoolean("_proxy_init");
    }

    public static String[] getSubjectIds(Context context) {
        String string = PushClientSp.m14368a(context).getString("subjectId");
        if (TextUtils.isEmpty(string)) {
            return new String[0];
        }
        return string.split(",");
    }

    public static void initSecret(Context context) {
        PushDataEncrypterManager.m14362a(context);
    }

    public static boolean isMainProc(Context context) {
        String m14387a = CommFun.m14387a(context);
        String str = context.getApplicationInfo().processName;
        HMSLog.m14110i("BaseUtils", "main process name: " + str + ", current process name: " + m14387a);
        return str.equals(m14387a);
    }

    public static boolean saveCacheData(Context context, String str, String str2, boolean z) {
        if (z) {
            return PushClientSp.m14368a(context).m14366a(str, str2);
        }
        return PushClientSp.m14368a(context).saveString(str, str2);
    }

    public static void saveProxyInit(Context context, boolean z) {
        PushClientSp.m14368a(context).saveBoolean("_proxy_init", z);
    }

    public static void saveToken(Context context, String str, String str2) {
        PushClientSp.m14368a(context).m14364b(str, str2);
    }

    public static String sendPostRequest(Context context, String str, String str2, Map<String, String> map) {
        return HttpClient.m14376a(context, str, str2, map);
    }
}
