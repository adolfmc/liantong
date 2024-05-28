package com.p319ss.android.socialbase.downloader.impls;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceConnectionListener;
import com.p319ss.android.socialbase.downloader.downloader.IndependentProcessDownloadService;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import com.p319ss.android.socialbase.downloader.utils.IPCUtils;
import java.util.List;

/* renamed from: com.ss.android.socialbase.downloader.impls.IndependentDownloadServiceHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class IndependentDownloadServiceHandler extends AbsDownloadServiceHandler implements ServiceConnection {
    private static final String TAG = "IndependentDownloadServiceHandler";
    private IDownloadAidlService aidlService;
    private IDownloadServiceConnectionListener connectionListener;
    private int logLevel = -1;

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler
    public void startService(Context context, ServiceConnection serviceConnection) {
        try {
            Logger.m6475d(TAG, "bindService");
            Intent intent = new Intent(context, IndependentProcessDownloadService.class);
            if (DownloadUtils.isMainProcess()) {
                intent.putExtra("fix_downloader_db_sigbus", DownloadSetting.obtainGlobal().optBugFix("fix_sigbus_downloader_db"));
            }
            if (serviceConnection != null) {
                context.bindService(intent, serviceConnection, 1);
            }
            context.startService(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler
    public void stopService(Context context, ServiceConnection serviceConnection) {
        Logger.m6475d(TAG, "stopService");
        this.isServiceAlive = false;
        Intent intent = new Intent(context, IndependentProcessDownloadService.class);
        if (serviceConnection != null) {
            context.unbindService(serviceConnection);
        }
        context.stopService(intent);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public IBinder onBind(Intent intent) {
        if (intent != null && intent.getBooleanExtra("fix_downloader_db_sigbus", false)) {
            Log.w(TAG, "downloader process sync database on main process!");
            DownloadSetting.setGlobalBugFix("fix_sigbus_downloader_db", true);
        }
        Logger.m6475d(TAG, "onBind IndependentDownloadBinder");
        return new IndependentDownloadBinder();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void tryDownload(DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("tryDownload aidlService == null:");
        sb.append(this.aidlService == null);
        Logger.m6475d(str, sb.toString());
        if (this.aidlService == null) {
            pendDownloadTask(downloadTask);
            startService(DownloadComponentManager.getAppContext(), this);
            return;
        }
        resumePendingTaskForIndependent();
        try {
            this.aidlService.tryDownload(IPCUtils.convertDownloadTaskToAidl(downloadTask));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void resumePendingTaskForIndependent() {
        SparseArray<List<DownloadTask>> clone;
        try {
            synchronized (this.pendingTasks) {
                clone = this.pendingTasks.clone();
                this.pendingTasks.clear();
            }
            if (clone == null || clone.size() <= 0 || DownloadComponentManager.getDownloadEngine() == null) {
                return;
            }
            for (int i = 0; i < clone.size(); i++) {
                List<DownloadTask> list = clone.get(clone.keyAt(i));
                if (list != null) {
                    for (DownloadTask downloadTask : list) {
                        try {
                            this.aidlService.tryDownload(IPCUtils.convertDownloadTaskToAidl(downloadTask));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th) {
            Logger.m6471e(TAG, "resumePendingTaskForIndependent failed", th);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void tryDownloadWithEngine(DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        DownloadProcessDispatcher.getInstance().setDownloadWithIndependentProcessStatus(downloadTask.getDownloadId(), true);
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            downloadEngine.tryDownload(downloadTask);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Logger.m6475d(TAG, "onServiceConnected ");
        this.aidlService = IDownloadAidlService.Stub.asInterface(iBinder);
        IDownloadServiceConnectionListener iDownloadServiceConnectionListener = this.connectionListener;
        if (iDownloadServiceConnectionListener != null) {
            iDownloadServiceConnectionListener.onServiceConnection(iBinder);
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onServiceConnected aidlService!=null");
        sb.append(this.aidlService != null);
        sb.append(" pendingTasks.size:");
        sb.append(this.pendingTasks.size());
        Logger.m6475d(str, sb.toString());
        if (this.aidlService != null) {
            DownloadProcessDispatcher.getInstance().dispatchDownloaderProcessConnectedEvent();
            this.isServiceAlive = true;
            this.isInvokeStartService = false;
            int i = this.logLevel;
            if (i != -1) {
                try {
                    this.aidlService.setLogLevel(i);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            if (this.aidlService != null) {
                resumePendingTaskForIndependent();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Logger.m6475d(TAG, "onServiceDisconnected ");
        this.aidlService = null;
        this.isServiceAlive = false;
        IDownloadServiceConnectionListener iDownloadServiceConnectionListener = this.connectionListener;
        if (iDownloadServiceConnectionListener != null) {
            iDownloadServiceConnectionListener.onServiceDisConnection();
        }
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        this.aidlService = null;
        IDownloadServiceConnectionListener iDownloadServiceConnectionListener = this.connectionListener;
        if (iDownloadServiceConnectionListener != null) {
            iDownloadServiceConnectionListener.onServiceDisConnection();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void setServiceConnectionListener(IDownloadServiceConnectionListener iDownloadServiceConnectionListener) {
        this.connectionListener = iDownloadServiceConnectionListener;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void setLogLevel(int i) {
        IDownloadAidlService iDownloadAidlService = this.aidlService;
        if (iDownloadAidlService == null) {
            this.logLevel = i;
            return;
        }
        try {
            iDownloadAidlService.setLogLevel(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.AbsDownloadServiceHandler, com.p319ss.android.socialbase.downloader.downloader.IDownloadServiceHandler
    public void startService() {
        if (this.aidlService == null) {
            startService(DownloadComponentManager.getAppContext(), this);
        }
    }
}
