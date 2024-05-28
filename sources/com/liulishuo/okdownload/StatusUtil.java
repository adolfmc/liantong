package com.liulishuo.okdownload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointStore;
import com.liulishuo.okdownload.core.dispatcher.DownloadDispatcher;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StatusUtil {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum Status {
        PENDING,
        RUNNING,
        COMPLETED,
        IDLE,
        UNKNOWN
    }

    public static boolean isSameTaskPendingOrRunning(@NonNull DownloadTask downloadTask) {
        return OkDownload.with().downloadDispatcher().findSameTask(downloadTask) != null;
    }

    public static Status getStatus(@NonNull DownloadTask downloadTask) {
        Status isCompletedOrUnknown = isCompletedOrUnknown(downloadTask);
        if (isCompletedOrUnknown == Status.COMPLETED) {
            return Status.COMPLETED;
        }
        DownloadDispatcher downloadDispatcher = OkDownload.with().downloadDispatcher();
        return downloadDispatcher.isPending(downloadTask) ? Status.PENDING : downloadDispatcher.isRunning(downloadTask) ? Status.RUNNING : isCompletedOrUnknown;
    }

    public static Status getStatus(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return getStatus(createFinder(str, str2, str3));
    }

    public static boolean isCompleted(@NonNull DownloadTask downloadTask) {
        return isCompletedOrUnknown(downloadTask) == Status.COMPLETED;
    }

    public static Status isCompletedOrUnknown(@NonNull DownloadTask downloadTask) {
        BreakpointStore breakpointStore = OkDownload.with().breakpointStore();
        BreakpointInfo breakpointInfo = breakpointStore.get(downloadTask.getId());
        String filename = downloadTask.getFilename();
        File parentFile = downloadTask.getParentFile();
        File file = downloadTask.getFile();
        if (breakpointInfo != null) {
            if (!breakpointInfo.isChunked() && breakpointInfo.getTotalLength() <= 0) {
                return Status.UNKNOWN;
            }
            if (file != null && file.equals(breakpointInfo.getFile()) && file.exists() && breakpointInfo.getTotalOffset() == breakpointInfo.getTotalLength()) {
                return Status.COMPLETED;
            }
            if (filename == null && breakpointInfo.getFile() != null && breakpointInfo.getFile().exists()) {
                return Status.IDLE;
            }
            if (file != null && file.equals(breakpointInfo.getFile()) && file.exists()) {
                return Status.IDLE;
            }
        } else if (breakpointStore.isOnlyMemoryCache() || breakpointStore.isFileDirty(downloadTask.getId())) {
            return Status.UNKNOWN;
        } else {
            if (file != null && file.exists()) {
                return Status.COMPLETED;
            }
            String responseFilename = breakpointStore.getResponseFilename(downloadTask.getUrl());
            if (responseFilename != null && new File(parentFile, responseFilename).exists()) {
                return Status.COMPLETED;
            }
        }
        return Status.UNKNOWN;
    }

    public static boolean isCompleted(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return isCompleted(createFinder(str, str2, str3));
    }

    @Nullable
    public static BreakpointInfo getCurrentInfo(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return getCurrentInfo(createFinder(str, str2, str3));
    }

    @Nullable
    public static BreakpointInfo getCurrentInfo(@NonNull DownloadTask downloadTask) {
        BreakpointStore breakpointStore = OkDownload.with().breakpointStore();
        BreakpointInfo breakpointInfo = breakpointStore.get(breakpointStore.findOrCreateId(downloadTask));
        if (breakpointInfo == null) {
            return null;
        }
        return breakpointInfo.copy();
    }

    @NonNull
    static DownloadTask createFinder(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        return new DownloadTask.Builder(str, str2, str3).build();
    }
}
