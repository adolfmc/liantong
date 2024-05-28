package com.mob.tools.network;

import com.mob.tools.proguard.EverythingKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class FileDownloadListener implements EverythingKeeper {
    private boolean isCanceled = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onProgress(int i, long j, long j2);

    public void cancel() {
        this.isCanceled = true;
    }

    public boolean isCanceled() {
        return this.isCanceled;
    }
}
