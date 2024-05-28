package com.gmrz.android.client.utils;

import android.util.Log;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Logger {

    /* renamed from: a */
    private static boolean f10181a = true;

    /* renamed from: b */
    private static final Map<String, Integer> f10182b = new TreeMap();

    public static boolean setLogEnabled(boolean z) {
        boolean z2 = f10181a;
        f10181a = z;
        return z2;
    }

    public static void startTimer(String str, String str2) {
        if (f10181a) {
            String str3 = str + str2;
            if (!f10182b.containsKey(str3)) {
                f10182b.put(str3, Integer.valueOf((int) System.currentTimeMillis()));
                Log.i(str, "\"" + str2 + "\" timer started.");
                return;
            }
            Log.w(str, "\"" + str2 + "\" timer is already running.");
        }
    }

    public static void endTimer(String str, String str2) {
        if (f10181a) {
            String str3 = str + str2;
            if (f10182b.containsKey(str3)) {
                int currentTimeMillis = ((int) System.currentTimeMillis()) - f10182b.get(str3).intValue();
                f10182b.remove(str3);
                Log.i(str, "End of \"" + str2 + "\" timer. Run in " + String.valueOf(currentTimeMillis) + " millisecond(s)");
                return;
            }
            Log.w(str, "There is no \"" + str2 + "\" timer running.");
        }
    }

    /* renamed from: i */
    public static int m15889i(String str, String str2) {
        if (f10181a) {
            int length = str2.length();
            if (length <= 4000) {
                Log.i(str, str2);
                return 4;
            }
            int i = 0;
            int i2 = 0;
            do {
                i += length > 4000 ? 4000 : length;
                length -= i - i2;
                Log.i(str, str2.substring(i2, i));
                i2 += 4000;
            } while (length > 0);
            return 4;
        }
        return 8;
    }

    /* renamed from: i */
    public static int m15888i(String str, String str2, Throwable th) {
        return m15889i(str, str2 + ": " + th.toString());
    }

    /* renamed from: i */
    public static int m15887i(String str, String str2, byte[] bArr) {
        if (f10181a) {
            return m15889i(str, m15896a(str2, bArr));
        }
        return 8;
    }

    /* renamed from: v */
    public static int m15886v(String str, String str2) {
        if (f10181a) {
            int length = str2.length();
            if (length <= 4000) {
                Log.v(str, str2);
                return 2;
            }
            int i = 0;
            int i2 = 0;
            do {
                i += length > 4000 ? 4000 : length;
                length -= i - i2;
                Log.v(str, str2.substring(i2, i));
                i2 += 4000;
            } while (length > 0);
            return 2;
        }
        return 8;
    }

    /* renamed from: v */
    public static int m15885v(String str, String str2, Throwable th) {
        return m15886v(str, str2 + ": " + th.toString());
    }

    /* renamed from: v */
    public static int m15884v(String str, String str2, byte[] bArr) {
        if (f10181a) {
            return m15886v(str, m15896a(str2, bArr));
        }
        return 8;
    }

    /* renamed from: w */
    public static int m15883w(String str, String str2) {
        if (f10181a) {
            int length = str2.length();
            if (length <= 4000) {
                Log.w(str, str2);
                return 5;
            }
            int i = 0;
            int i2 = 0;
            do {
                i += length > 4000 ? 4000 : length;
                length -= i - i2;
                Log.w(str, str2.substring(i2, i));
                i2 += 4000;
            } while (length > 0);
            return 5;
        }
        return 8;
    }

    /* renamed from: w */
    public static int m15882w(String str, String str2, Throwable th) {
        return m15883w(str, str2 + ": " + th.toString());
    }

    /* renamed from: w */
    public static int m15881w(String str, String str2, byte[] bArr) {
        if (f10181a) {
            return m15883w(str, m15896a(str2, bArr));
        }
        return 8;
    }

    /* renamed from: d */
    public static int m15895d(String str, String str2) {
        if (f10181a) {
            int length = str2.length();
            if (length <= 4000) {
                Log.d(str, str2);
                return 3;
            }
            int i = 0;
            int i2 = 0;
            do {
                i += length > 4000 ? 4000 : length;
                length -= i - i2;
                Log.d(str, str2.substring(i2, i));
                i2 += 4000;
            } while (length > 0);
            return 3;
        }
        return 8;
    }

    /* renamed from: d */
    public static int m15894d(String str, String str2, Throwable th) {
        return m15895d(str, str2 + ": " + th.toString());
    }

    /* renamed from: d */
    public static int m15893d(String str, String str2, byte[] bArr) {
        if (f10181a) {
            return Log.d(str, m15896a(str2, bArr));
        }
        return 8;
    }

    /* renamed from: e */
    public static int m15892e(String str, String str2) {
        if (f10181a) {
            int length = str2.length();
            if (length <= 4000) {
                Log.e(str, str2);
                return 6;
            }
            int i = 0;
            int i2 = 0;
            do {
                i += length > 4000 ? 4000 : length;
                length -= i - i2;
                Log.e(str, str2.substring(i2, i));
                i2 += 4000;
            } while (length > 0);
            return 6;
        }
        return 8;
    }

    /* renamed from: e */
    public static int m15891e(String str, String str2, Throwable th) {
        if (f10181a) {
            return Log.e(str, str2, th);
        }
        return 8;
    }

    /* renamed from: e */
    public static int m15890e(String str, String str2, byte[] bArr) {
        if (f10181a) {
            return m15892e(str, m15896a(str2, bArr));
        }
        return 8;
    }

    /* renamed from: a */
    private static String m15896a(String str, byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer("");
        if (str != null) {
            stringBuffer.append(str);
        }
        if (bArr == null) {
            stringBuffer.append(":null");
            return stringBuffer.toString();
        }
        int length = bArr.length;
        stringBuffer.append("(" + length + "):\n");
        for (int i = 0; i < length; i += 16) {
            stringBuffer.append(String.format("%06x:", Integer.valueOf(i)));
            for (int i2 = 0; i2 < 16; i2++) {
                int i3 = i + i2;
                if (i3 < length) {
                    stringBuffer.append(String.format("%02x ", Integer.valueOf(bArr[i3] & 255)));
                } else {
                    stringBuffer.append("   ");
                }
            }
            stringBuffer.append(" ");
            for (int i4 = 0; i4 < 16; i4++) {
                int i5 = i + i4;
                if (i5 < length) {
                    char c = (char) (bArr[i5] & 255);
                    Object[] objArr = new Object[1];
                    objArr[0] = Character.valueOf((c < ' ' || c > '~') ? '.' : '.');
                    stringBuffer.append(String.format("%c", objArr));
                }
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }
}
