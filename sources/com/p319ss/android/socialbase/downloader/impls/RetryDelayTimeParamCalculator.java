package com.p319ss.android.socialbase.downloader.impls;

import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;

/* renamed from: com.ss.android.socialbase.downloader.impls.RetryDelayTimeParamCalculator */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RetryDelayTimeParamCalculator implements IRetryDelayTimeCalculator {
    private final long[] mTimeArray;

    public RetryDelayTimeParamCalculator(String str) {
        this.mTimeArray = parseTimeArray(str);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
    public long calculateRetryDelayTime(int i, int i2) {
        long[] jArr = this.mTimeArray;
        if (jArr == null || jArr.length <= 0) {
            return 0L;
        }
        int i3 = i - 1;
        if (i3 < 0) {
            i3 = 0;
        }
        long[] jArr2 = this.mTimeArray;
        if (i3 > jArr2.length - 1) {
            i3 = jArr2.length - 1;
        }
        return this.mTimeArray[i3];
    }

    private long[] parseTimeArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] split = str.split(",");
            if (split.length == 0) {
                return null;
            }
            long[] jArr = new long[split.length];
            for (int i = 0; i < split.length; i++) {
                jArr[i] = Long.parseLong(split[i]);
            }
            return jArr;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
