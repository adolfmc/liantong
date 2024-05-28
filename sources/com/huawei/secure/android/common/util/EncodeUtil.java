package com.huawei.secure.android.common.util;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EncodeUtil {

    /* renamed from: a */
    private static final String f12127a = "EncodeUtil";

    /* renamed from: b */
    private static final char[] f12128b = {',', '.', '-', '_'};

    /* renamed from: c */
    private static final String[] f12129c = new String[256];

    static {
        for (char c = 0; c < 255; c = (char) (c + 1)) {
            if ((c >= '0' && c <= '9') || ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                f12129c[c] = null;
            } else {
                f12129c[c] = m13831b(c).intern();
            }
        }
    }

    /* renamed from: a */
    private static String m13832a(char[] cArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(m13833a(cArr, Character.valueOf(str.charAt(i))));
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m13831b(char c) {
        return Integer.toHexString(c);
    }

    public static String decodeForJavaScript(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            C5124a c5124a = new C5124a(str);
            while (c5124a.m13781a()) {
                Character m13834a = m13834a(c5124a);
                if (m13834a != null) {
                    sb.append(m13834a);
                } else {
                    sb.append(c5124a.m13774d());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            String str2 = f12127a;
            Log.e(str2, "decode js: " + e.getMessage());
            return "";
        }
    }

    public static String encodeForJavaScript(String str) {
        return encodeForJavaScript(str, f12128b);
    }

    public static String encodeForJavaScript(String str, char[] cArr) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return m13832a(cArr, str);
        } catch (Exception e) {
            String str2 = f12127a;
            Log.e(str2, "encode js: " + e.getMessage());
            return "";
        }
    }

    /* renamed from: a */
    private static String m13833a(char[] cArr, Character ch) {
        if (m13835a(ch.charValue(), cArr)) {
            return "" + ch;
        } else if (m13836a(ch.charValue()) == null) {
            return "" + ch;
        } else {
            String hexString = Integer.toHexString(ch.charValue());
            if (ch.charValue() < 256) {
                return "\\x" + "00".substring(hexString.length()) + hexString.toUpperCase(Locale.ENGLISH);
            }
            return "\\u" + "0000".substring(hexString.length()) + hexString.toUpperCase(Locale.ENGLISH);
        }
    }

    /* renamed from: a */
    private static boolean m13835a(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static String m13836a(char c) {
        if (c < 255) {
            return f12129c[c];
        }
        return m13831b(c);
    }

    /* renamed from: a */
    private static Character m13834a(C5124a c5124a) {
        c5124a.m13776c();
        Character m13774d = c5124a.m13774d();
        if (m13774d == null) {
            c5124a.m13769i();
            return null;
        } else if (m13774d.charValue() != '\\') {
            c5124a.m13769i();
            return null;
        } else {
            Character m13774d2 = c5124a.m13774d();
            if (m13774d2 == null) {
                c5124a.m13769i();
                return null;
            } else if (m13774d2.charValue() == 'b') {
                return '\b';
            } else {
                if (m13774d2.charValue() == 't') {
                    return '\t';
                }
                if (m13774d2.charValue() == 'n') {
                    return '\n';
                }
                if (m13774d2.charValue() == 'v') {
                    return (char) 11;
                }
                if (m13774d2.charValue() == 'f') {
                    return '\f';
                }
                if (m13774d2.charValue() == 'r') {
                    return '\r';
                }
                if (m13774d2.charValue() == '\"') {
                    return '\"';
                }
                if (m13774d2.charValue() == '\'') {
                    return '\'';
                }
                if (m13774d2.charValue() == '\\') {
                    return '\\';
                }
                int i = 0;
                if (Character.toLowerCase(m13774d2.charValue()) == 'x') {
                    StringBuilder sb = new StringBuilder();
                    while (i < 2) {
                        Character m13773e = c5124a.m13773e();
                        if (m13773e != null) {
                            sb.append(m13773e);
                            i++;
                        } else {
                            c5124a.m13769i();
                            return null;
                        }
                    }
                    try {
                        int parseInt = Integer.parseInt(sb.toString(), 16);
                        if (Character.isValidCodePoint(parseInt)) {
                            return Character.valueOf((char) parseInt);
                        }
                    } catch (NumberFormatException unused) {
                        c5124a.m13769i();
                        return null;
                    }
                } else if (Character.toLowerCase(m13774d2.charValue()) == 'u') {
                    StringBuilder sb2 = new StringBuilder();
                    while (i < 4) {
                        Character m13773e2 = c5124a.m13773e();
                        if (m13773e2 != null) {
                            sb2.append(m13773e2);
                            i++;
                        } else {
                            c5124a.m13769i();
                            return null;
                        }
                    }
                    try {
                        int parseInt2 = Integer.parseInt(sb2.toString(), 16);
                        if (Character.isValidCodePoint(parseInt2)) {
                            return Character.valueOf((char) parseInt2);
                        }
                    } catch (NumberFormatException unused2) {
                        c5124a.m13769i();
                        return null;
                    }
                } else if (C5124a.m13775c(m13774d2)) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(m13774d2);
                    Character m13774d3 = c5124a.m13774d();
                    if (!C5124a.m13775c(m13774d3)) {
                        c5124a.m13779a(m13774d3);
                    } else {
                        sb3.append(m13774d3);
                        Character m13774d4 = c5124a.m13774d();
                        if (!C5124a.m13775c(m13774d4)) {
                            c5124a.m13779a(m13774d4);
                        } else {
                            sb3.append(m13774d4);
                        }
                    }
                    try {
                        int parseInt3 = Integer.parseInt(sb3.toString(), 8);
                        if (Character.isValidCodePoint(parseInt3)) {
                            return Character.valueOf((char) parseInt3);
                        }
                    } catch (NumberFormatException unused3) {
                        c5124a.m13769i();
                        return null;
                    }
                }
                return m13774d2;
            }
        }
    }
}
