package com.p319ss.android.downloadlib.addownload;

import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadlib.C9992ko;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.p334ox.C10026h;
import com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.ss.android.downloadlib.addownload.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9841b {

    /* renamed from: mb */
    private static String f18908mb = "b";

    /* renamed from: ox */
    private static volatile C9841b f18909ox;

    /* renamed from: b */
    private ConcurrentHashMap<Long, Runnable> f18910b;

    public C9841b() {
        this.f18910b = null;
        this.f18910b = new ConcurrentHashMap<>();
    }

    /* renamed from: mb */
    public static C9841b m7707mb() {
        if (f18909ox == null) {
            synchronized (C9841b.class) {
                if (f18909ox == null) {
                    f18909ox = new C9841b();
                }
            }
        }
        return f18909ox;
    }

    /* renamed from: mb */
    public void m7704mb(C9878h c9878h, boolean z, int i, DownloadModel downloadModel) {
        if (downloadModel instanceof AdDownloadModel) {
            ((AdDownloadModel) downloadModel).setFunnelType(3);
        }
        long id = downloadModel.getId();
        if (i == 7) {
            Runnable remove = this.f18910b.remove(Long.valueOf(id));
            if (z) {
                AdEventHandler.m7315mb().m7314mb(id, 1);
                m7706mb(id, true, 1);
                return;
            }
            if (remove != null) {
                C9992ko.m7236mb().m7222ox().post(remove);
            }
            m7706mb(id, false, 1);
            return;
        }
        switch (i) {
            case 4:
                if (!z) {
                    m7706mb(id, false, 2);
                    c9878h.m7606ox(false);
                    return;
                }
                m7706mb(id, true, 2);
                return;
            case 5:
                if (!z) {
                    m7706mb(id, false, 1);
                    c9878h.m7638b(false);
                    return;
                }
                m7706mb(id, true, 1);
                return;
            default:
                return;
        }
    }

    /* renamed from: mb */
    private void m7706mb(long j, boolean z, int i) {
        AdEventHandler.m7315mb().m7311mb(j, z, i);
        if (z) {
            C9940x.m7373gm().mo7340mb(null, null, null, null, null, 3);
        }
    }

    /* renamed from: mb */
    public void m7705mb(final C9878h c9878h, final int i, final DownloadModel downloadModel) {
        C10026h.m7135mb().m7132mb(new InterfaceC10029hj() { // from class: com.ss.android.downloadlib.addownload.b.1
            @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj
            /* renamed from: mb */
            public void mo7115mb(boolean z) {
                C9841b.this.m7704mb(c9878h, z, i, downloadModel);
            }
        }, m7702ox());
    }

    /* renamed from: ox */
    public long m7702ox() {
        return C9940x.m7364lz().optLong("quick_app_check_internal", 1200L);
    }

    /* renamed from: mb */
    public static boolean m7703mb(DownloadInfo downloadInfo) {
        return downloadInfo == null || downloadInfo.getStatus() == 0 || downloadInfo.getStatus() == -4;
    }
}
