package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.IDownloadEvent;
import com.liulishuo.filedownloader.event.IDownloadListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class FileDownloadConnectListener extends IDownloadListener {
    @Override // com.liulishuo.filedownloader.event.IDownloadListener
    public boolean callback(IDownloadEvent iDownloadEvent) {
        return false;
    }

    public abstract void connected();

    public abstract void disconnected();
}
