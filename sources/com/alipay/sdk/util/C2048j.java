package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.encrypt.C2016f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2048j {

    /* renamed from: a */
    private static String f3894a;

    /* renamed from: a */
    public static boolean m20687a(Context context, String str) {
        try {
            return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
            C2040c.m20715a(th);
            return false;
        }
    }

    /* renamed from: b */
    public static void m20685b(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }

    /* renamed from: a */
    public static void m20686a(Context context, String str, String str2) {
        try {
            String m20830a = C2016f.m20830a(m20688a(context), str2, str);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(m20830a)) {
                C2000a.m20899a("cp", "TriDesDecryptError", String.format("%s,%s", str, str2));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, m20830a).commit();
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }

    /* renamed from: b */
    public static String m20684b(Context context, String str, String str2) {
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            r0 = TextUtils.isEmpty(string) ? null : C2016f.m20828b(m20688a(context), string, str);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(r0)) {
                C2000a.m20899a("cp", "TriDesEncryptError", String.format("%s,%s", str, string));
            }
        } catch (Exception e) {
            C2040c.m20715a(e);
        }
        return r0;
    }

    /* renamed from: a */
    private static String m20688a(Context context) {
        if (TextUtils.isEmpty(f3894a)) {
            String str = "";
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                C2040c.m20715a(th);
            }
            f3894a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return f3894a;
    }
}
