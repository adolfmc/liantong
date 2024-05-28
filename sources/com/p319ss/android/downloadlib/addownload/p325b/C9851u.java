package com.p319ss.android.downloadlib.addownload.p325b;

import android.content.Context;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C10071ww;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.ss.android.downloadlib.addownload.b.u */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9851u {

    /* renamed from: mb */
    private static C9851u f18923mb;

    /* renamed from: ox */
    private List<InterfaceC9845hj> f18924ox = new ArrayList();

    /* renamed from: mb */
    public static C9851u m7689mb() {
        if (f18923mb == null) {
            synchronized (C9851u.class) {
                if (f18923mb == null) {
                    f18923mb = new C9851u();
                }
            }
        }
        return f18923mb;
    }

    private C9851u() {
        this.f18924ox.add(new C9844h());
        this.f18924ox.add(new C9846ko());
        this.f18924ox.add(new C9849ox());
        this.f18924ox.add(new C9847mb());
    }

    /* renamed from: mb */
    public void m7688mb(C9837ox c9837ox, int i, InterfaceC9843b interfaceC9843b) {
        List<InterfaceC9845hj> list = this.f18924ox;
        if (list == null || list.size() == 0 || c9837ox == null) {
            interfaceC9843b.mo7599mb(c9837ox);
            return;
        }
        DownloadInfo m6947ox = C10071ww.m6960mb((Context) null).m6947ox(c9837ox.mo7478mb());
        if (m6947ox == null || !"application/vnd.android.package-archive".equals(m6947ox.getMimeType())) {
            interfaceC9843b.mo7599mb(c9837ox);
            return;
        }
        boolean z = DownloadSetting.obtain(c9837ox.mo7479m()).optInt("pause_optimise_switch", 0) == 1;
        for (InterfaceC9845hj interfaceC9845hj : this.f18924ox) {
            if (z || (interfaceC9845hj instanceof C9846ko)) {
                if (interfaceC9845hj.mo7691mb(c9837ox, i, interfaceC9843b)) {
                    return;
                }
            }
        }
        interfaceC9843b.mo7599mb(c9837ox);
    }
}
