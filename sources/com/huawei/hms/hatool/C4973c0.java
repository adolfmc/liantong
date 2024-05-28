package com.huawei.hms.hatool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.c0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4973c0 {
    /* renamed from: a */
    public static boolean m14780a(Context context) {
        return System.currentTimeMillis() - C4975d.m14768a(context, "Privacy_MY", "flashKeyTime", -1L) > 43200000;
    }

    /* renamed from: a */
    public static boolean m14779a(Context context, String str) {
        if (context == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return false;
            }
        } else if (context.checkSelfPermission(str) == 0) {
            return false;
        }
        C5029v.m14451f("hmsSdk", "not have read phone permission!");
        return true;
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: a */
    public static boolean m14778a(Context context, String str, int i) {
        String str2 = C4975d.m14762c(context, str) + ".xml";
        long length = new File(context.getFilesDir(), "../shared_prefs/" + str2).length();
        if (length > i) {
            C5029v.m14455c("hmsSdk", String.format("reach local file limited size - file len: %d limitedSize: %d", Long.valueOf(length), Integer.valueOf(i)));
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m14777a(String str, long j, long j2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            return j - Long.parseLong(str) > j2;
        } catch (NumberFormatException unused) {
            C5029v.m14451f("hmsSdk", "isTimeExpired(): Data type conversion error : number format !");
            return true;
        }
    }
}
