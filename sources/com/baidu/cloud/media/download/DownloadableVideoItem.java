package com.baidu.cloud.media.download;

import java.util.Observable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class DownloadableVideoItem extends Observable {
    public static final String[] ERROR_CODE_DESC = {"ERROR_CODE_NO_ERROR", "ERROR_CODE_INVALID_URL", "ERROR_CODE_NETWORK_FAILED", "ERROR_CODE_SDCARD_UNMOUNTED", "ERROR_CODE_M3U8_INVALID_FORMAT", "ERROR_CODE_M3U8_SAVE_FAILED", "ERROR_CODE_M3U8_DRM_INVALID", "ERROR_CODE_TS_SAVE_FAILED"};
    public static final int ERROR_CODE_INVALID_URL = 1;
    public static final int ERROR_CODE_M3U8_DRM_INVALID = 6;
    public static final int ERROR_CODE_M3U8_INVALID_FORMAT = 4;
    public static final int ERROR_CODE_M3U8_SAVE_FAILED = 5;
    public static final int ERROR_CODE_NETWORK_FAILED = 2;
    public static final int ERROR_CODE_NO_ERROR = 0;
    public static final int ERROR_CODE_SDCARD_UNMOUNTED = 3;
    public static final int ERROR_CODE_TS_SAVE_FAILED = 7;
    protected volatile DownloadStatus downloadStatus = DownloadStatus.NONE;
    protected volatile int errorCode;
    protected String failReason;
    protected String fileName;
    protected String folderPath;
    protected volatile int progress;
    protected volatile int speed;
    protected volatile int totalSize;
    protected String url;

    public int getErrorCode() {
        return this.errorCode;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum DownloadStatus {
        NONE(0, "first add"),
        DOWNLOADING(1, "downloading videos"),
        PAUSED(2, "paused"),
        COMPLETED(3, "completed"),
        ERROR(4, "failed to download"),
        DELETED(5, "delete manually"),
        PENDING(6, "pending, will start automatically(blocked by Parallel Strategy)");
        
        private int code;
        private String msg;

        DownloadStatus(int i, String str) {
            this.code = i;
            this.msg = str;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.msg;
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getLocalAbsolutePath() {
        String str = this.fileName;
        if (str == null || str.equals("")) {
            return this.folderPath;
        }
        String str2 = this.folderPath;
        if (str2 == null || str2.equals("")) {
            return null;
        }
        return this.folderPath + "/" + this.fileName;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public float getProgress() {
        return this.progress / 100.0f;
    }

    public String getSpeed() {
        if (this.downloadStatus == DownloadStatus.DOWNLOADING) {
            if (this.speed < 1024) {
                return this.speed + "KB/s";
            }
            return (this.speed / 1024) + "MB/s";
        }
        return "0KB/s";
    }

    public DownloadStatus getStatus() {
        return this.downloadStatus;
    }

    public String getFailReason() {
        return this.failReason;
    }
}
