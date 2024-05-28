package com.huawei.secure.android.common.sign;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.secure.android.common.util.LogsUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HiPkgSignManager {

    /* renamed from: a */
    private static final String f11982a = "HiPkgSignManager";

    /* renamed from: a */
    private static PackageInfo m13938a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageArchiveInfo(str, 64);
            }
            return null;
        } catch (Exception e) {
            LogsUtil.m13814e("HiPkgSignManager", "Exception : " + e.getMessage(), true);
            return null;
        }
    }

    /* renamed from: b */
    private static String m13935b(byte[] bArr) {
        try {
            return m13937a(MessageDigest.getInstance("SHA-256").digest(bArr));
        } catch (NoSuchAlgorithmException e) {
            LogsUtil.m13819e("HiPkgSignManager", "NoSuchAlgorithmException" + e.getMessage());
            return "";
        }
    }

    public static boolean doCheckArchiveApk(Context context, String str, String str2, String str3) {
        PackageInfo m13938a;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || context == null || TextUtils.isEmpty(str3) || (m13938a = m13938a(context, str2)) == null) {
            return false;
        }
        return str.equalsIgnoreCase(m13935b(m13938a.signatures[0].toByteArray())) && str3.equals(m13938a.packageName);
    }

    public static boolean doCheckInstalled(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || context == null) {
            return false;
        }
        return str.equalsIgnoreCase(getInstalledAppHash(context, str2));
    }

    public static boolean doCheckInstalledV2V3(Context context, List<String> list, String str) {
        List<String> installedAppHashV2V3;
        if (TextUtils.isEmpty(str) || list == null || context == null || (installedAppHashV2V3 = getInstalledAppHashV2V3(context, str)) == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : list) {
            arrayList.add(str2.toUpperCase(Locale.ENGLISH));
        }
        for (String str3 : installedAppHashV2V3) {
            if (!arrayList.contains(str3)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] getInstalledAPPSignature(Context context, String str) {
        PackageInfo packageInfo;
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null) {
                    return packageInfo.signatures[0].toByteArray();
                }
            } catch (PackageManager.NameNotFoundException e) {
                LogsUtil.m13814e("HiPkgSignManager", "PackageManager.NameNotFoundException : " + e.getMessage(), true);
            } catch (Exception e2) {
                LogsUtil.m13814e("HiPkgSignManager", "Exception : " + e2.getMessage(), true);
            }
            return new byte[0];
        }
        LogsUtil.m13819e("HiPkgSignManager", "packageName is null or context is null");
        return new byte[0];
    }

    public static String getInstalledAppHash(Context context, String str) {
        byte[] installedAPPSignature = getInstalledAPPSignature(context, str);
        return (installedAPPSignature == null || installedAPPSignature.length <= 0) ? "" : m13935b(installedAPPSignature);
    }

    public static List<String> getInstalledAppHashV2V3(Context context, String str) {
        PackageManager packageManager;
        try {
            packageManager = context.getPackageManager();
        } catch (Throwable unused) {
        }
        if (Build.VERSION.SDK_INT >= 28) {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 134217728);
            if (packageInfo != null && packageInfo.signingInfo != null) {
                if (packageInfo.signingInfo.hasMultipleSigners()) {
                    return m13936a(packageInfo.signingInfo.getApkContentsSigners());
                }
                return m13936a(packageInfo.signingInfo.getSigningCertificateHistory());
            }
            return null;
        }
        PackageInfo packageInfo2 = packageManager.getPackageInfo(str, 64);
        if (packageInfo2 != null && packageInfo2.signatures != null && packageInfo2.signatures.length != 0 && packageInfo2.signatures[0] != null) {
            return m13936a(packageInfo2.signatures);
        }
        return null;
    }

    public static String getUnInstalledAPPPackageName(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            PackageInfo m13938a = m13938a(context, str);
            return m13938a != null ? m13938a.packageName : "";
        }
        LogsUtil.m13819e("HiPkgSignManager", "archiveFilePath is null or context is null");
        return "";
    }

    public static byte[] getUnInstalledAPPSignature(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            PackageInfo m13938a = m13938a(context, str);
            if (m13938a != null) {
                Signature signature = m13938a.signatures[0];
                if (signature != null) {
                    return signature.toByteArray();
                }
            } else {
                LogsUtil.m13819e("HiPkgSignManager", "PackageInfo is null ");
            }
            return new byte[0];
        }
        LogsUtil.m13819e("HiPkgSignManager", "archiveFilePath is null or context is null");
        return new byte[0];
    }

    public static String getUnInstalledAppHash(Context context, String str) {
        byte[] unInstalledAPPSignature = getUnInstalledAPPSignature(context, str);
        return (unInstalledAPPSignature == null || unInstalledAPPSignature.length <= 0) ? "" : m13935b(unInstalledAPPSignature);
    }

    /* renamed from: a */
    private static String m13937a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if ((bArr[i] & 255) < 16) {
                stringBuffer.append("0" + Integer.toHexString(bArr[i] & 255));
            } else {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
            }
        }
        return stringBuffer.toString().toUpperCase(Locale.ENGLISH);
    }

    /* renamed from: a */
    private static List<String> m13936a(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        if (signatureArr != null && signatureArr.length != 0) {
            for (Signature signature : signatureArr) {
                arrayList.add(m13935b(signature.toByteArray()));
            }
        }
        return arrayList;
    }
}
