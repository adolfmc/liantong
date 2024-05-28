package com.p319ss.android.downloadlib.p330hj;

import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10094lz;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.ss.android.downloadlib.hj.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9985b implements InterfaceC10101x {

    /* renamed from: mb */
    private static volatile C9985b f19233mb;

    /* renamed from: ox */
    private List<InterfaceC10101x> f19234ox = new ArrayList();

    /* renamed from: mb */
    public static C9985b m7247mb() {
        if (f19233mb == null) {
            synchronized (C9985b.class) {
                if (f19233mb == null) {
                    f19233mb = new C9985b();
                }
            }
        }
        return f19233mb;
    }

    private C9985b() {
        this.f19234ox.add(new C9991ox());
        this.f19234ox.add(new C9987mb());
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x
    /* renamed from: mb */
    public void mo6868mb(DownloadInfo downloadInfo, InterfaceC10094lz interfaceC10094lz) {
        if (downloadInfo != null && this.f19234ox.size() != 0) {
            m7245mb(downloadInfo, 0, interfaceC10094lz);
        } else if (interfaceC10094lz != null) {
            interfaceC10094lz.mo6876mb();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m7245mb(final DownloadInfo downloadInfo, final int i, final InterfaceC10094lz interfaceC10094lz) {
        if (i == this.f19234ox.size() || i < 0) {
            interfaceC10094lz.mo6876mb();
        } else {
            this.f19234ox.get(i).mo6868mb(downloadInfo, new InterfaceC10094lz() { // from class: com.ss.android.downloadlib.hj.b.1
                @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10094lz
                /* renamed from: mb */
                public void mo6876mb() {
                    C9985b.this.m7245mb(downloadInfo, i + 1, interfaceC10094lz);
                }
            });
        }
    }
}
