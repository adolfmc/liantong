package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.segment.SegmentUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SegmentUtils {
    public static long getFirstOffset(@NonNull List<Segment> list) {
        int size = list.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Segment segment = list.get(i);
            if (segment.getStartOffset() > j) {
                break;
            }
            if (segment.getCurrentOffsetRead() > j) {
                j = segment.getCurrentOffsetRead();
            }
        }
        return j;
    }

    public static long getDownloadedBytes(@NonNull List<Segment> list) {
        long j = 0;
        long j2 = -1;
        long j3 = -1;
        for (Segment segment : list) {
            if (j2 == -1) {
                if (segment.getDownloadBytes() > 0) {
                    j2 = segment.getStartOffset();
                    j3 = segment.getCurrentOffset();
                }
            } else if (segment.getStartOffset() <= j3) {
                if (segment.getCurrentOffset() > j3) {
                    j3 = segment.getCurrentOffset();
                }
            } else {
                j += j3 - j2;
                if (segment.getDownloadBytes() > 0) {
                    j2 = segment.getStartOffset();
                    j3 = segment.getCurrentOffset();
                } else {
                    j2 = -1;
                    j3 = -1;
                }
            }
        }
        return (j2 < 0 || j3 <= j2) ? j : j + (j3 - j2);
    }
}
