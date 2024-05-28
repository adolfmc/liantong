package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StringUtil {
    public static String getString16() {
        StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length() <= 8) {
            stringBuffer.append(Integer.toHexString((int) (Math.random() * 100.0d)));
        }
        return stringBuffer.length() > 8 ? stringBuffer.substring(0, 8) : "";
    }

    public static String getWANName(String str, String str2) {
        String[] split;
        for (String str3 : str.split("/")) {
            if (str3.contains(str2)) {
                return str3;
            }
        }
        return "";
    }

    public static String getOnly(Context context) {
        String string = SharedPreferencesHelp.getInstance(context, "version").getString("uuid");
        if (TextUtils.isEmpty(string)) {
            string = getUUID();
            SharedPreferencesHelp.getInstance(context, "version").putString("uuid", string);
        }
        return string.toLowerCase();
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static boolean isNetworkOnline() {
        try {
            int waitFor = Runtime.getRuntime().exec("ping -c 3 www.baidu.com").waitFor();
            JtL.m16339i("Avalible", "Process:" + waitFor);
            return waitFor == 0;
        } catch (IOException | InterruptedException unused) {
            return false;
        }
    }
}
