package com.baidu.rtc.logreport;

import com.webrtc.Logging;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCBitrateTracker {
    long mBytesDelta;
    double mBitrate = 0.0d;
    long mPreTimeMs = 0;
    long mPreByteCount = 0;

    public void updataBitrateWidhCurrentByteCount(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.mPreTimeMs;
        long j3 = currentTimeMillis - j2;
        long j4 = this.mPreByteCount;
        this.mBytesDelta = j - j4;
        if (j3 <= 0) {
            return;
        }
        if (j2 != 0 && j > j4) {
            this.mBitrate = ((this.mBytesDelta * 8) * 1000) / j3;
        }
        this.mPreByteCount = j;
        this.mPreTimeMs = currentTimeMillis;
    }

    public long getBytesDelta() {
        return this.mBytesDelta;
    }

    public String bitRateString() {
        return bitrateStringForBitrate(this.mBitrate);
    }

    public Double getBitrate() {
        return Double.valueOf(this.mBitrate);
    }

    public static String bitrateStringForBitrate(double d) {
        return d > 1000000.0d ? String.format("%.2fMbps", Double.valueOf(d * 1.0E-6d)) : d > 1000.0d ? String.format("%.0fKbps", Double.valueOf(d * 0.001d)) : String.format("%.0fbps", Double.valueOf(d));
    }

    public static int bitrateToString(String str) {
        if (str == null) {
            return -1;
        }
        try {
            if (str.indexOf("Mbps") != -1) {
                return (int) Math.round(Double.parseDouble(str.substring(0, str.indexOf("Mbps"))) * 1000000.0d);
            }
            if (str.indexOf("Kbps") != -1) {
                return (int) (Integer.parseInt(str.substring(0, str.indexOf("Kbps"))) * 1000.0d);
            }
            if (str.indexOf("bps") != -1) {
                return Integer.parseInt(str.substring(0, str.indexOf("bps")));
            }
            Logging.m5304e("BRTC", "illegal input num");
            return -1;
        } catch (NumberFormatException e) {
            Logging.m5304e("RTCBitrateTracker", "bitrateToString dataFormat error: " + e);
            return -1;
        }
    }
}
