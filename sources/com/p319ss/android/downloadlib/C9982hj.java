package com.p319ss.android.downloadlib;

import android.content.SharedPreferences;
import android.util.SparseArray;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.p319ss.android.socialbase.downloader.impls.DefaultDownloadCache;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.thread.DefaultThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.ss.android.downloadlib.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9982hj {

    /* renamed from: b */
    private ScheduledExecutorService f19228b;

    /* renamed from: mb */
    private ExecutorService f19229mb;

    /* renamed from: ox */
    private ExecutorService f19230ox;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.hj$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9984mb {

        /* renamed from: mb */
        private static C9982hj f19232mb = new C9982hj();
    }

    /* renamed from: mb */
    public static C9982hj m7254mb() {
        return C9984mb.f19232mb;
    }

    private C9982hj() {
    }

    /* renamed from: mb */
    public void m7253mb(Runnable runnable) {
        m7251mb(runnable, false);
    }

    /* renamed from: ox */
    public void m7249ox(Runnable runnable) {
        m7248ox(runnable, false);
    }

    /* renamed from: mb */
    public void m7251mb(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !C10050jb.m7057mb()) {
            runnable.run();
        } else {
            m7250ox().execute(runnable);
        }
    }

    /* renamed from: ox */
    public void m7248ox(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !C10050jb.m7057mb()) {
            runnable.run();
        } else {
            m7257b().execute(runnable);
        }
    }

    /* renamed from: mb */
    public void m7252mb(Runnable runnable, long j) {
        try {
            m7255hj().schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: ox */
    public ExecutorService m7250ox() {
        if (this.f19229mb == null) {
            synchronized (C9982hj.class) {
                if (this.f19229mb == null) {
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    SynchronousQueue synchronousQueue = new SynchronousQueue();
                    this.f19229mb = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, timeUnit, synchronousQueue, new DefaultThreadFactory(C10071ww.class.getName() + "-CPUThreadPool"));
                }
            }
        }
        return this.f19229mb;
    }

    /* renamed from: b */
    public ExecutorService m7257b() {
        if (this.f19230ox == null) {
            synchronized (C9982hj.class) {
                if (this.f19230ox == null) {
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    SynchronousQueue synchronousQueue = new SynchronousQueue();
                    this.f19230ox = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, timeUnit, synchronousQueue, new DefaultThreadFactory(C10071ww.class.getName() + "-IOThreadPool"));
                }
            }
        }
        return this.f19230ox;
    }

    /* renamed from: hj */
    public ScheduledExecutorService m7255hj() {
        if (this.f19228b == null) {
            synchronized (C9982hj.class) {
                if (this.f19228b == null) {
                    this.f19228b = new ScheduledThreadPoolExecutor(0, new DefaultThreadFactory(C10071ww.class.getName() + "-ScheduledThreadPool"));
                }
            }
        }
        return this.f19228b;
    }

    /* renamed from: h */
    public void m7256h() {
        m7253mb(new Runnable() { // from class: com.ss.android.downloadlib.hj.1
            @Override // java.lang.Runnable
            public void run() {
                IDownloadCache downloadCache;
                synchronized (C9982hj.class) {
                    try {
                        for (String str : new String[]{"sp_ad_download_event", "sp_download_finish_cache", "sp_delay_operation_info", "sp_ttdownloader_md5", "sp_name_installed_app", "misc_config", "sp_ad_install_back_dialog", "sp_ttdownloader_clean", "sp_order_download", "sp_a_b_c", "sp_ah_config", "sp_download_info", "sp_appdownloader"}) {
                            SharedPreferences sharedPreferences = C9940x.getContext().getSharedPreferences(str, 0);
                            if (sharedPreferences != null) {
                                sharedPreferences.edit().clear().apply();
                            }
                        }
                        downloadCache = DownloadComponentManager.getDownloadCache();
                    } catch (Throwable unused) {
                    }
                    if (downloadCache instanceof DefaultDownloadCache) {
                        SparseArray<DownloadInfo> downloadInfoMap = ((DefaultDownloadCache) downloadCache).getDownloadCache().getDownloadInfoMap();
                        for (int size = downloadInfoMap.size() - 1; size >= 0; size--) {
                            DownloadInfo downloadInfo = downloadInfoMap.get(downloadInfoMap.keyAt(size));
                            if (downloadInfo != null) {
                                Downloader.getInstance(C9940x.getContext()).clearDownloadData(downloadInfo.getId());
                            }
                        }
                    }
                }
            }
        });
    }
}
