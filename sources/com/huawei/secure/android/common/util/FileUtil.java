package com.huawei.secure.android.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileUtil {
    /* renamed from: a */
    private static String m13830a(String str) {
        int lastIndexOf;
        int i;
        return (str.isEmpty() || (lastIndexOf = str.lastIndexOf(".")) == -1 || (i = lastIndexOf + 1) == str.length()) ? "" : str.substring(i).toLowerCase(Locale.ENGLISH);
    }

    public static boolean checkFileExtValid(String str, String[] strArr) {
        if (str != null && !str.isEmpty() && strArr != null) {
            String m13830a = m13830a(str);
            for (String str2 : strArr) {
                if (m13830a.equalsIgnoreCase(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean filePathIsValid(String str, String str2) throws IllegalArgumentException, IOException {
        if (str != null && !str.equals("") && str2 != null && !str2.equals("")) {
            try {
                String decode = URLDecoder.decode(str2, "utf-8");
                String decode2 = URLDecoder.decode(str, "utf-8");
                if (!decode2.contains("..") && !decode2.contains("./") && !decode2.contains(".\\.\\") && !decode2.contains("%00") && !decode.contains("..") && !decode.contains("./") && !decode.contains(".\\.\\") && !decode.contains("%00")) {
                    return new File(decode2, decode).getCanonicalPath().startsWith(new File(decode2).getCanonicalPath());
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        return false;
    }

    public static boolean filePathIsValid(String str) throws UnsupportedEncodingException {
        if (str == null || str.equals("")) {
            return true;
        }
        if (str.contains("%")) {
            str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        }
        String decode = URLDecoder.decode(str, "utf-8");
        return (decode.contains("..") || decode.contains("./") || decode.contains(".\\.\\") || decode.contains("%00")) ? false : true;
    }
}
