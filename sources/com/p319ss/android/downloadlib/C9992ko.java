package com.p319ss.android.downloadlib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.config.OnItemClickListener;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb;
import com.p319ss.android.downloadlib.addownload.C9878h;
import com.p319ss.android.downloadlib.addownload.InterfaceC9938u;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.ss.android.downloadlib.ko */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9992ko {

    /* renamed from: mb */
    private static volatile C9992ko f19246mb;

    /* renamed from: u */
    private long f19251u;

    /* renamed from: b */
    private final List<InterfaceC9938u> f19247b = new CopyOnWriteArrayList();

    /* renamed from: hj */
    private final Map<String, InterfaceC9938u> f19249hj = new ConcurrentHashMap();

    /* renamed from: h */
    private final CopyOnWriteArrayList<Object> f19248h = new CopyOnWriteArrayList<>();

    /* renamed from: ox */
    private final Handler f19250ox = new Handler(Looper.getMainLooper());

    private C9992ko() {
    }

    /* renamed from: mb */
    public static C9992ko m7236mb() {
        if (f19246mb == null) {
            synchronized (C9992ko.class) {
                if (f19246mb == null) {
                    f19246mb = new C9992ko();
                }
            }
        }
        return f19246mb;
    }

    /* renamed from: mb */
    public void m7235mb(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (downloadModel == null || TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            return;
        }
        InterfaceC9938u interfaceC9938u = this.f19249hj.get(downloadModel.getDownloadUrl());
        if (interfaceC9938u != null) {
            interfaceC9938u.mo7392ox(context).mo7393ox(i, downloadStatusChangeListener).mo7389ox(downloadModel).mo7401mb();
        } else if (!this.f19247b.isEmpty()) {
            m7221ox(context, i, downloadStatusChangeListener, downloadModel);
        } else {
            m7238b(context, i, downloadStatusChangeListener, downloadModel);
        }
    }

    /* renamed from: mb */
    public C9878h m7228mb(String str) {
        Map<String, InterfaceC9938u> map = this.f19249hj;
        if (map == null || map.size() == 0 || TextUtils.isEmpty(str)) {
            return null;
        }
        InterfaceC9938u interfaceC9938u = this.f19249hj.get(str);
        if (interfaceC9938u instanceof C9878h) {
            return (C9878h) interfaceC9938u;
        }
        return null;
    }

    /* renamed from: ox */
    private synchronized void m7221ox(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (this.f19247b.size() <= 0) {
            m7238b(context, i, downloadStatusChangeListener, downloadModel);
        } else {
            InterfaceC9938u remove = this.f19247b.remove(0);
            remove.mo7392ox(context).mo7393ox(i, downloadStatusChangeListener).mo7389ox(downloadModel).mo7401mb();
            this.f19249hj.put(downloadModel.getDownloadUrl(), remove);
        }
    }

    /* renamed from: b */
    private void m7238b(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (downloadModel == null) {
            return;
        }
        C9878h c9878h = new C9878h();
        c9878h.mo7392ox(context).mo7393ox(i, downloadStatusChangeListener).mo7389ox(downloadModel).mo7401mb();
        this.f19249hj.put(downloadModel.getDownloadUrl(), c9878h);
    }

    /* renamed from: mb */
    public void m7227mb(String str, int i) {
        InterfaceC9938u interfaceC9938u;
        if (TextUtils.isEmpty(str) || (interfaceC9938u = this.f19249hj.get(str)) == null) {
            return;
        }
        if (interfaceC9938u.mo7400mb(i)) {
            this.f19247b.add(interfaceC9938u);
            this.f19249hj.remove(str);
        }
        m7239b();
    }

    /* renamed from: mb */
    public void m7223mb(String str, boolean z) {
        InterfaceC9938u interfaceC9938u;
        if (TextUtils.isEmpty(str) || (interfaceC9938u = this.f19249hj.get(str)) == null) {
            return;
        }
        interfaceC9938u.mo7396mb(z);
    }

    /* renamed from: mb */
    public void m7226mb(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        m7224mb(str, j, i, downloadEventConfig, downloadController, null, null);
    }

    /* renamed from: mb */
    public void m7225mb(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener) {
        m7224mb(str, j, i, downloadEventConfig, downloadController, null, iDownloadButtonClickListener);
    }

    /* renamed from: mb */
    public void m7224mb(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, OnItemClickListener onItemClickListener, IDownloadButtonClickListener iDownloadButtonClickListener) {
        InterfaceC9938u interfaceC9938u;
        if (TextUtils.isEmpty(str) || (interfaceC9938u = this.f19249hj.get(str)) == null) {
            return;
        }
        interfaceC9938u.mo7399mb(j).mo7390ox(downloadEventConfig).mo7391ox(downloadController).mo7397mb(onItemClickListener).mo7398mb(iDownloadButtonClickListener).mo7394ox(i);
    }

    /* renamed from: mb */
    public void m7233mb(InterfaceC9817mb interfaceC9817mb) {
        if (interfaceC9817mb != null) {
            if (DownloadSetting.obtainGlobal().optBugFix("fix_listener_oom", false)) {
                this.f19248h.add(new SoftReference(interfaceC9817mb));
            } else {
                this.f19248h.add(interfaceC9817mb);
            }
        }
    }

    /* renamed from: b */
    private void m7239b() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f19251u < 300000) {
            return;
        }
        this.f19251u = currentTimeMillis;
        if (this.f19247b.isEmpty()) {
            return;
        }
        m7237hj();
    }

    /* renamed from: hj */
    private void m7237hj() {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (InterfaceC9938u interfaceC9938u : this.f19247b) {
            if (!interfaceC9938u.mo7395ox() && currentTimeMillis - interfaceC9938u.mo7402hj() > 300000) {
                interfaceC9938u.mo7388ww();
                arrayList.add(interfaceC9938u);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.f19247b.removeAll(arrayList);
    }

    /* renamed from: mb */
    public void m7234mb(final DownloadModel downloadModel, @Nullable final DownloadController downloadController, @Nullable final DownloadEventConfig downloadEventConfig) {
        this.f19250ox.post(new Runnable() { // from class: com.ss.android.downloadlib.ko.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = C9992ko.this.f19248h.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof InterfaceC9817mb) {
                        ((InterfaceC9817mb) next).mo7910mb(downloadModel, downloadController, downloadEventConfig);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof InterfaceC9817mb) {
                            ((InterfaceC9817mb) softReference.get()).mo7910mb(downloadModel, downloadController, downloadEventConfig);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: mb */
    public void m7230mb(final DownloadInfo downloadInfo, final BaseException baseException, final String str) {
        this.f19250ox.post(new Runnable() { // from class: com.ss.android.downloadlib.ko.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = C9992ko.this.f19248h.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof InterfaceC9817mb) {
                        ((InterfaceC9817mb) next).mo7908mb(downloadInfo, baseException, str);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof InterfaceC9817mb) {
                            ((InterfaceC9817mb) softReference.get()).mo7908mb(downloadInfo, baseException, str);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: mb */
    public void m7229mb(final DownloadInfo downloadInfo, final String str) {
        this.f19250ox.post(new Runnable() { // from class: com.ss.android.downloadlib.ko.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = C9992ko.this.f19248h.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof InterfaceC9817mb) {
                        ((InterfaceC9817mb) next).mo7907mb(downloadInfo, str);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof InterfaceC9817mb) {
                            ((InterfaceC9817mb) softReference.get()).mo7907mb(downloadInfo, str);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: ox */
    public void m7220ox(final DownloadInfo downloadInfo, final String str) {
        this.f19250ox.post(new Runnable() { // from class: com.ss.android.downloadlib.ko.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = C9992ko.this.f19248h.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof InterfaceC9817mb) {
                        ((InterfaceC9817mb) next).mo7906ox(downloadInfo, str);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof InterfaceC9817mb) {
                            ((InterfaceC9817mb) softReference.get()).mo7906ox(downloadInfo, str);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: mb */
    public void m7231mb(final DownloadInfo downloadInfo) {
        this.f19250ox.post(new Runnable() { // from class: com.ss.android.downloadlib.ko.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = C9992ko.this.f19248h.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof InterfaceC9817mb) {
                        ((InterfaceC9817mb) next).mo7909mb(downloadInfo);
                    } else if (next instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) next;
                        if (softReference.get() instanceof InterfaceC9817mb) {
                            ((InterfaceC9817mb) softReference.get()).mo7909mb(downloadInfo);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: ox */
    public Handler m7222ox() {
        return this.f19250ox;
    }
}
