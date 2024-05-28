package com.huawei.hms.hatool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"ApplySharedPref"})
/* renamed from: com.huawei.hms.hatool.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4975d {
    /* renamed from: a */
    public static long m14768a(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C5029v.m14451f("hmsSdk", "context is null or spName empty or spkey is empty");
            return j;
        }
        SharedPreferences m14765b = m14765b(context, str);
        return m14765b != null ? m14765b.getLong(str2, j) : j;
    }

    /* renamed from: a */
    public static String m14767a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C5029v.m14451f("hmsSdk", "context is null or spName empty or spkey is empty");
            return str3;
        }
        SharedPreferences m14765b = m14765b(context, str);
        return m14765b != null ? m14765b.getString(str2, str3) : str3;
    }

    /* renamed from: a */
    public static Map<String, ?> m14769a(Context context, String str) {
        return m14765b(context, str).getAll();
    }

    /* renamed from: a */
    public static void m14766a(Context context, String str, String... strArr) {
        String str2;
        String str3;
        if (context == null || TextUtils.isEmpty(str)) {
            str2 = "hmsSdk";
            str3 = "clearData(): parameter error.context,spname";
        } else if (strArr != null) {
            SharedPreferences m14765b = m14765b(context, str);
            if (m14765b != null) {
                SharedPreferences.Editor edit = m14765b.edit();
                if (strArr.length == 0) {
                    edit.clear();
                    edit.commit();
                    return;
                }
                for (String str4 : strArr) {
                    if (m14765b.contains(str4)) {
                        edit.remove(str4);
                        edit.commit();
                    }
                }
                return;
            }
            return;
        } else {
            str2 = "hmsSdk";
            str3 = "clearData(): No data need to be deleted,keys is null";
        }
        C5029v.m14451f(str2, str3);
    }

    /* renamed from: b */
    private static SharedPreferences m14765b(Context context, String str) {
        return context.getSharedPreferences(m14762c(context, str), 0);
    }

    /* renamed from: b */
    public static void m14764b(Context context, String str, String str2, long j) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C5029v.m14451f("hmsSdk", "context is null or spName empty or spkey is empty");
            return;
        }
        SharedPreferences m14765b = m14765b(context, str);
        if (m14765b != null) {
            SharedPreferences.Editor edit = m14765b.edit();
            edit.putLong(str2, j);
            edit.commit();
        }
    }

    /* renamed from: b */
    public static void m14763b(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            C5029v.m14452e("hmsSdk", "context is null or spName empty or spkey is empty");
            return;
        }
        SharedPreferences m14765b = m14765b(context, str);
        if (m14765b != null) {
            SharedPreferences.Editor edit = m14765b.edit();
            edit.putString(str2, str3);
            edit.commit();
        }
    }

    /* renamed from: c */
    public static String m14762c(Context context, String str) {
        String packageName = context.getPackageName();
        String m14804n = AbstractC4966a1.m14804n("_hms_config_tag", "oper");
        if (TextUtils.isEmpty(m14804n)) {
            return "hms_" + str + "_" + packageName;
        }
        return "hms_" + str + "_" + packageName + "_" + m14804n;
    }
}
