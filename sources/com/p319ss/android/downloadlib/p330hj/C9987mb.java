package com.p319ss.android.downloadlib.p330hj;

import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.guide.install.InterfaceC9977mb;
import com.p319ss.android.downloadlib.p334ox.C10024b;
import com.p319ss.android.downloadlib.p334ox.C10031lz;
import com.p319ss.android.downloadlib.p334ox.C10039u;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10094lz;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.downloadlib.hj.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9987mb implements InterfaceC10101x {
    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x
    /* renamed from: mb */
    public void mo6868mb(DownloadInfo downloadInfo, final InterfaceC10094lz interfaceC10094lz) {
        m7242mb(downloadInfo, new InterfaceC9977mb() { // from class: com.ss.android.downloadlib.hj.mb.1
            @Override // com.p319ss.android.downloadlib.guide.install.InterfaceC9977mb
            /* renamed from: mb */
            public void mo7240mb() {
                interfaceC10094lz.mo6876mb();
            }
        });
    }

    /* renamed from: mb */
    public void m7242mb(final DownloadInfo downloadInfo, @NonNull final InterfaceC9977mb interfaceC9977mb) {
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb != null && C10031lz.m7128mb(m7442mb)) {
            TTDelegateActivity.m7720mb(m7442mb, new InterfaceC9977mb() { // from class: com.ss.android.downloadlib.hj.mb.2
                @Override // com.p319ss.android.downloadlib.guide.install.InterfaceC9977mb
                /* renamed from: mb */
                public void mo7240mb() {
                    C9987mb.this.m7241ox(downloadInfo, interfaceC9977mb);
                }
            });
        } else {
            m7241ox(downloadInfo, interfaceC9977mb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public void m7241ox(DownloadInfo downloadInfo, @NonNull final InterfaceC9977mb interfaceC9977mb) {
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        boolean m7108mb = C10039u.m7108mb(m7442mb);
        boolean m7105ox = C10039u.m7105ox(m7442mb);
        if (!m7108mb || !m7105ox) {
            interfaceC9977mb.mo7240mb();
        } else {
            C10024b.m7136mb(m7442mb, new InterfaceC9977mb() { // from class: com.ss.android.downloadlib.hj.mb.3
                @Override // com.p319ss.android.downloadlib.guide.install.InterfaceC9977mb
                /* renamed from: mb */
                public void mo7240mb() {
                    interfaceC9977mb.mo7240mb();
                }
            });
        }
    }
}
