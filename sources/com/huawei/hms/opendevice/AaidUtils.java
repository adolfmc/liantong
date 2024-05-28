package com.huawei.hms.opendevice;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.DeleteTokenReq;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AaidUtils {
    /* renamed from: a */
    public static DeleteTokenReq m14397a(Context context, String str, String str2, String str3, String str4) {
        DeleteTokenReq deleteTokenReq = new DeleteTokenReq();
        deleteTokenReq.setAppId(str);
        deleteTokenReq.setScope(str4);
        deleteTokenReq.setProjectId(str2);
        deleteTokenReq.setPkgName(context.getPackageName());
        deleteTokenReq.setSubjectId(str3);
        if (TextUtils.isEmpty(str)) {
            deleteTokenReq.setAppId(Util.getAppId(context));
        }
        if (TextUtils.isEmpty(str4)) {
            deleteTokenReq.setScope("HCM");
        }
        if (TextUtils.isEmpty(str2)) {
            deleteTokenReq.setProjectId(m14392c(context));
        }
        return deleteTokenReq;
    }

    /* renamed from: b */
    public static TokenReq m14394b(Context context, String str, String str2) {
        return m14393b(context, str, null, null, str2);
    }

    /* renamed from: c */
    public static String m14392c(Context context) {
        return AGConnectServicesConfig.fromContext(context).getString("client/project_id");
    }

    /* renamed from: d */
    public static boolean m14391d(Context context) {
        Bundle bundle;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                if (!TextUtils.isEmpty(bundle.getString("com.huawei.hms.client.service.name:base"))) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.m14112e(HmsInstanceId.TAG, "isIntegratedBaseSdk failed.");
            return true;
        }
    }

    /* renamed from: b */
    public static TokenReq m14395b(Context context, String str) {
        return m14393b(context, null, null, str, null);
    }

    /* renamed from: b */
    public static TokenReq m14393b(Context context, String str, String str2, String str3, String str4) {
        TokenReq tokenReq = new TokenReq();
        tokenReq.setPackageName(context.getPackageName());
        tokenReq.setAppId(str);
        tokenReq.setScope(str4);
        tokenReq.setProjectId(str2);
        tokenReq.setSubjectId(str3);
        tokenReq.setMultiSender(false);
        if (TextUtils.isEmpty(str)) {
            tokenReq.setAppId(Util.getAppId(context));
        }
        if (TextUtils.isEmpty(str2)) {
            tokenReq.setProjectId(m14392c(context));
        }
        if (TextUtils.isEmpty(str4)) {
            tokenReq.setScope("HCM");
        }
        PushClientSp m14368a = PushClientSp.m14368a(context);
        if (!m14368a.getBoolean("hasRequestAgreement")) {
            tokenReq.setFirstTime(true);
            m14368a.saveBoolean("hasRequestAgreement", true);
        } else {
            tokenReq.setFirstTime(false);
        }
        return tokenReq;
    }

    /* renamed from: a */
    public static DeleteTokenReq m14398a(Context context, String str, String str2) {
        return m14397a(context, str, null, null, str2);
    }

    /* renamed from: a */
    public static DeleteTokenReq m14399a(Context context, String str) {
        return m14397a(context, null, null, str, null);
    }

    /* renamed from: a */
    public static DeleteTokenReq m14400a(Context context) {
        return m14397a(context, null, null, null, null);
    }

    /* renamed from: b */
    public static synchronized String m14396b(Context context) {
        String uuid;
        synchronized (AaidUtils.class) {
            PushPreferences pushPreferences = new PushPreferences(context, "aaid");
            if (pushPreferences.containsKey("aaid")) {
                uuid = pushPreferences.getString("aaid");
            } else {
                uuid = UUID.randomUUID().toString();
                pushPreferences.saveString("aaid", uuid);
                pushPreferences.saveLong("creationTime", Long.valueOf(System.currentTimeMillis()));
            }
        }
        return uuid;
    }
}
