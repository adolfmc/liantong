package com.chinaunicon.jtwifilib.jtcommon.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FrequencyUtil {
    public static String getWifiFrequencyBand(int i) {
        return (i <= 2400 || i >= 2500) ? (i <= 4900 || i >= 5900) ? "" : "5G" : "2.4G";
    }
}
