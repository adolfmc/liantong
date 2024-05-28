package com.liulishuo.filedownloader.message;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
interface IMessageSnapshot {
    String getEtag();

    String getFileName();

    int getId();

    long getLargeSofarBytes();

    long getLargeTotalBytes();

    int getRetryingTimes();

    int getSmallSofarBytes();

    int getSmallTotalBytes();

    byte getStatus();

    Throwable getThrowable();

    boolean isLargeFile();

    boolean isResuming();

    boolean isReusedDownloadedFile();
}
