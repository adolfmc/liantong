package com.gmrz.appsdk.util;

import android.util.Base64;
import java.nio.charset.StandardCharsets;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Base64Util {
    private static final int FLAG = 11;

    public static String decodeToString(String str) {
        return new String(Base64.decode(str, 11), StandardCharsets.UTF_8);
    }

    public static String decodeToString(byte[] bArr) {
        return new String(Base64.decode(bArr, 11), StandardCharsets.UTF_8);
    }
}
