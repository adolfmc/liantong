package com.p319ss.android.socialbase.appdownloader;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Queue;

/* renamed from: com.ss.android.socialbase.appdownloader.ww */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10174ww {

    /* renamed from: b */
    private long f19680b;

    /* renamed from: h */
    private SoftReference<JumpUnknownSourceActivity> f19681h;

    /* renamed from: hj */
    private long f19682hj;

    /* renamed from: ko */
    private Runnable f19683ko;

    /* renamed from: mb */
    private final Queue<Integer> f19684mb;

    /* renamed from: ox */
    private boolean f19685ox;

    /* renamed from: u */
    private Handler f19686u;

    private C10174ww() {
        this.f19684mb = new ArrayDeque();
        this.f19685ox = false;
        this.f19686u = new Handler(Looper.getMainLooper());
        this.f19683ko = new Runnable() { // from class: com.ss.android.socialbase.appdownloader.ww.1
            @Override // java.lang.Runnable
            public void run() {
                C10174ww.this.m6492b();
            }
        };
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.socialbase.appdownloader.ww.2
            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                if (C10174ww.this.f19684mb.isEmpty()) {
                    return;
                }
                long optLong = DownloadSetting.obtainGlobal().optLong("install_on_resume_install_interval", 120000L);
                long currentTimeMillis = System.currentTimeMillis() - C10174ww.this.f19682hj;
                if (currentTimeMillis < optLong) {
                    if (C10174ww.this.f19686u.hasCallbacks(C10174ww.this.f19683ko)) {
                        return;
                    }
                    C10174ww.this.f19686u.postDelayed(C10174ww.this.f19683ko, optLong - currentTimeMillis);
                    return;
                }
                C10174ww.this.f19682hj = System.currentTimeMillis();
                C10174ww.this.m6492b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m6492b() {
        final Integer poll;
        if (Build.VERSION.SDK_INT < 29 || AppStatusManager.getInstance().isAppForeground()) {
            synchronized (this.f19684mb) {
                poll = this.f19684mb.poll();
            }
            this.f19686u.removeCallbacks(this.f19683ko);
            if (poll != null) {
                final Context appContext = DownloadComponentManager.getAppContext();
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    this.f19686u.post(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.ww.3
                        @Override // java.lang.Runnable
                        public void run() {
                            C10174ww.this.m6479ox(appContext, poll.intValue(), false);
                        }
                    });
                } else {
                    m6479ox(appContext, poll.intValue(), false);
                }
                this.f19686u.postDelayed(this.f19683ko, 20000L);
                return;
            }
            this.f19685ox = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m6481mb(DownloadInfo downloadInfo, String str) {
        if (downloadInfo == null || TextUtils.isEmpty(str)) {
            return;
        }
        m6492b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.appdownloader.ww$mb */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10179mb {

        /* renamed from: mb */
        private static final C10174ww f19696mb = new C10174ww();
    }

    /* renamed from: mb */
    public static C10174ww m6487mb() {
        return C10179mb.f19696mb;
    }

    /* renamed from: mb */
    public int m6486mb(final Context context, final int i, final boolean z) {
        if (z) {
            return m6479ox(context, i, z);
        }
        if (m6489hj()) {
            this.f19686u.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.ww.4
                @Override // java.lang.Runnable
                public void run() {
                    C10174ww.this.m6486mb(context, i, z);
                }
            }, 1000L);
            return 1;
        } else if (AppStatusManager.getInstance().isAppForeground()) {
            Logger.m6469i("leaves", "on Foreground");
            return m6479ox(context, i, z);
        } else if (C10140ox.m6723mb()) {
            return 1;
        } else {
            boolean z2 = Build.VERSION.SDK_INT < 29;
            if (this.f19684mb.isEmpty() && !this.f19685ox && z2) {
                return m6479ox(context, i, z);
            }
            int optInt = DownloadSetting.obtainGlobal().optInt("install_queue_size", 3);
            synchronized (this.f19684mb) {
                while (this.f19684mb.size() > optInt) {
                    this.f19684mb.poll();
                }
            }
            if (z2) {
                this.f19686u.removeCallbacks(this.f19683ko);
                this.f19686u.postDelayed(this.f19683ko, DownloadSetting.obtain(i).optLong("install_queue_timeout", 20000L));
            }
            synchronized (this.f19684mb) {
                if (!this.f19684mb.contains(Integer.valueOf(i))) {
                    this.f19684mb.offer(Integer.valueOf(i));
                }
            }
            return 1;
        }
    }

    /* renamed from: hj */
    private boolean m6489hj() {
        return System.currentTimeMillis() - this.f19680b < 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public int m6479ox(Context context, int i, boolean z) {
        int m6895ox = C10085b.m6895ox(context, i, z);
        if (m6895ox == 1) {
            this.f19685ox = true;
        }
        this.f19680b = System.currentTimeMillis();
        return m6895ox;
    }

    /* renamed from: mb */
    public void m6485mb(JumpUnknownSourceActivity jumpUnknownSourceActivity) {
        this.f19681h = new SoftReference<>(jumpUnknownSourceActivity);
    }

    /* renamed from: ox */
    public JumpUnknownSourceActivity m6480ox() {
        SoftReference<JumpUnknownSourceActivity> softReference = this.f19681h;
        JumpUnknownSourceActivity jumpUnknownSourceActivity = softReference == null ? null : softReference.get();
        this.f19681h = null;
        return jumpUnknownSourceActivity;
    }
}
