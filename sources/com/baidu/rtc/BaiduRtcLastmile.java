package com.baidu.rtc;

import com.baidu.rtc.internal.BaiduRtcLastmileImp;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaiduRtcLastmile {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface BaiduRtcLastmileDelegate {
        void onLastmileProbeResult(LastmileProbeResult lastmileProbeResult);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LastmileProbeResult {
        public int quality;
        public int rtt;
        public short state;
        public LastmileProbeOneWayResult uplinkReport = new LastmileProbeOneWayResult();
        public LastmileProbeOneWayResult downlinkReport = new LastmileProbeOneWayResult();

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class LastmileProbeOneWayResult {
            public int bandwidth;
            public int jitter;
            public int lossRate;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcLastmileQuality {
        QUALITY_UNKNOWN,
        QUALITY_EXCELLENT,
        QUALITY_GOOD,
        QUALITY_POOR,
        QUALITY_BAD,
        QUALITY_VBAD,
        QUALITY_DOWN,
        QUALITY_DETECTING
    }

    public abstract void stopProbeTest();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcLastmileState {
        PROBE_RESULT_COMPLETE(1),
        PROBE_RESULT_INCOMPLETE(2),
        PROBE_RESULT_UNAVAILABLE(3);

        RtcLastmileState(int i) {
        }
    }

    public static synchronized BaiduRtcLastmileImp initProbeTest(String str, String str2, int i, int i2, BaiduRtcLastmileDelegate baiduRtcLastmileDelegate) {
        BaiduRtcLastmileImp baiduRtcLastmileImp;
        synchronized (BaiduRtcLastmile.class) {
            baiduRtcLastmileImp = new BaiduRtcLastmileImp(str, str2, baiduRtcLastmileDelegate);
            baiduRtcLastmileImp.startProbeTest(i, i2);
        }
        return baiduRtcLastmileImp;
    }

    public static void setServerUrl(String str) {
        BaiduRtcLastmileImp.setServerUrl(str);
    }
}
