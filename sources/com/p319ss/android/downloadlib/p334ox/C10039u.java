package com.p319ss.android.downloadlib.p334ox;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.ox.u */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10039u {

    /* renamed from: mb */
    private static Handler f19361mb = new Handler(Looper.getMainLooper());

    /* renamed from: mb */
    public static void m7106mb(final C9837ox c9837ox, @NonNull final InterfaceC10043ww interfaceC10043ww) {
        boolean isAppForeground = AppStatusManager.getInstance().isAppForeground();
        if (!isAppForeground && Build.VERSION.SDK_INT >= 29) {
            C10050jb.m7034ox();
        }
        boolean isAppForeground2 = AppStatusManager.getInstance().isAppForeground();
        boolean z = !isAppForeground && isAppForeground2;
        if (c9837ox != null) {
            c9837ox.m7791je(z);
        }
        interfaceC10043ww.mo7100mb(z);
        if (c9837ox == null) {
            return;
        }
        m7104ox(c9837ox, m7101x(c9837ox));
        if (isAppForeground2) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.ox.u.1
            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                C9982hj.m7254mb().m7253mb(new Runnable() { // from class: com.ss.android.downloadlib.ox.u.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean m7062b = C10050jb.m7062b(C9837ox.this.mo7489h());
                        long m7112h = C10039u.m7112h(C9837ox.this);
                        if (!m7062b || m7112h >= System.currentTimeMillis() - currentTimeMillis) {
                            if (System.currentTimeMillis() - currentTimeMillis > C10039u.m7102ww(C9837ox.this)) {
                                AdEventHandler.m7315mb().m7303mb("deeplink_delay_timeout", C9837ox.this);
                                return;
                            }
                            C9837ox.this.m7791je(true);
                            AdEventHandler.m7315mb().m7303mb("deeplink_delay_invoke", C9837ox.this);
                            interfaceC10043ww.mo7100mb(true);
                            C10039u.m7104ox(C9837ox.this, C10039u.m7101x(C9837ox.this));
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m7104ox(@NonNull final C9837ox c9837ox, final int i) {
        if (i <= 0) {
            return;
        }
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.ox.u.2
            @Override // java.lang.Runnable
            public void run() {
                int i2 = 1;
                if (C10050jb.m7062b(C9837ox.this.mo7489h())) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (!C9837ox.this.m7743up()) {
                            i2 = 2;
                        }
                        jSONObject.putOpt("deeplink_source", Integer.valueOf(i2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    AdEventHandler.m7315mb().m7298mb("deeplink_success_2", jSONObject, C9837ox.this);
                    return;
                }
                C10039u.m7104ox(C9837ox.this, i - 1);
            }
        }, m7109lz(c9837ox) * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ww */
    public static long m7102ww(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optLong("app_link_check_timeout", 300000L);
    }

    /* renamed from: lz */
    private static int m7109lz(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optInt("app_link_check_delay", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x */
    public static int m7101x(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optInt("app_link_check_count", 10);
    }

    /* renamed from: mb */
    public static boolean m7108mb(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optInt("app_link_opt_switch") == 1;
    }

    /* renamed from: ox */
    public static boolean m7105ox(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optInt("app_link_opt_install_switch") == 1;
    }

    /* renamed from: b */
    public static boolean m7113b(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optInt("app_link_opt_invoke_switch") == 1;
    }

    /* renamed from: hj */
    public static boolean m7111hj(C9837ox c9837ox) {
        return C10049hj.m7071mb(c9837ox).optInt("app_link_opt_dialog_switch") == 1;
    }

    /* renamed from: h */
    public static long m7112h(C9837ox c9837ox) {
        if (c9837ox == null) {
            return 3000L;
        }
        return C10049hj.m7071mb(c9837ox).optInt("app_link_opt_back_time_limit", 3) * 1000;
    }
}
