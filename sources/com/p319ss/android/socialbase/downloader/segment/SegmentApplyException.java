package com.p319ss.android.socialbase.downloader.segment;

import com.p319ss.android.socialbase.downloader.exception.BaseException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.segment.SegmentApplyException */
/* loaded from: E:\11617560_dexfile_execute.dex */
class SegmentApplyException extends BaseException {
    public static final int ALREADY_APPLY_BY_OTHER = 1;
    public static final int BAD_SEGMENT = 6;
    public static final int CHANGE_SEGMENT = 5;
    public static final int PREV_END_ADJUST_FAIL = 4;
    public static final int PREV_OVERSTEP = 3;
    public static final int SEGMENT_NOT_EXIST = 2;
    private int applyCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SegmentApplyException(int i, String str) {
        super(1072, "applyCode=" + i + ", " + str);
        this.applyCode = i;
    }

    public int getSegmentApplyErrorCode() {
        return this.applyCode;
    }
}
