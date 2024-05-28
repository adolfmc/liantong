package com.baidu.rtc.logreport;

import com.webrtc.Logging;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BdLogReport {
    private static final boolean ENABLE_LOG_REPORT = false;
    private static final String TAG = "BdLogReport";

    public static void init(String str, long j, String str2, String str3) {
    }

    public static void release() {
    }

    public static void setConsoleEnable(boolean z) {
    }

    public static void setParams(String str, long j, String str2) {
    }

    public static void setReportEnable(boolean z) {
    }

    public static void stopReport() {
    }

    public static void reportEvent(String str) {
        Logging.m5305d("BdLogReport", "rtc event:" + str);
    }

    public static void reportLog(int i, String str) {
        Logging.m5305d("BdLogReport", "rtc log:" + str);
    }
}
