package com.p319ss.android.downloadlib.p330hj;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10094lz;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* renamed from: com.ss.android.downloadlib.hj.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9991ox implements InterfaceC10101x {
    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x
    /* renamed from: mb */
    public void mo6868mb(DownloadInfo downloadInfo, InterfaceC10094lz interfaceC10094lz) {
        C9837ox m7442mb;
        if (downloadInfo != null && (m7442mb = C9923u.m7451mb().m7442mb(downloadInfo)) != null) {
            downloadInfo.setLinkMode(m7442mb.m7730yr());
        }
        if (interfaceC10094lz != null) {
            interfaceC10094lz.mo6876mb();
        }
    }
}
