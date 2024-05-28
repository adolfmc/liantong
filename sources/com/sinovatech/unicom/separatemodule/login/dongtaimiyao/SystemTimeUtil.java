package com.sinovatech.unicom.separatemodule.login.dongtaimiyao;

import android.os.SystemClock;
import com.sinovatech.unicom.common.DateUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SystemTimeUtil {
    private static long beginTime;
    private static long severTime;

    public static long currentTimeMillis() {
        long elapsedRealtime = severTime + SystemClock.elapsedRealtime();
        long j = beginTime;
        long j2 = elapsedRealtime - j;
        if (j == 0 || severTime == 0) {
            j2 = System.currentTimeMillis();
        }
        if (Math.abs(j2 - System.currentTimeMillis()) < 2000) {
            j2 = System.currentTimeMillis();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("服务端返回时间：");
        sb.append(DateUtils.timeStamp2Dategeshi(severTime + ""));
        MsLogUtil.m7979d("SystemTimeUtil", sb.toString());
        MsLogUtil.m7979d("SystemTimeUtil", "差值时间：" + (SystemClock.elapsedRealtime() - beginTime) + "");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("纠正时间：");
        sb2.append(DateUtils.timeStamp2Dategeshi(j2 + ""));
        MsLogUtil.m7979d("SystemTimeUtil", sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("系统时间：");
        sb3.append(DateUtils.timeStamp2Dategeshi(System.currentTimeMillis() + ""));
        MsLogUtil.m7979d("SystemTimeUtil", sb3.toString());
        return j2;
    }

    public static void setSeverTime(String str) {
        try {
            beginTime = SystemClock.elapsedRealtime();
            severTime = Long.parseLong(str);
            StringBuilder sb = new StringBuilder();
            sb.append("服务端返回时间：");
            sb.append(DateUtils.timeStamp2Dategeshi(str + ""));
            MsLogUtil.m7979d("SystemTimeUtil", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("系统时间：");
            sb2.append(DateUtils.timeStamp2Dategeshi(System.currentTimeMillis() + ""));
            MsLogUtil.m7979d("SystemTimeUtil", sb2.toString());
        } catch (Exception e) {
            e.printStackTrace();
            severTime = System.currentTimeMillis();
        }
    }
}
