package com.bytedance.applog;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.x2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3730x2 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.x2$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC3731a {
        UNKNOWN(-1),
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5),
        MOBILE_5G(6),
        WIFI_24GHZ(7),
        WIFI_5GHZ(8),
        MOBILE_3G_H(9),
        MOBILE_3G_HP(10);
        

        /* renamed from: a */
        public final int f8930a;

        EnumC3731a(int i) {
            this.f8930a = i;
        }
    }

    static {
        EnumC3731a enumC3731a = EnumC3731a.UNKNOWN;
    }

    /* renamed from: a */
    public static String m17033a(Context context) {
        EnumC3731a m17031b = m17031b(context);
        return m17031b == EnumC3731a.WIFI ? "wifi" : m17031b == EnumC3731a.WIFI_24GHZ ? "wifi24ghz" : m17031b == EnumC3731a.WIFI_5GHZ ? "wifi5ghz" : m17031b == EnumC3731a.MOBILE_2G ? "2g" : m17031b == EnumC3731a.MOBILE_3G ? "3g" : m17031b == EnumC3731a.MOBILE_3G_H ? "3gh" : m17031b == EnumC3731a.MOBILE_3G_HP ? "3ghp" : m17031b == EnumC3731a.MOBILE_4G ? "4g" : m17031b == EnumC3731a.MOBILE_5G ? "5g" : m17031b == EnumC3731a.MOBILE ? "mobile" : "";
    }

    /* renamed from: a */
    public static boolean m17032a(TelephonyManager telephonyManager) {
        Method[] declaredMethods;
        int i;
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getDeclaredMethod("getServiceState", new Class[0]).invoke(telephonyManager, new Object[0]);
            for (Method method : Class.forName(invoke.getClass().getName()).getDeclaredMethods()) {
                i = (method.getName().equals("getNrStatus") || method.getName().equals("getNrState")) ? 0 : i + 1;
                method.setAccessible(true);
                return ((Integer) method.invoke(invoke, new Object[0])).intValue() == 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: b */
    public static EnumC3731a m17031b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return EnumC3731a.WIFI;
                }
                if (type == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    switch (telephonyManager.getNetworkType()) {
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return EnumC3731a.MOBILE_3G;
                        case 4:
                        case 7:
                        case 11:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        default:
                            return EnumC3731a.MOBILE;
                        case 13:
                            return (Build.VERSION.SDK_INT < 28 || !m17032a(telephonyManager)) ? EnumC3731a.MOBILE_4G : EnumC3731a.MOBILE_5G;
                        case 20:
                            return EnumC3731a.MOBILE_5G;
                    }
                }
                return EnumC3731a.MOBILE;
            }
            return EnumC3731a.NONE;
        } catch (Throwable unused) {
            return EnumC3731a.MOBILE;
        }
    }

    /* renamed from: c */
    public static boolean m17030c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
