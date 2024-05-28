package com.p319ss.android.socialbase.downloader.impls;

import com.p319ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultRetryDelayTimeCalculator */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DefaultRetryDelayTimeCalculator implements IRetryDelayTimeCalculator {
    @Override // com.p319ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
    public long calculateRetryDelayTime(int i, int i2) {
        if (i == 1) {
            return 3000L;
        }
        if (i == 2) {
            return 15000L;
        }
        if (i == 3) {
            return 30000L;
        }
        return i > 3 ? 300000L : 0L;
    }
}
