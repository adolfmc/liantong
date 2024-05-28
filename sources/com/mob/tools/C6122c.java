package com.mob.tools;

import android.content.pm.ApplicationInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5747b;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C6122c {
    /* renamed from: a */
    public static int m11361a(ApplicationInfo applicationInfo, String str) {
        if (applicationInfo == null || !m11359a("1001", str)) {
            return -1;
        }
        return applicationInfo.uid;
    }

    /* renamed from: b */
    public static String m11358b(ApplicationInfo applicationInfo, String str) {
        if (applicationInfo == null || !m11359a("1004", str)) {
            return null;
        }
        return applicationInfo.name;
    }

    /* renamed from: c */
    public static int m11356c(ApplicationInfo applicationInfo, String str) {
        if (applicationInfo == null || !m11359a("1005", str)) {
            return -1;
        }
        return applicationInfo.labelRes;
    }

    /* renamed from: d */
    public static CharSequence m11354d(ApplicationInfo applicationInfo, String str) {
        if (applicationInfo == null || !m11359a("1006", str)) {
            return null;
        }
        return applicationInfo.nonLocalizedLabel;
    }

    /* renamed from: e */
    public static boolean m11352e(ApplicationInfo applicationInfo, String str) {
        return applicationInfo != null && m11359a("1007", str) && applicationInfo.enabled;
    }

    /* renamed from: f */
    public static String m11350f(ApplicationInfo applicationInfo, String str) {
        if (applicationInfo == null || !m11359a("1008", str)) {
            return null;
        }
        return applicationInfo.processName;
    }

    /* renamed from: g */
    public static CharSequence m11348g(ApplicationInfo applicationInfo, String str) {
        if (applicationInfo == null || !m11359a("1101", str)) {
            return null;
        }
        return applicationInfo.loadLabel(MobSDK.getContext().getPackageManager());
    }

    /* renamed from: a */
    public static ApplicationInfo m11360a(Object obj, String str) {
        if (m11359a("2001", str)) {
            return (ApplicationInfo) ReflectHelper.getInstanceField(obj, C5731l.m12674a("015ekkhVejIdejWejfeUfJfj)f5fgfe"), null);
        }
        return null;
    }

    /* renamed from: b */
    public static Signature[] m11357b(Object obj, String str) {
        if (m11359a("2002", str)) {
            return (Signature[]) ReflectHelper.getInstanceField(obj, C5731l.m12674a("010AgiejffKfej5ehekOgNgi"), null);
        }
        return null;
    }

    /* renamed from: c */
    public static String m11355c(Object obj, String str) {
        return m11359a("2004", str) ? (String) ReflectHelper.getInstanceField(obj, C5731l.m12674a("0117eeZg^ekgiejfeEfZfi(e8eg4g"), "1.0") : "1.0";
    }

    /* renamed from: d */
    public static long m11353d(Object obj, String str) {
        if (m11359a("2005", str)) {
            return ((Long) ReflectHelper.getInstanceField(obj, C5731l.m12674a("016Lfgejekgi<jCfjPf8gi9jehhTflejeg_g"), 0L)).longValue();
        }
        return 0L;
    }

    /* renamed from: e */
    public static long m11351e(Object obj, String str) {
        if (m11359a("2006", str)) {
            return ((Long) ReflectHelper.getInstanceField(obj, C5731l.m12674a("014heXgiAjVfh5k.ed;ejgEflejeg1g"), 0L)).longValue();
        }
        return 0L;
    }

    /* renamed from: f */
    public static int m11349f(Object obj, String str) {
        if (m11359a("2007", str)) {
            return ((Integer) ReflectHelper.getInstanceField(obj, C5731l.m12674a("011)eeYgJekgiejfe1fDhkfeedXg"), 0)).intValue();
        }
        return 0;
    }

    /* renamed from: g */
    public static long m11347g(Object obj, String str) {
        if (m11359a("2101", str)) {
            return ((Long) ReflectHelper.invokeInstanceMethodNoThrow(obj, C5731l.m12674a("018)ffYgj1gdfeAfUffhj%gWekgiejfe;f3hkfeed1g"), 0L, new Object[0])).longValue();
        }
        return 0L;
    }

    /* renamed from: a */
    public static boolean m11359a(String str, String str2) {
        String str3 = (String) C5747b.m12583a("aps", (Object) null);
        if (str3 == null) {
            return true;
        }
        String[] split = str3.split(";");
        if (TextUtils.equals(str2, C6152DH.SyncMtd.getPackageName())) {
            if (split.length > 1) {
                return !split[1].contains(str);
            }
            return true;
        } else if (split.length > 0) {
            return !split[0].contains(str);
        } else {
            return true;
        }
    }
}
