package com.p319ss.android.downloadlib;

import android.content.Context;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import com.p319ss.android.download.api.InterfaceC9819mb;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.config.InterfaceC9810u;
import com.p319ss.android.download.api.config.OnItemClickListener;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb;
import com.p319ss.android.downloadad.api.InterfaceC9835mb;
import com.p319ss.android.downloadad.api.InterfaceC9838ox;
import com.p319ss.android.downloadlib.addownload.C9895jb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.exception.C9974ox;
import com.p319ss.android.downloadlib.p329b.C9951h;
import com.p319ss.android.downloadlib.p329b.C9952hj;
import com.p319ss.android.downloadlib.p329b.C9954ko;
import com.p319ss.android.downloadlib.p329b.C9961u;
import com.p319ss.android.downloadlib.p330hj.C9985b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.downloadlib.ww */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C10071ww {

    /* renamed from: mb */
    private static volatile C10071ww f19400mb;

    /* renamed from: b */
    private final C9992ko f19401b;

    /* renamed from: h */
    private InterfaceC9838ox f19402h;

    /* renamed from: hj */
    private final InterfaceC9835mb f19403hj;

    /* renamed from: ox */
    private final InterfaceC9819mb f19404ox;

    /* renamed from: u */
    private long f19405u;

    /* renamed from: mb */
    public static C10071ww m6960mb(final Context context) {
        if (f19400mb == null) {
            synchronized (C10071ww.class) {
                if (f19400mb == null) {
                    C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.1
                        @Override // java.lang.Runnable
                        public void run() {
                            C10071ww unused = C10071ww.f19400mb = new C10071ww(context);
                        }
                    });
                }
            }
        }
        return f19400mb;
    }

    private C10071ww(Context context) {
        this.f19401b = C9992ko.m7236mb();
        this.f19404ox = new C9978h();
        this.f19405u = System.currentTimeMillis();
        m6949ox(context);
        this.f19403hj = C9998mb.m7215mb();
    }

    /* renamed from: ox */
    private void m6949ox(Context context) {
        C9940x.m7361mb(context);
        Downloader.getInstance(C9940x.getContext());
        C9923u.m7451mb().m7438ox();
        C10112hj.m6786x().m6809mb(C9940x.getContext(), "misc_config", new C9954ko(), new C9961u(context), new C9946b());
        C9952hj c9952hj = new C9952hj();
        C10112hj.m6786x().m6806mb(c9952hj);
        Downloader.getInstance(context).registerDownloadCacheSyncListener(c9952hj);
        C10112hj.m6786x().m6799mb(new C9895jb());
        DownloadComponentManager.setDownloadEventListener(new C9951h());
        C10112hj.m6786x().m6805mb(C9985b.m7247mb());
    }

    /* renamed from: mb */
    public InterfaceC9819mb m6961mb() {
        return this.f19404ox;
    }

    /* renamed from: mb */
    public InterfaceC9819mb m6956mb(String str) {
        InterfaceC9810u m7097ox = C10045u.m7098mb().m7097ox();
        if (m7097ox != null && m7097ox.m7934mb(str)) {
            return m7097ox.m7933ox(str);
        }
        return this.f19404ox;
    }

    /* renamed from: ox */
    public long m6950ox() {
        return this.f19405u;
    }

    /* renamed from: b */
    public void m6965b() {
        this.f19405u = System.currentTimeMillis();
    }

    /* renamed from: hj */
    public InterfaceC9835mb m6963hj() {
        return this.f19403hj;
    }

    /* renamed from: h */
    public InterfaceC9838ox m6964h() {
        if (this.f19402h == null) {
            this.f19402h = C10020ox.m7160mb();
        }
        return this.f19402h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ww */
    public C9992ko m6945ww() {
        return this.f19401b;
    }

    @MainThread
    /* renamed from: mb */
    public void m6959mb(final Context context, final int i, final DownloadStatusChangeListener downloadStatusChangeListener, final DownloadModel downloadModel) {
        C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.4
            @Override // java.lang.Runnable
            public void run() {
                C10071ww.this.m6945ww().m7235mb(context, i, downloadStatusChangeListener, downloadModel);
            }
        });
    }

    @MainThread
    /* renamed from: mb */
    public void m6952mb(final String str, final long j, final int i, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final OnItemClickListener onItemClickListener, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.5
            @Override // java.lang.Runnable
            public void run() {
                C10071ww.this.m6945ww().m7224mb(str, j, i, downloadEventConfig, downloadController, onItemClickListener, iDownloadButtonClickListener);
            }
        });
    }

    @MainThread
    /* renamed from: mb */
    public void m6954mb(final String str, final long j, final int i, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController) {
        C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.6
            @Override // java.lang.Runnable
            public void run() {
                C10071ww.this.m6945ww().m7226mb(str, j, i, downloadEventConfig, downloadController);
            }
        });
    }

    @MainThread
    /* renamed from: mb */
    public void m6953mb(final String str, final long j, final int i, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.7
            @Override // java.lang.Runnable
            public void run() {
                C10071ww.this.m6945ww().m7225mb(str, j, i, downloadEventConfig, downloadController, iDownloadButtonClickListener);
            }
        });
    }

    @MainThread
    /* renamed from: mb */
    public void m6955mb(final String str, final int i) {
        C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.2
            @Override // java.lang.Runnable
            public void run() {
                C10071ww.this.m6945ww().m7227mb(str, i);
            }
        });
    }

    @MainThread
    /* renamed from: mb */
    public void m6951mb(final String str, final boolean z) {
        C9974ox.m7273mb(new Runnable() { // from class: com.ss.android.downloadlib.ww.3
            @Override // java.lang.Runnable
            public void run() {
                C10071ww.this.m6945ww().m7223mb(str, z);
            }
        });
    }

    /* renamed from: mb */
    public void m6958mb(InterfaceC9817mb interfaceC9817mb) {
        m6945ww().m7233mb(interfaceC9817mb);
    }

    /* renamed from: u */
    public String m6946u() {
        return C9940x.m7350nk();
    }

    /* renamed from: ko */
    public void m6962ko() {
        C9982hj.m7254mb().m7256h();
    }

    /* renamed from: ox */
    public DownloadInfo m6947ox(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return C10112hj.m6786x().m6810mb(C9940x.getContext(), str);
    }
}
