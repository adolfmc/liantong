package com.mob.commons;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.ResHelper;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.o */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5859o {

    /* renamed from: k */
    private static final String f14445k = C5731l.m12674a("011d$feegeg:mh fe_dXemgiIm");

    /* renamed from: a */
    public static final String f14435a = f14445k + ".mrlock";

    /* renamed from: b */
    public static final String f14436b = f14445k + C5731l.m12674a("007Ugeed8ih'fe9d$em");

    /* renamed from: c */
    public static final String f14437c = f14445k + C5731l.m12674a("0110geffQh1fegfVehGgdfe1d%em");

    /* renamed from: d */
    public static final String f14438d = f14445k + C5731l.m12674a("008.geedelei]h9feXd em");

    /* renamed from: e */
    public static final String f14439e = f14445k + C5731l.m12674a("008TgeedgieiOh5fe0dKem");

    /* renamed from: f */
    public static final String f14440f = f14445k + ".cl_lock";

    /* renamed from: g */
    public static final String f14441g = f14445k + ".gcf_lock";

    /* renamed from: h */
    public static final String f14442h = f14445k + ".mp_lock";

    /* renamed from: i */
    public static final Object f14443i = new Object();

    /* renamed from: j */
    public static final Object f14444j = new Object();

    /* renamed from: a */
    public static synchronized File m12223a(String str) {
        File dataCacheFile;
        synchronized (C5859o.class) {
            dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), str, true);
        }
        return dataCacheFile;
    }

    /* renamed from: a */
    public static boolean m12225a(File file, InterfaceC5858n interfaceC5858n) {
        return m12224a(file, true, interfaceC5858n);
    }

    /* renamed from: b */
    private static String m12222b(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.endsWith(f14437c)) {
                return f14437c;
            }
            if (str.endsWith(f14436b)) {
                return f14436b;
            }
            if (str.endsWith(f14438d)) {
                return f14438d;
            }
            if (str.endsWith(f14439e)) {
                return f14439e;
            }
            if (str.endsWith(f14440f)) {
                return f14440f;
            }
            if (str.endsWith(f14441g)) {
                return f14441g;
            }
        }
        return str;
    }

    /* renamed from: a */
    public static boolean m12224a(File file, boolean z, InterfaceC5858n interfaceC5858n) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            String absolutePath = file.getAbsolutePath();
            synchronized (m12222b(absolutePath)) {
                FileLocker fileLocker = new FileLocker();
                fileLocker.setLockFile(absolutePath);
                if (fileLocker.lock(z)) {
                    if (!interfaceC5858n.mo11219a(fileLocker)) {
                        fileLocker.release();
                    }
                    return true;
                }
                return false;
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return true;
        }
    }
}
