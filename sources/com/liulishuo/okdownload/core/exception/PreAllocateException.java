package com.liulishuo.okdownload.core.exception;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PreAllocateException extends IOException {
    private final long freeSpace;
    private final long requireSpace;

    public PreAllocateException(long j, long j2) {
        super("There is Free space less than Require space: " + j2 + " < " + j);
        this.requireSpace = j;
        this.freeSpace = j2;
    }

    public long getRequireSpace() {
        return this.requireSpace;
    }

    public long getFreeSpace() {
        return this.freeSpace;
    }
}
