package com.chinaunicon.jtwifilib.jtcommon.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtStringUtil {
    public static String getToken(String str, String str2) {
        String MD5SHA = JtMD5Util.MD5SHA(JtMD5Util.MD5SHA(str, "MD5"), "SHA-256");
        return JtMD5Util.MD5SHA(MD5SHA + str2, "SHA-256");
    }

    public static String getkey(String str) {
        return JtMD5Util.MD5SHA(JtMD5Util.MD5SHA(JtMD5Util.MD5SHA(str, "MD5"), "SHA-256"), "MD5");
    }
}
