package com.vivo.push.util;

import android.os.Build;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.n */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Device {

    /* renamed from: f */
    private static Method f21228f;

    /* renamed from: a */
    public static final boolean f21223a = Utility.m5435b("ro.vivo.product.overseas", "no").equals("yes");

    /* renamed from: b */
    public static final boolean f21224b = m5382b("rom_1.0");

    /* renamed from: c */
    public static final boolean f21225c = m5382b("rom_2.0");

    /* renamed from: d */
    public static final boolean f21226d = m5382b("rom_2.5");

    /* renamed from: e */
    public static final boolean f21227e = m5382b("rom_3.0");

    /* renamed from: g */
    private static String f21229g = null;

    /* renamed from: h */
    private static String f21230h = null;

    /* renamed from: a */
    public static String m5384a(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            e.printStackTrace();
            str3 = str2;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    /* renamed from: a */
    public static synchronized String m5386a() {
        synchronized (Device.class) {
            if (f21229g == null && f21230h == null) {
                try {
                    Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class);
                    f21228f = declaredMethod;
                    declaredMethod.setAccessible(true);
                    f21229g = (String) f21228f.invoke(null, "ro.vivo.rom", "@><@");
                    f21230h = (String) f21228f.invoke(null, "ro.vivo.rom.version", "@><@");
                } catch (Exception unused) {
                    LogUtil.m5346b("Device", "getRomCode error");
                }
            }
            LogUtil.m5341d("Device", "sRomProperty1 : " + f21229g + " ; sRomProperty2 : " + f21230h);
            String m5385a = m5385a(f21229g);
            if (TextUtils.isEmpty(m5385a)) {
                String m5385a2 = m5385a(f21230h);
                if (TextUtils.isEmpty(m5385a2)) {
                    return null;
                }
                return m5385a2;
            }
            return m5385a;
        }
    }

    /* renamed from: a */
    private static String m5385a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("rom_([\\d]*).?([\\d]*)", 2).matcher(str);
        if (matcher.find()) {
            StringBuilder sb = new StringBuilder();
            sb.append(matcher.group(1));
            sb.append(TextUtils.isEmpty(matcher.group(2)) ? "0" : matcher.group(2).substring(0, 1));
            return sb.toString();
        }
        return null;
    }

    /* renamed from: b */
    private static boolean m5382b(String str) {
        String m5435b = Utility.m5435b("ro.vivo.rom", "");
        String m5435b2 = Utility.m5435b("ro.vivo.rom.version", "");
        LogUtil.m5341d("Device", "ro.vivo.rom = " + m5435b + " ; ro.vivo.rom.version = " + m5435b2);
        if (m5435b == null || !m5435b.contains(str)) {
            return m5435b2 != null && m5435b2.contains(str);
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m5383b() {
        if (TextUtils.isEmpty(Build.MANUFACTURER)) {
            LogUtil.m5341d("Device", "Build.MANUFACTURER is null");
            return false;
        }
        LogUtil.m5341d("Device", "Build.MANUFACTURER is " + Build.MANUFACTURER);
        return Build.MANUFACTURER.toLowerCase().contains("bbk") || Build.MANUFACTURER.toLowerCase().startsWith("vivo");
    }
}
