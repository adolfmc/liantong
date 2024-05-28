package com.unionpay.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UPUtils {
    /* renamed from: a */
    public static String m5870a(Context context, String str) {
        if (context != null) {
            String string = context.getSharedPreferences("UnionPayPluginEx.pref", 0).getString(str, "");
            String m5865b = m5865b(string, ("0000000023456789abcdef12123456786789abcd").substring(0, 32));
            return (m5865b != null && m5865b.endsWith("00000000")) ? m5865b.substring(0, m5865b.length() - 8) : "";
        }
        return null;
    }

    /* renamed from: a */
    public static String m5868a(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(bytes);
            return C10914a.m5863a(messageDigest.digest());
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m5867a(String str, String str2) {
        try {
            return C10914a.m5863a(C10917d.m5842a(C10914a.m5864a(str2), str.getBytes("utf-8")));
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static void m5869a(Context context, String str, String str2) {
        if (context != null) {
            String substring = ("0000000023456789abcdef12123456786789abcd").substring(0, 32);
            String m5867a = m5867a(str + "00000000", substring);
            if (m5867a == null) {
                m5867a = "";
            }
            SharedPreferences.Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
            edit.putString(str2, m5867a);
            edit.commit();
        }
    }

    /* renamed from: b */
    private static String m5865b(String str, String str2) {
        try {
            return new String(C10917d.m5841b(C10914a.m5864a(str2), C10914a.m5864a(str)), "utf-8").trim();
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: b */
    public static void m5866b(Context context, String str) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
            edit.remove(str);
            edit.commit();
        }
    }

    public static native String forConfig(int i, String str);

    public static native String forScanUrl(int i);

    public static native String forUrl(int i);

    public static native String forWap(int i, String str);

    public static native String getIssuer(int i);

    public static native String getSubject(int i);

    public static native String getTalkingDataIdForAssist(int i);
}
