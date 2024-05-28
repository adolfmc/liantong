package com.baidu.rtc.utils;

import android.text.TextUtils;
import com.webrtc.Logging;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static String randomString(Integer num) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(num.intValue());
        for (int i = 0; i < num.intValue(); i++) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    public static String randomNumber(Integer num) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(num.intValue());
        for (int i = 0; i < num.intValue(); i++) {
            sb.append("0123456789".charAt(random.nextInt(10)));
        }
        return sb.toString();
    }

    public static long strToLong(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Long.valueOf(str).longValue();
            } catch (NumberFormatException e) {
                Logging.m5304e("CommonUtils", "number format fault:" + e.getMessage());
            }
        }
        return -1L;
    }
}
