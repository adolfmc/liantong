package com.p319ss.android.downloadlib.p334ox;

import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;

/* renamed from: com.ss.android.downloadlib.ox.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C10026h implements AppStatusManager.AppStatusChangeListener {

    /* renamed from: mb */
    private long f19345mb;

    @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppBackground() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.ox.h$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C10028mb {

        /* renamed from: mb */
        private static C10026h f19349mb = new C10026h();
    }

    /* renamed from: mb */
    public static C10026h m7135mb() {
        return C10028mb.f19349mb;
    }

    private C10026h() {
        this.f19345mb = 0L;
        AppStatusManager.getInstance().registerAppSwitchListener(this);
    }

    @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppForeground() {
        this.f19345mb = System.currentTimeMillis();
    }

    /* renamed from: mb */
    public void m7132mb(final InterfaceC10029hj interfaceC10029hj, final long j) {
        if (interfaceC10029hj == null) {
            return;
        }
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.ox.h.1
            @Override // java.lang.Runnable
            public void run() {
                if (!AppStatusManager.getInstance().isAppFocus() || System.currentTimeMillis() - C10026h.this.f19345mb <= j) {
                    interfaceC10029hj.mo7115mb(true);
                } else {
                    interfaceC10029hj.mo7115mb(false);
                }
            }
        }, j);
    }

    /* renamed from: mb */
    public void m7133mb(InterfaceC10029hj interfaceC10029hj) {
        m7132mb(interfaceC10029hj, 5000L);
    }

    /* renamed from: ox */
    public void m7131ox(InterfaceC10029hj interfaceC10029hj) {
        if (interfaceC10029hj == null) {
            return;
        }
        int optInt = C9940x.m7364lz().optInt("check_an_result_delay", 1200);
        m7132mb(interfaceC10029hj, optInt > 0 ? optInt : 1200);
    }
}
