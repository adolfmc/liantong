package com.baidu.cloud.download.core;

import com.baidu.cloud.download.base.DownloadTask;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SingleDownloadTask extends DownloadTaskImpl {
    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected Map<String, String> getHttpHeaders(ThreadRecord threadRecord) {
        return null;
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected int getResponseCode() {
        return 200;
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected void insertIntoDB(ThreadRecord threadRecord) {
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected void updateDB(ThreadRecord threadRecord) {
    }

    public SingleDownloadTask(DownloadInfo downloadInfo, ThreadRecord threadRecord, DownloadTask.OnDownloadListener onDownloadListener) {
        super(downloadInfo, threadRecord, onDownloadListener);
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected RandomAccessFile getFile(File file, String str, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(file, str), "rwd");
        randomAccessFile.seek(0L);
        return randomAccessFile;
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
