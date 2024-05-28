package com.liulishuo.filedownloader;

import android.support.annotation.Nullable;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FileDownloadList {
    private static final String TAG = "FileDownloadList";
    private static volatile FileDownloadList singleton;
    final ArrayList<DownloadTaskAdapter> list = new ArrayList<>();

    public static FileDownloadList getImpl() {
        if (singleton == null) {
            synchronized (FileDownloadList.class) {
                if (singleton == null) {
                    singleton = new FileDownloadList();
                }
            }
        }
        return singleton;
    }

    public static void setSingleton(FileDownloadList fileDownloadList) {
        singleton = fileDownloadList;
    }

    FileDownloadList() {
    }

    @Nullable
    public BaseDownloadTask.IRunningTask get(int i) {
        synchronized (this.list) {
            Iterator<DownloadTaskAdapter> it = this.list.iterator();
            while (it.hasNext()) {
                DownloadTaskAdapter next = it.next();
                if (next.mo13743is(i) && OkDownload.with().downloadDispatcher().isRunning(((DownloadTaskAdapter) next.getOrigin()).getDownloadTask())) {
                    return next;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<DownloadTaskAdapter> getByFileDownloadListener(FileDownloadListener fileDownloadListener) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.list) {
            Iterator<DownloadTaskAdapter> it = this.list.iterator();
            while (it.hasNext()) {
                DownloadTaskAdapter next = it.next();
                if (next.getListener() != null && next.getListener() == fileDownloadListener) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    @Deprecated
    public boolean remove(BaseDownloadTask.IRunningTask iRunningTask, MessageSnapshot messageSnapshot) {
        if (iRunningTask == null) {
            return false;
        }
        return remove((DownloadTaskAdapter) iRunningTask.getOrigin());
    }

    public boolean remove(DownloadTaskAdapter downloadTaskAdapter) {
        Util.m13741d(TAG, "remove task: " + downloadTaskAdapter.getId());
        return this.list.remove(downloadTaskAdapter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addQueueTask(DownloadTaskAdapter downloadTaskAdapter) {
        if (downloadTaskAdapter.isMarkedAdded2List()) {
            Util.m13738w(TAG, "queue task: " + downloadTaskAdapter + " has been marked");
            return;
        }
        synchronized (this.list) {
            downloadTaskAdapter.markAdded2List();
            downloadTaskAdapter.assembleDownloadTask();
            this.list.add(downloadTaskAdapter);
            Util.m13741d(TAG, "add list in all " + downloadTaskAdapter + " " + this.list.size());
        }
    }

    public void addIndependentTask(DownloadTaskAdapter downloadTaskAdapter) {
        if (downloadTaskAdapter.isMarkedAdded2List()) {
            Util.m13738w(TAG, "independent task: " + downloadTaskAdapter.getId() + " has been added to queue");
            return;
        }
        synchronized (this.list) {
            downloadTaskAdapter.setAttachKeyDefault();
            downloadTaskAdapter.markAdded2List();
            this.list.add(downloadTaskAdapter);
            Util.m13741d(TAG, "add independent task: " + downloadTaskAdapter.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<DownloadTaskAdapter> assembleTasksToStart(FileDownloadListener fileDownloadListener) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.list) {
            Iterator<DownloadTaskAdapter> it = this.list.iterator();
            while (it.hasNext()) {
                DownloadTaskAdapter next = it.next();
                if (next.getListener() == fileDownloadListener && !next.isAttached()) {
                    next.setAttachKeyByQueue(fileDownloadListener.hashCode());
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }
}
