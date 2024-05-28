package com.huawei.secure.android.common.anonymization;

import android.text.TextUtils;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Anonymizer {

    /* renamed from: a */
    private static final String f11867a = "Anonymizer";

    public static String maskAccountId(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 1) {
            return "*";
        }
        if (str.length() < 8) {
            return maskCommonString(str, 0, 1);
        }
        return maskCommonString(str, 0, 4);
    }

    public static String maskBankAccount(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 2) {
            return C5099a.m14026a(str, '*');
        }
        if (str.length() >= 11 && str.length() < 20) {
            return maskCommonString(str, 4, 4);
        }
        return maskCommonString(str, 6, 4);
    }

    public static String maskBirthday(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 4) {
            return C5099a.m14026a(str, '*');
        }
        String[] m14024a = C5099a.m14024a(str, 4);
        return C5099a.m14019a("", C5099a.m14018a(m14024a, 0), C5099a.m14021a(C5099a.m14018a(m14024a, 1), "0123456789", "*"));
    }

    public static String maskCommonString(String str, int i, int i2) {
        int length;
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (str.length() <= i + i2) {
            length = str.length() - 1;
            i = 1;
        } else {
            length = str.length() - i2;
        }
        String[] m14023a = C5099a.m14023a(str, i, length);
        return C5099a.m14019a("", C5099a.m14018a(m14023a, 0), C5099a.m14026a(C5099a.m14018a(m14023a, 1), '*'), C5099a.m14018a(m14023a, 2));
    }

    public static String maskEmail(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 1) {
            return "*";
        }
        int indexOf = str.indexOf(64);
        if (indexOf < 0) {
            String[] m14024a = C5099a.m14024a(str, 1);
            return C5099a.m14019a("", C5099a.m14018a(m14024a, 0), C5099a.m14026a(C5099a.m14018a(m14024a, 1), '*'));
        }
        String[] m14022a = C5099a.m14022a(str, indexOf, indexOf + 1, str.lastIndexOf(46));
        return C5099a.m14019a("", C5099a.m14026a(C5099a.m14018a(m14022a, 0), '*'), C5099a.m14018a(m14022a, 1), C5099a.m14026a(C5099a.m14018a(m14022a, 2), '*'), C5099a.m14018a(m14022a, 3));
    }

    public static String maskId(String str) {
        return (str == null || str.length() <= 0) ? "" : maskCommonString(str, str.length() - 12, 0);
    }

    public static String maskImeiImsi(String str) {
        return (str == null || str.length() <= 0) ? "" : maskCommonString(str, str.length() - 4, 0);
    }

    public static String maskIpV4(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return C5099a.m14026a(str, '*');
        }
        String[] m14024a = C5099a.m14024a(str, lastIndexOf + 1);
        return C5099a.m14019a("", C5099a.m14018a(m14024a, 0), C5099a.m14026a(C5099a.m14018a(m14024a, 1), '*'));
    }

    public static String maskIpV6(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        String[] m14015b = C5099a.m14015b(str, ':');
        if (m14015b.length <= 1) {
            return C5099a.m14026a(str, '*');
        }
        if (m14015b.length != 8) {
            String[] m14024a = C5099a.m14024a(str, str.indexOf(58) + 1);
            return C5099a.m14019a("", C5099a.m14018a(m14024a, 0), C5099a.m14021a(C5099a.m14018a(m14024a, 1), "0123456789ABCDEFabcdef", "*"));
        }
        m14015b[2] = maskLower8Bit(m14015b[2]);
        for (int i = 3; i < m14015b.length; i++) {
            m14015b[i] = C5099a.m14026a(m14015b[i], '*');
        }
        return C5099a.m14019a(":", m14015b);
    }

    public static String maskLower8Bit(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(f11867a, "maskLower8Bit: s is null");
            return "";
        } else if (str.length() <= 2) {
            return C5099a.m14026a(str, '*');
        } else {
            String[] m14024a = C5099a.m14024a(str, str.length() - 2);
            return C5099a.m14019a("", C5099a.m14018a(m14024a, 0), C5099a.m14026a(C5099a.m14018a(m14024a, 1), '*'));
        }
    }

    public static String maskMac(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        int m14025a = C5099a.m14025a(str, '-', 2);
        if (m14025a < 0) {
            return C5099a.m14021a(str, "0123456789ABCDEFabcdef", "*");
        }
        String[] m14024a = C5099a.m14024a(str, m14025a);
        return C5099a.m14019a("", C5099a.m14018a(m14024a, 0), C5099a.m14021a(C5099a.m14018a(m14024a, 1), "0123456789ABCDEFabcdef", "*"));
    }

    public static String maskName(String str) {
        return (str == null || str.length() <= 0) ? "" : str.length() <= 1 ? "*" : maskCommonString(str, 1, 0);
    }

    public static String maskPhone(String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        if (str.length() <= 2) {
            return C5099a.m14026a(str, '*');
        }
        if (str.length() >= 8 && str.length() < 11) {
            return maskCommonString(str, 2, 2);
        }
        return maskCommonString(str, 3, 4);
    }
}
