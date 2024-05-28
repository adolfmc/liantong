package com.heytap.mcssdk.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.utils.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4743a {

    /* renamed from: a */
    private static final String f10701a = "file";

    /* renamed from: b */
    private static final String f10702b = "ro.crypto.type";

    /* renamed from: a */
    private static String m15510a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m15511a() {
        return "file".equals(m15510a("ro.crypto.type"));
    }
}
