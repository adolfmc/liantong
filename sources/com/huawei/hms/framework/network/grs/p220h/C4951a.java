package com.huawei.hms.framework.network.grs.p220h;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4951a {
    /* renamed from: a */
    public static String m14865a() {
        return "6.0.2.300";
    }

    /* renamed from: a */
    public static String m14864a(Context context) {
        if (context == null) {
            return "";
        }
        if (ContextHolder.getAppContext() != null) {
            context = ContextHolder.getAppContext();
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.m15044w("AgentUtil", "", e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m14863a(Context context, String str, String str2) {
        if (context == null) {
            return String.format(Locale.ROOT, str + "/%s", m14865a());
        }
        String packageName = (ContextHolder.getAppContext() == null ? context : ContextHolder.getAppContext()).getPackageName();
        String m14864a = m14864a(context);
        String str3 = Build.VERSION.RELEASE;
        String str4 = Build.MODEL;
        Locale locale = Locale.ROOT;
        String str5 = "%s/%s (Linux; Android %s; %s) " + str + "/%s %s";
        Object[] objArr = new Object[6];
        objArr[0] = packageName;
        objArr[1] = m14864a;
        objArr[2] = str3;
        objArr[3] = str4;
        objArr[4] = m14865a();
        if (TextUtils.isEmpty(str2)) {
            str2 = "no_service_name";
        }
        objArr[5] = str2;
        return String.format(locale, str5, objArr);
    }

    /* renamed from: b */
    public static String m14862b(Context context, String str, String str2) {
        return m14863a(context, str, str2);
    }
}
