package com.huawei.hms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IOUtils;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AGCUtils {
    /* renamed from: b */
    private static String m15088b(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.m14112e("AGCUtils", "In getMetaDataCpId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("com.huawei.hms.client.cpid")) != null) {
                String valueOf = String.valueOf(obj);
                return valueOf.startsWith("cpid=") ? valueOf.substring(5) : valueOf;
            }
            HMSLog.m14110i("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        } catch (AndroidException unused) {
            HMSLog.m14112e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        } catch (RuntimeException e) {
            HMSLog.m14111e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.", e);
            return "";
        }
    }

    /* renamed from: c */
    private static boolean m15087c(Context context) {
        return context.getPackageName().equals(HMSPackageManager.getInstance(context).getHMSPackageNameForMultiService());
    }

    public static String getAppId(Context context) {
        String str;
        if (m15087c(context)) {
            str = m15089a(context, "client/app_id");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        } else {
            str = null;
        }
        try {
            AGConnectInstance aGConnectInstance = AGConnectInstance.getInstance();
            if (aGConnectInstance.getContext() != context) {
                aGConnectInstance = AGConnectInstance.buildInstance(new AGConnectOptionsBuilder().build(context));
            }
            str = aGConnectInstance.getOptions().getString("client/app_id");
        } catch (NullPointerException unused) {
            HMSLog.m14112e("AGCUtils", "Get appId with AGConnectServicesConfig failed");
        }
        if (TextUtils.isEmpty(str)) {
            String m15090a = m15090a(context);
            return !TextUtils.isEmpty(m15090a) ? m15090a : m15089a(context, "client/app_id");
        }
        return str;
    }

    public static String getCpId(Context context) {
        if (m15087c(context)) {
            return m15089a(context, "client/cp_id");
        }
        String str = null;
        try {
            AGConnectInstance aGConnectInstance = AGConnectInstance.getInstance();
            if (aGConnectInstance.getContext() != context) {
                aGConnectInstance = AGConnectInstance.buildInstance(new AGConnectOptionsBuilder().build(context));
            }
            str = aGConnectInstance.getOptions().getString("client/cp_id");
        } catch (NullPointerException unused) {
            HMSLog.m14112e("AGCUtils", "Get cpid with AGConnectServicesConfig failed");
        }
        if (TextUtils.isEmpty(str)) {
            String m15088b = m15088b(context);
            return !TextUtils.isEmpty(m15088b) ? m15088b : m15089a(context, "client/cp_id");
        }
        return str;
    }

    /* renamed from: a */
    private static String m15089a(Context context, String str) {
        String str2 = "";
        InputStream inputStream = null;
        try {
            try {
                try {
                    AGConnectOptionsBuilder aGConnectOptionsBuilder = new AGConnectOptionsBuilder();
                    inputStream = context.getResources().getAssets().open("agconnect-services.json");
                    aGConnectOptionsBuilder.setInputStream(inputStream);
                    str2 = aGConnectOptionsBuilder.build(context).getString(str);
                } catch (NullPointerException e) {
                    HMSLog.m14112e("AGCUtils", "Get " + str + " with AGConnectServicesConfig failed: " + e);
                }
            } catch (IOException e2) {
                HMSLog.m14112e("AGCUtils", "Get " + str + " failed: " + e2);
            }
            IOUtils.closeQuietly(inputStream);
            if (TextUtils.isEmpty(str2)) {
                HMSLog.m14112e("AGCUtils", "The " + str + " is null.");
                return "";
            }
            return str2;
        } catch (Throwable th) {
            IOUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    /* renamed from: a */
    private static String m15090a(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.m14112e("AGCUtils", "In getMetaDataAppId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("com.huawei.hms.client.appid")) != null) {
                String valueOf = String.valueOf(obj);
                return valueOf.startsWith("appid=") ? valueOf.substring(6) : valueOf;
            }
            HMSLog.m14112e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (AndroidException unused) {
            HMSLog.m14112e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (RuntimeException e) {
            HMSLog.m14111e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.", e);
            return "";
        }
    }
}
