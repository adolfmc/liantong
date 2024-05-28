package com.liulishuo.okdownload.core.breakpoint;

import android.support.annotation.NonNull;
import com.liulishuo.okdownload.core.breakpoint.RemitSyncExecutor;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class RemitSyncToDBHelper {
    long delayMillis;
    private final RemitSyncExecutor executor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemitSyncToDBHelper(@NonNull RemitSyncExecutor.RemitAgent remitAgent) {
        this(new RemitSyncExecutor(remitAgent));
    }

    RemitSyncToDBHelper(@NonNull RemitSyncExecutor remitSyncExecutor) {
        this.executor = remitSyncExecutor;
        this.delayMillis = 1500L;
    }

    void shutdown() {
        this.executor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isNotFreeToDatabase(int i) {
        return !this.executor.isFreeToDatabase(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onTaskStart(int i) {
        this.executor.removePostWithId(i);
        this.executor.postSyncInfoDelay(i, this.delayMillis);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endAndEnsureToDB(int i) {
        this.executor.removePostWithId(i);
        try {
            if (this.executor.isFreeToDatabase(i)) {
                return;
            }
            this.executor.postSync(i);
        } finally {
            this.executor.postRemoveFreeId(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void discard(int i) {
        this.executor.removePostWithId(i);
        this.executor.postRemoveInfo(i);
    }
}
