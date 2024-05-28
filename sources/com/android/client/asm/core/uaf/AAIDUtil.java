package com.android.client.asm.core.uaf;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AAIDUtil {
    public static final String AKID_FPS = "0000";
    public static final String AKID_MOCK = "8000";
    public static final String AKID_SFT = "8000";
    public static final String AKID_WB = "0000";

    public static String getTokenAKId(String str, String str2) {
        String[] split = str.split("#");
        String str3 = split[0];
        String format = String.format("%04X", Integer.valueOf(Integer.parseInt(split[1], 16) | Integer.parseInt(str2, 16)));
        return str3 + "#" + format;
    }
}
