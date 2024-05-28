package com.danikula.videocache.file;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TotalCountLruDiskUsage extends LruDiskUsage {
    private final int maxCount;

    public TotalCountLruDiskUsage(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Max count must be positive number!");
        }
        this.maxCount = i;
    }

    @Override // com.danikula.videocache.file.LruDiskUsage
    protected boolean accept(File file, long j, int i) {
        return i <= this.maxCount;
    }
}
