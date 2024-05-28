package com.sinovatech.unicom.separatemodule.live.capture.util;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TimeUtil {
    public static String timeStamp2Date(String str, String str2) {
        if (TextUtils.isEmpty(str) || str.equals("null")) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat((str2 == null || str2.isEmpty()) ? "yyyy/MM/dd HH:mm:ss" : "yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.format(new Date(Long.valueOf(str + "000").longValue()));
    }

    public static String timeStamp2Date(long j, String str) {
        return new SimpleDateFormat((str == null || str.isEmpty()) ? "yyyy/MM/dd HH:mm:ss" : "yyyy/MM/dd HH:mm:ss").format(new Date(j));
    }

    public static String time4VideoTime(long j) {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        if (j < 1000) {
            j = 1000;
        }
        long j2 = j / 1000;
        long j3 = j2 % 60;
        long j4 = (j2 / 60) % 60;
        long j5 = j2 / 3600;
        if (j5 < 10) {
            sb = new StringBuilder();
            str = "0";
        } else {
            sb = new StringBuilder();
            str = "";
        }
        sb.append(str);
        sb.append(j5);
        String sb4 = sb.toString();
        if (j4 < 10) {
            sb2 = new StringBuilder();
            str2 = "0";
        } else {
            sb2 = new StringBuilder();
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(j4);
        String sb5 = sb2.toString();
        if (j3 < 10) {
            sb3 = new StringBuilder();
            str3 = "0";
        } else {
            sb3 = new StringBuilder();
            str3 = "";
        }
        sb3.append(str3);
        sb3.append(j3);
        String sb6 = sb3.toString();
        if (j5 > 0) {
            return sb4 + ":" + sb5 + ":" + sb6;
        }
        return sb5 + ":" + sb6;
    }

    public static long[] getMinuteAndSecond(long j) {
        long j2 = j / 1000;
        return new long[]{j2 / 60, j2 % 60};
    }

    public static String getCurrentDate() {
        try {
            return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            MsLogUtil.m7977e("TimeUtil ==> getCurrentDate() :", e.getMessage());
            return "";
        }
    }
}
