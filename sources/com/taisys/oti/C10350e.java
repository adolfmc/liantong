package com.taisys.oti;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.taisys.oti.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10350e {

    /* renamed from: a */
    public static final String f19908a = "4F5441332E30";

    /* renamed from: f */
    public static String[] f19913f = null;

    /* renamed from: g */
    public static final int f19914g = 0;

    /* renamed from: h */
    public static final int f19915h = 1;

    /* renamed from: i */
    public static final int f19916i = 2;

    /* renamed from: m */
    public static final int f19920m = 20;

    /* renamed from: b */
    public static byte[] f19909b = {2, -127};

    /* renamed from: c */
    public static byte[] f19910c = {2, -127, 1};

    /* renamed from: d */
    public static byte[] f19911d = new byte[3];

    /* renamed from: e */
    public static byte[] f19912e = {0, 0, 16};

    /* renamed from: j */
    public static boolean f19917j = false;

    /* renamed from: k */
    public static String f19918k = null;

    /* renamed from: l */
    public static String f19919l = null;

    /* renamed from: b */
    public static byte m6265b(byte b) {
        int i = b & 255;
        return (byte) (((i & 240) >> 4) | ((i & 15) << 4));
    }

    /* renamed from: d */
    public static int m6258d(byte b) {
        return (b & 15) + (((b & 240) >> 4) * 10);
    }

    /* renamed from: a */
    public static boolean m6267a(byte[] bArr) {
        if (m6262b(bArr).startsWith("4F5441332E30")) {
            f19918k = m6262b(bArr).substring(12, 31);
            f19919l = m6262b(bArr).substring(32);
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static String m6274a(int i) {
        String num = Integer.toString(i, 16);
        if (num.length() < 2) {
            return "0" + num;
        }
        return num;
    }

    /* renamed from: a */
    public static String m6268a(short s) {
        String num = Integer.toString(s, 16);
        while (num.length() < 4) {
            num = "0" + num;
        }
        return num;
    }

    /* renamed from: a */
    public static String m6275a(byte b) {
        String num = Integer.toString(b, 16);
        if (num.length() < 2) {
            return "0" + num;
        }
        return num;
    }

    /* renamed from: b */
    public static String m6262b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return new String(cArr2);
    }

    /* renamed from: a */
    public static byte[] m6270a(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    /* renamed from: c */
    public static byte[] m6259c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = m6265b(bArr[i]);
        }
        return bArr2;
    }

    /* renamed from: c */
    public static byte m6261c(byte b) {
        return (byte) ((b / 10) | ((b % 10) << 4));
    }

    /* renamed from: b */
    public static String m6263b(String str) {
        int indexOf = str.indexOf("FF");
        return indexOf > -1 ? str.substring(0, indexOf) : str;
    }

    /* renamed from: c */
    public static String m6260c(String str) {
        int indexOf = str.indexOf("F");
        return indexOf > -1 ? str.substring(0, indexOf) : str;
    }

    /* renamed from: a */
    public static String m6269a(String str, String str2) {
        int indexOf = str.indexOf(str2);
        return indexOf > -1 ? str.substring(0, indexOf) : str;
    }

    /* renamed from: d */
    public static String m6256d(byte[] bArr) {
        String m6262b = m6262b(bArr);
        if (bArr.length % 2 > 0) {
            m6262b = String.valueOf(m6262b) + "FF";
        }
        byte[] m6270a = m6270a(m6262b);
        char[] cArr = new char[m6270a.length / 2];
        for (int i = 0; i < m6270a.length / 2; i++) {
            int i2 = i * 2;
            cArr[i] = (char) (((m6270a[i2] & 255) << 8) + (m6270a[i2 + 1] & 255));
        }
        return String.valueOf(cArr);
    }

    /* renamed from: a */
    public static int m6273a(Context context) {
        boolean z;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        boolean z2 = telephonyManager.getSimState() == 5;
        try {
            try {
                z2 = m6272a(context, "getSimStateGemini", 0);
                z = m6272a(context, "getSimStateGemini", 1);
            } catch (Exception unused) {
                z2 = m6272a(context, "getSimState", 0);
                z = m6272a(context, "getSimState", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        String subscriberId = telephonyManager.getSubscriberId();
        String str = null;
        try {
            subscriberId = m6264b(context, "getSubscriberIdGemini", 0);
            str = m6264b(context, "getSubscriberIdGemini", 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        f19913f = new String[2];
        String[] strArr = f19913f;
        strArr[0] = subscriberId;
        strArr[1] = str;
        int i = z2 ? 1 : 0;
        if (z) {
            i |= 2;
        }
        m6257d("SIM Ready State=" + i);
        return i;
    }

    /* renamed from: a */
    private static boolean m6272a(Context context, String str, int i) throws Exception {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            if (invoke != null) {
                if (Integer.parseInt(invoke.toString()) == 5) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(str);
        }
    }

    /* renamed from: b */
    private static String m6264b(Context context, String str, int i) throws Exception {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            if (invoke != null) {
                return String.valueOf(invoke);
            }
            return null;
        } catch (Exception unused) {
            throw new Exception(str);
        }
    }

    /* renamed from: a */
    public static boolean m6276a() {
        try {
            for (Method method : Class.forName("android.telephony.TelephonyManager").getMethods()) {
                m6257d(method.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: a */
    public static void m6271a(Class<?> cls) {
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++) {
            m6257d(methods[i].getName());
            Class<?>[] parameterTypes = methods[i].getParameterTypes();
            for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                m6257d("==>" + parameterTypes[i2].getName());
            }
        }
    }

    /* renamed from: a */
    public static void m6266a(StackTraceElement[] stackTraceElementArr) {
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            Log.w("OTI", stackTraceElement.toString());
        }
    }

    /* renamed from: d */
    public static void m6257d(String str) {
        if (f19917j) {
            Log.i("OTI", str);
        }
    }
}
