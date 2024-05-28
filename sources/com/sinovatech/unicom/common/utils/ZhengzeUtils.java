package com.sinovatech.unicom.common.utils;

import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ZhengzeUtils {
    private static final String hasNum = ".*[0-9]{1,}.*";

    public static boolean isHasNum(String str) {
        return Pattern.matches(".*[0-9]{1,}.*", str);
    }
}
