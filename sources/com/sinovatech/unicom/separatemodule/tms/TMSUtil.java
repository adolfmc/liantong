package com.sinovatech.unicom.separatemodule.tms;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.support.p083v4.content.FileProvider;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TMSUtil {
    private static final String TAG = "TMSUtil";

    public static String getMD5(String str) {
        byte[] bArr;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            bArr = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            UIUtils.logE("TMSUtil", "getMD5 (NoSuchAlgorithmException)" + e.getMessage());
            bArr = null;
        }
        StringBuilder sb = new StringBuilder(new BigInteger(1, bArr).toString(16));
        for (int i = 0; i < 32 - sb.length(); i++) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public static boolean isPkgInstalled(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Throwable th) {
            UIUtils.logD("TMSUtil", "IsPkgInstalled " + th.getMessage());
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean installApkByPath(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (Build.VERSION.SDK_INT >= 24) {
                intent.setDataAndType(FileProvider.getUriForFile(context, "com.sinovatech.unicom.ui.fileprovider", new File(str)), "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
            }
            intent.setFlags(268435456);
            intent.addFlags(1);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            UIUtils.logE("TMSUtil", "installApkByPath " + th.getMessage());
            return false;
        }
    }

    public static boolean checkIsUnicomSimCard(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 22 || DeviceHelper.getNETType(context).equalsIgnoreCase("Wifi") || !SystemServiceUtils.netIsAvailable()) {
                return false;
            }
            for (SubscriptionInfo subscriptionInfo : SubscriptionManager.from(context).getActiveSubscriptionInfoList()) {
                if (subscriptionInfo.getMnc() == 1 && Build.VERSION.SDK_INT >= 24) {
                    SubscriptionManager.from(context);
                    if (SubscriptionManager.getDefaultDataSubscriptionId() == subscriptionInfo.getSubscriptionId()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
