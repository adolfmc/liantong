package com.p319ss.android.downloadlib.p334ox;

import android.os.Build;
import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.guide.install.InterfaceC9977mb;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.logger.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.ox.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10024b {
    /* renamed from: mb */
    public static void m7136mb(final C9837ox c9837ox, @NonNull final InterfaceC9977mb interfaceC9977mb) {
        boolean isAppForeground = AppStatusManager.getInstance().isAppForeground();
        if (!isAppForeground && Build.VERSION.SDK_INT >= 29) {
            C10050jb.m7034ox();
        }
        boolean isAppForeground2 = AppStatusManager.getInstance().isAppForeground();
        if (!isAppForeground && isAppForeground2 && c9837ox != null) {
            c9837ox.m7791je(true);
        }
        interfaceC9977mb.mo7240mb();
        Logger.m6475d("AppInstallOptimiseHelper", "AppInstallOptimiseHelper-->isAppForegroundSecond:::" + isAppForeground2);
        if (isAppForeground2) {
            return;
        }
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.ox.b.1
            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                Logger.m6475d("AppInstallOptimiseHelper", "AppInstallOptimiseHelper-->onAppForeground");
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                if (C10050jb.m7031ox(C9837ox.this)) {
                    return;
                }
                C9837ox.this.m7765nk(true);
                AdEventHandler.m7315mb().m7303mb("install_delay_invoke", C9837ox.this);
                interfaceC9977mb.mo7240mb();
            }
        });
    }
}
