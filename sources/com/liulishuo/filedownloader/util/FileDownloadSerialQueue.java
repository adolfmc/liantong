package com.liulishuo.filedownloader.util;

import android.support.annotation.NonNull;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.DownloadTaskAdapter;
import com.liulishuo.filedownloader.FileDownloadList;
import com.liulishuo.okdownload.DownloadSerialQueue;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.UnifiedListenerManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FileDownloadSerialQueue {
    final UnifiedListenerManager listenerManager;
    final DownloadSerialQueue serialQueue;

    public FileDownloadSerialQueue() {
        this(new DownloadSerialQueue(), new UnifiedListenerManager());
    }

    public FileDownloadSerialQueue(@NonNull DownloadSerialQueue downloadSerialQueue, @NonNull UnifiedListenerManager unifiedListenerManager) {
        this.serialQueue = downloadSerialQueue;
        this.listenerManager = unifiedListenerManager;
        this.serialQueue.setListener(this.listenerManager.getHostListener());
    }

    public void enqueue(BaseDownloadTask baseDownloadTask) {
        DownloadTaskAdapter downloadTaskAdapter = (DownloadTaskAdapter) baseDownloadTask;
        downloadTaskAdapter.assembleDownloadTask();
        FileDownloadList.getImpl().addIndependentTask(downloadTaskAdapter);
        this.serialQueue.enqueue(downloadTaskAdapter.getDownloadTask());
        this.listenerManager.addAutoRemoveListenersWhenTaskEnd(downloadTaskAdapter.getId());
        this.listenerManager.attachListener(downloadTaskAdapter.getDownloadTask(), downloadTaskAdapter.getCompatListener());
    }

    public void pause() {
        this.serialQueue.pause();
    }

    public void resume() {
        this.serialQueue.resume();
    }

    public int getWorkingTaskId() {
        return this.serialQueue.getWorkingTaskId();
    }

    public int getWaitingTaskCount() {
        return this.serialQueue.getWaitingTaskCount();
    }

    public List<BaseDownloadTask> shutdown() {
        DownloadTask[] shutdown = this.serialQueue.shutdown();
        ArrayList arrayList = new ArrayList();
        for (DownloadTask downloadTask : shutdown) {
            DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
            if (findDownloadTaskAdapter != null) {
                arrayList.add(findDownloadTaskAdapter);
                FileDownloadList.getImpl().remove(findDownloadTaskAdapter);
            }
        }
        return arrayList;
    }
}
