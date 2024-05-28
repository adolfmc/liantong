package com.baidu.cloud.download.core;

import com.baidu.cloud.download.base.DownloadTask;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MultiDownloadTask extends DownloadTaskImpl {
    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected int getResponseCode() {
        return 206;
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected void insertIntoDB(ThreadRecord threadRecord) {
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected void updateDB(ThreadRecord threadRecord) {
    }

    public MultiDownloadTask(DownloadInfo downloadInfo, ThreadRecord threadRecord, DownloadTask.OnDownloadListener onDownloadListener) {
        super(downloadInfo, threadRecord, onDownloadListener);
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected Map<String, String> getHttpHeaders(ThreadRecord threadRecord) {
        HashMap hashMap = new HashMap();
        long start = threadRecord.getStart() + threadRecord.getFinished();
        long end = threadRecord.getEnd();
        hashMap.put("Range", "bytes=" + start + "-" + end);
        return hashMap;
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected RandomAccessFile getFile(File file, String str, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(file, str), "rwd");
        randomAccessFile.seek(j);
        return randomAccessFile;
    }

    @Override // com.baidu.cloud.download.core.DownloadTaskImpl
    protected String getTag() {
        return getClass().getSimpleName();
    }
}
