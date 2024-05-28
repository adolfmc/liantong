package com.example.asus.detectionandalign.utils;

import java.util.Arrays;
import szcom.googlecode.mp4parser.authoring.Track;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.l */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4302l {
    /* renamed from: a */
    public static double m15931a(Track track, double d, boolean z) {
        double[] dArr = new double[track.getSyncSamples().length];
        int i = 0;
        double d2 = 0.0d;
        double d3 = 0.0d;
        long j = 0;
        for (int i2 = 0; i2 < track.getSampleDurations().length; i2++) {
            long j2 = track.getSampleDurations()[i2];
            j++;
            if (Arrays.binarySearch(track.getSyncSamples(), j) >= 0) {
                dArr[Arrays.binarySearch(track.getSyncSamples(), j)] = d3;
            }
            d3 += j2 / track.getTrackMetaData().getTimescale();
        }
        int length = dArr.length;
        while (i < length) {
            double d4 = dArr[i];
            if (d4 > d) {
                return z ? d4 : d2;
            }
            i++;
            d2 = d4;
        }
        return dArr[dArr.length - 1];
    }
}
