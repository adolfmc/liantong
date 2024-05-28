package com.chinaunicon.jtwifilib.core.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class KeyUtil {
    public static String getKey(String str) {
        return MD5Util.MD5SHA(MD5Util.MD5SHA(MD5Util.MD5SHA(str, "MD5"), "SHA-256"), "MD5");
    }
}
