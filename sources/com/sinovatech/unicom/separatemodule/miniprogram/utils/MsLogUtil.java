package com.sinovatech.unicom.separatemodule.miniprogram.utils;

import android.util.Log;
import com.sinovatech.unicom.common.URLEnvironmentConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MsLogUtil {
    /* renamed from: d */
    public static void m7980d(String str) {
        try {
            if (URLEnvironmentConfig.isForPublish()) {
                return;
            }
            String[] tagAndDetailMessage = getTagAndDetailMessage(str);
            Log.d(tagAndDetailMessage[0], tagAndDetailMessage[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public static void m7979d(String str, String str2) {
        try {
            if (URLEnvironmentConfig.isForPublish()) {
                return;
            }
            int length = 2001 - str.length();
            while (str2.length() > length) {
                Log.d(str, str2.substring(0, length));
                str2 = str2.substring(length);
            }
            Log.d(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m7978e(String str) {
        try {
            if (URLEnvironmentConfig.isForPublish()) {
                return;
            }
            String[] tagAndDetailMessage = getTagAndDetailMessage(str);
            Log.e(tagAndDetailMessage[0], tagAndDetailMessage[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    public static void m7977e(String str, String str2) {
        try {
            if (URLEnvironmentConfig.isForPublish()) {
                return;
            }
            Log.e(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] getTagAndDetailMessage(String str) {
        String[] strArr = new String[2];
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            if (!MsLogUtil.class.getName().equals(stackTraceElement.getClassName())) {
                strArr[0] = stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(".") + 1);
                strArr[1] = str;
                break;
            }
            i++;
        }
        return strArr;
    }
}
