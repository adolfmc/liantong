package com.liulishuo.okdownload.core.breakpoint;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.core.cause.EndCause;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DownloadStore extends BreakpointStore {
    @Nullable
    BreakpointInfo getAfterCompleted(int i);

    boolean markFileClear(int i);

    boolean markFileDirty(int i);

    void onSyncToFilesystemSuccess(@NonNull BreakpointInfo breakpointInfo, int i, long j) throws IOException;

    void onTaskEnd(int i, @NonNull EndCause endCause, @Nullable Exception exc);

    void onTaskStart(int i);
}
