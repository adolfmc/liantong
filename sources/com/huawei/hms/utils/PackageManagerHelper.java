package com.huawei.hms.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.hms.support.log.HMSLog;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PackageManagerHelper {

    /* renamed from: a */
    private final PackageManager f11842a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum PackageStates {
        ENABLED,
        DISABLED,
        NOT_INSTALLED,
        SPOOF
    }

    public PackageManagerHelper(Context context) {
        this.f11842a = context.getPackageManager();
    }

    /* renamed from: a */
    private byte[] m14046a(String str) {
        Signature[] signatureArr;
        try {
            PackageInfo packageInfo = this.f11842a.getPackageInfo(str, 64);
            if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length > 0) {
                return signatureArr[0].toByteArray();
            }
        } catch (AndroidException e) {
            HMSLog.m14112e("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e.getMessage());
        } catch (RuntimeException e2) {
            HMSLog.m14111e("PackageManagerHelper", "Failed to get application signature certificate fingerprint.", e2);
        }
        HMSLog.m14112e("PackageManagerHelper", "Failed to get application signature certificate fingerprint.");
        return new byte[0];
    }

    public String getApplicationName(String str) {
        try {
            return this.f11842a.getApplicationLabel(this.f11842a.getApplicationInfo(str, 128)).toString();
        } catch (AndroidException | RuntimeException unused) {
            HMSLog.m14112e("PackageManagerHelper", "Failed to get application name for " + str);
            return null;
        }
    }

    public long getPackageFirstInstallTime(String str) {
        try {
            PackageInfo packageInfo = this.f11842a.getPackageInfo(str, 128);
            if (packageInfo != null) {
                return packageInfo.firstInstallTime;
            }
            return 0L;
        } catch (AndroidException | RuntimeException unused) {
            return 0L;
        }
    }

    public String getPackageSignature(String str) {
        byte[] m14046a = m14046a(str);
        if (m14046a == null || m14046a.length == 0) {
            return null;
        }
        return HEX.encodeHexString(SHA256.digest(m14046a), true);
    }

    public PackageStates getPackageStates(String str) {
        if (TextUtils.isEmpty(str)) {
            return PackageStates.NOT_INSTALLED;
        }
        try {
            if (this.f11842a.getApplicationInfo(str, 128).enabled) {
                return PackageStates.ENABLED;
            }
            return PackageStates.DISABLED;
        } catch (AndroidException | RuntimeException unused) {
            HMSLog.m14112e("PackageManagerHelper", "in getPackageStates, getApplicationInfo threw an exception");
            return PackageStates.NOT_INSTALLED;
        }
    }

    public int getPackageVersionCode(String str) {
        try {
            PackageInfo packageInfo = this.f11842a.getPackageInfo(str, 16);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (AndroidException e) {
            HMSLog.m14112e("PackageManagerHelper", "get PackageVersionCode failed " + e);
            return 0;
        } catch (RuntimeException e2) {
            HMSLog.m14111e("PackageManagerHelper", "get PackageVersionCode failed", e2);
            return 0;
        }
    }

    public String getPackageVersionName(String str) {
        try {
            PackageInfo packageInfo = this.f11842a.getPackageInfo(str, 16);
            if (packageInfo != null) {
                String str2 = packageInfo.versionName;
                return str2 != null ? str2 : "";
            }
            return "";
        } catch (AndroidException unused) {
            return "";
        } catch (RuntimeException e) {
            HMSLog.m14111e("PackageManagerHelper", "get getPackageVersionName failed", e);
            return "";
        }
    }

    public boolean hasProvider(String str, String str2) {
        ProviderInfo[] providerInfoArr;
        try {
            PackageInfo packageInfo = this.f11842a.getPackageInfo(str, 8);
            if (packageInfo != null && (providerInfoArr = packageInfo.providers) != null) {
                for (ProviderInfo providerInfo : providerInfoArr) {
                    if (str2.equals(providerInfo.authority)) {
                        return true;
                    }
                }
            }
        } catch (AndroidException | RuntimeException unused) {
        }
        return false;
    }

    public boolean isPackageFreshInstall(String str) {
        try {
            PackageInfo packageInfo = this.f11842a.getPackageInfo(str, 128);
            if (packageInfo != null) {
                return packageInfo.firstInstallTime == packageInfo.lastUpdateTime;
            }
            return false;
        } catch (AndroidException | RuntimeException unused) {
            return false;
        }
    }

    public boolean verifyPackageArchive(String str, String str2, String str3) {
        PackageInfo packageInfo;
        InputStream inputStream = null;
        try {
            packageInfo = this.f11842a.getPackageArchiveInfo(str, 64);
        } catch (Exception e) {
            HMSLog.m14112e("PackageManagerHelper", "getPackageArchiveInfo Exception. " + e.getMessage());
            packageInfo = null;
        }
        if (packageInfo != null && packageInfo.signatures.length > 0) {
            try {
                if (!str2.equals(packageInfo.packageName)) {
                    return false;
                }
                try {
                    inputStream = IOUtils.toInputStream(packageInfo.signatures[0].toByteArray());
                    return str3.equalsIgnoreCase(HEX.encodeHexString(SHA256.digest(CertificateFactory.getInstance("X.509").generateCertificate(inputStream).getEncoded()), true));
                } catch (IOException | CertificateException e2) {
                    HMSLog.m14112e("PackageManagerHelper", "Failed to get application signature certificate fingerprint." + e2.getMessage());
                }
            } finally {
                IOUtils.closeQuietly((InputStream) null);
            }
        }
        return false;
    }
}
