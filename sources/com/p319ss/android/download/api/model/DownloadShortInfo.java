package com.p319ss.android.download.api.model;

import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.Arrays;

/* renamed from: com.ss.android.download.api.model.DownloadShortInfo */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DownloadShortInfo {
    public String fileName;
    public boolean onlyWifi;

    /* renamed from: id */
    public long f18791id = -1;
    public int status = -1;
    public long totalBytes = -1;
    public long currentBytes = -1;
    public int failStatus = 0;

    public boolean equals(Object obj) {
        if ((obj instanceof DownloadShortInfo) && obj != null) {
            DownloadShortInfo downloadShortInfo = (DownloadShortInfo) obj;
            return ((this.f18791id > downloadShortInfo.f18791id ? 1 : (this.f18791id == downloadShortInfo.f18791id ? 0 : -1)) == 0) && (this.status == downloadShortInfo.status) && ((this.totalBytes > downloadShortInfo.totalBytes ? 1 : (this.totalBytes == downloadShortInfo.totalBytes ? 0 : -1)) == 0) && ((TextUtils.isEmpty(this.fileName) && TextUtils.isEmpty(downloadShortInfo.fileName)) || (!TextUtils.isEmpty(this.fileName) && !TextUtils.isEmpty(downloadShortInfo.fileName) && this.fileName.equals(downloadShortInfo.fileName)));
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f18791id), Integer.valueOf(this.status), Long.valueOf(this.totalBytes), this.fileName});
    }

    public void updateFromNewDownloadInfo(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        this.f18791id = downloadInfo.getId();
        this.status = downloadInfo.getStatus();
        this.currentBytes = downloadInfo.getCurBytes();
        this.totalBytes = downloadInfo.getTotalBytes();
        this.fileName = downloadInfo.getTargetFilePath();
        BaseException failedException = downloadInfo.getFailedException();
        if (failedException != null) {
            this.failStatus = failedException.getErrorCode();
        } else {
            this.failStatus = 0;
        }
        this.onlyWifi = downloadInfo.isOnlyWifi();
    }
}
