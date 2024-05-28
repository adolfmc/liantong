package com.liulishuo.filedownloader.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileDownloadStatus {
    public static final byte INVALID_STATUS = 0;
    public static final byte blockComplete = 4;
    public static final byte completed = -3;
    public static final byte connected = 2;
    public static final byte error = -1;
    public static final byte paused = -2;
    public static final byte pending = 1;
    public static final byte progress = 3;
    public static final byte retry = 5;
    public static final byte started = 6;
    public static final byte toFileDownloadService = 11;
    public static final byte toLaunchPool = 10;
    public static final byte warn = -4;

    public static boolean isIng(int i) {
        return i >= 1 && i <= 6;
    }

    public static boolean isOver(int i) {
        return i < 0;
    }
}
