package cn.sharesdk.framework.utils;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.Looper;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AppUtils {
    /* renamed from: a */
    public static boolean m21718a(String str, int i) {
        return (str == null || str.isEmpty() || m21715b(str, i) == null) ? false : true;
    }

    /* renamed from: a */
    public static boolean m21720a() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /* renamed from: a */
    public static ResolveInfo m21719a(Intent intent, int i) {
        try {
            try {
                return DeviceHelper.getInstance(MobSDK.getContext()).resolveActivity(intent, i);
            } catch (Throwable unused) {
                return MobSDK.getContext().getPackageManager().resolveActivity(intent, i);
            }
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21733d("getResolveActivity error" + th, new Object[0]);
            return null;
        }
    }

    /* renamed from: b */
    public static PackageInfo m21715b(String str, int i) {
        try {
            try {
                return DeviceHelper.getInstance(MobSDK.getContext()).getPInfo(str, i);
            } catch (Throwable unused) {
                return MobSDK.getContext().getPackageManager().getPackageInfo(str, i);
            }
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21743a(str + ":" + th);
            return null;
        }
    }

    /* renamed from: c */
    public static ApplicationInfo m21713c(String str, int i) {
        try {
            try {
                return DeviceHelper.getInstance(MobSDK.getContext()).getAInfo(str, i);
            } catch (Throwable unused) {
                return MobSDK.getContext().getPackageManager().getApplicationInfo(str, i);
            }
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21743a(str + ":" + th);
            return null;
        }
    }

    /* renamed from: b */
    public static ApplicationInfo m21716b() {
        try {
            return DeviceHelper.getInstance(MobSDK.getContext()).getAInfo();
        } catch (Throwable th) {
            try {
                return MobSDK.getContext().getApplicationInfo();
            } catch (Throwable unused) {
                SSDKLog.m21740b().m21737b(th);
                return null;
            }
        }
    }

    /* renamed from: c */
    public static String m21714c() {
        DeviceHelper deviceHelper = DeviceHelper.getInstance(MobSDK.getContext());
        try {
            try {
                return deviceHelper.getDeviceData();
            } catch (Throwable th) {
                SSDKLog.m21740b().m21732d(th);
                return "";
            }
        } catch (Throwable unused) {
            String str = deviceHelper.getModel() + "|" + deviceHelper.getOSVersionInt() + "|" + deviceHelper.getManufacturer() + "|" + deviceHelper.getCarrier() + "|" + deviceHelper.getScreenSize();
            String deviceKey = deviceHelper.getDeviceKey();
            if (deviceKey == null) {
                deviceKey = "";
            } else if (deviceKey.length() > 16) {
                deviceKey = deviceKey.substring(0, 16);
            }
            return m21717a(str, deviceKey);
        }
    }

    /* renamed from: d */
    public static String m21712d() {
        DeviceHelper deviceHelper = DeviceHelper.getInstance(MobSDK.getContext());
        try {
            try {
                return deviceHelper.getDeviceDataNotAES();
            } catch (Throwable th) {
                SSDKLog.m21740b().m21732d(th);
                return "";
            }
        } catch (Throwable unused) {
            return deviceHelper.getModel() + "|" + deviceHelper.getOSVersionInt() + "|" + deviceHelper.getManufacturer() + "|" + deviceHelper.getCarrier() + "|" + deviceHelper.getScreenSize();
        }
    }

    /* renamed from: a */
    private static String m21717a(String str, String str2) {
        try {
            String encodeToString = Base64.encodeToString(Data.AES128Encode(str2, str), 0);
            return encodeToString.contains("\n") ? encodeToString.replace("\n", "") : encodeToString;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }
}
