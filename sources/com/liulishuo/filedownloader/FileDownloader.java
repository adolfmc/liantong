package com.liulishuo.filedownloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.liulishuo.okdownload.DownloadContext;
import com.liulishuo.okdownload.DownloadContextListener;
import com.liulishuo.okdownload.DownloadMonitor;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.connection.DownloadOkHttp3Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FileDownloader {
    private static final String TAG = "FileDownloader";

    @Deprecated
    public void addServiceConnectListener(FileDownloadConnectListener fileDownloadConnectListener) {
    }

    @Deprecated
    public void bindService() {
    }

    @Deprecated
    public boolean isServiceConnected() {
        return true;
    }

    @Deprecated
    public void removeServiceConnectListener(FileDownloadConnectListener fileDownloadConnectListener) {
    }

    @Deprecated
    public void unBindServiceIfIdle() {
    }

    @Deprecated
    public void unbindService() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class HolderClass {
        private static final FileDownloader INSTANCE = new FileDownloader();

        private HolderClass() {
        }
    }

    public static FileDownloader getImpl() {
        return HolderClass.INSTANCE;
    }

    public static void init(@NonNull Context context) {
        init(context, null);
    }

    public static void init(@NonNull Context context, @Nullable FileDownloadHelper.OkHttpClientCustomMaker okHttpClientCustomMaker) {
        init(context, okHttpClientCustomMaker, 0);
    }

    public static void init(@NonNull Context context, @Nullable FileDownloadHelper.OkHttpClientCustomMaker okHttpClientCustomMaker, int i) {
        setup(context);
        OkDownload.Builder builder = null;
        final OkHttpClient customMake = okHttpClientCustomMaker == null ? null : okHttpClientCustomMaker.customMake();
        if (customMake != null) {
            builder = new OkDownload.Builder(context);
            builder.connectionFactory(new DownloadConnection.Factory() { // from class: com.liulishuo.filedownloader.FileDownloader.1
                @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Factory
                public DownloadConnection create(String str) throws IOException {
                    Util.m13741d(FileDownloader.TAG, "create a okhttp connection with " + str);
                    return new DownloadOkHttp3Connection.Factory().setBuilder(OkHttpClient.this.newBuilder()).create(str);
                }
            });
        }
        DownloadMonitor downloadMonitor = FileDownloadMonitor.getDownloadMonitor();
        if (downloadMonitor != null) {
            if (builder == null) {
                builder = new OkDownload.Builder(context);
            }
            builder.monitor(downloadMonitor);
        }
        if (builder != null) {
            OkDownload.setSingletonInstance(builder.build());
        }
    }

    public static void setup(@NonNull Context context) {
        FileDownloadHelper.holdContext(context.getApplicationContext());
    }

    public DownloadTaskAdapter create(String str) {
        return new DownloadTaskAdapter(str);
    }

    public boolean start(FileDownloadListener fileDownloadListener, boolean z) {
        if (fileDownloadListener == null) {
            Util.m13738w(TAG, "Tasks with the listener can't start, because the listener provided is null: [null, " + z + "]");
            return false;
        }
        List<DownloadTaskAdapter> assembleTasksToStart = FileDownloadList.getImpl().assembleTasksToStart(fileDownloadListener);
        if (assembleTasksToStart.isEmpty()) {
            Util.m13738w(TAG, "no task for listener: " + fileDownloadListener + " to start");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (DownloadTaskAdapter downloadTaskAdapter : assembleTasksToStart) {
            arrayList.add(downloadTaskAdapter.getDownloadTask());
        }
        new DownloadContext.Builder(new DownloadContext.QueueSet(), arrayList).setListener(new DownloadContextListener() { // from class: com.liulishuo.filedownloader.FileDownloader.2
            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void taskEnd(@NonNull DownloadContext downloadContext, @NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, int i) {
                Util.m13741d(FileDownloader.TAG, "task " + downloadTask.getId() + "end");
                DownloadTaskAdapter findDownloadTaskAdapter = FileDownloadUtils.findDownloadTaskAdapter(downloadTask);
                if (findDownloadTaskAdapter != null) {
                    FileDownloadList.getImpl().remove(findDownloadTaskAdapter);
                }
            }

            @Override // com.liulishuo.okdownload.DownloadContextListener
            public void queueEnd(@NonNull DownloadContext downloadContext) {
                Util.m13741d(FileDownloader.TAG, "queue end");
            }
        }).build().start(CompatListenerAdapter.create(fileDownloadListener), z);
        return true;
    }

    public void pause(FileDownloadListener fileDownloadListener) {
        List<DownloadTaskAdapter> byFileDownloadListener = FileDownloadList.getImpl().getByFileDownloadListener(fileDownloadListener);
        DownloadTask[] downloadTaskArr = new DownloadTask[byFileDownloadListener.size()];
        for (int i = 0; i < byFileDownloadListener.size(); i++) {
            downloadTaskArr[i] = byFileDownloadListener.get(i).getDownloadTask();
        }
        OkDownload.with().downloadDispatcher().cancel(downloadTaskArr);
    }

    public void pauseAll() {
        OkDownload.with().downloadDispatcher().cancelAll();
    }

    public int pause(int i) {
        OkDownload.with().downloadDispatcher().cancel(i);
        return 0;
    }
}
