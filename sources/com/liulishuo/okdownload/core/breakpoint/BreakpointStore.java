package com.liulishuo.okdownload.core.breakpoint;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface BreakpointStore {
    @NonNull
    BreakpointInfo createAndInsert(@NonNull DownloadTask downloadTask) throws IOException;

    @Nullable
    BreakpointInfo findAnotherInfoFromCompare(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo);

    int findOrCreateId(@NonNull DownloadTask downloadTask);

    @Nullable
    BreakpointInfo get(int i);

    @Nullable
    String getResponseFilename(String str);

    boolean isFileDirty(int i);

    boolean isOnlyMemoryCache();

    void remove(int i);

    boolean update(@NonNull BreakpointInfo breakpointInfo) throws IOException;
}
