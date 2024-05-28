package com.sinovatech.unicom.separatemodule.audience.util;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class StringUtils {
    public static String encodeUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || "null".equals(str) || str.trim().length() == 0 || str.length() == 0;
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }
}
