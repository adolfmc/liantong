package com.p319ss.android.downloadlib.p329b;

import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10048h;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.appdownloader.p336h.C10103b;
import com.p319ss.android.socialbase.appdownloader.p336h.C10106hj;
import com.p319ss.android.socialbase.appdownloader.p336h.C10110mb;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.b.ww */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9962ww {
    private C9962ww() {
    }

    /* renamed from: b */
    private void m7328b(@NonNull final C9837ox c9837ox, long j) {
        final int mo7479m = c9837ox.mo7479m();
        if (DownloadSetting.obtain(mo7479m).optInt("notification_opt_2") != 1) {
            return;
        }
        m7324mb(mo7479m);
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.b.ww.2
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(mo7479m);
                JSONObject jSONObject = new JSONObject();
                C10050jb.m7040mb(jSONObject, "ttdownloader_type", (Object) 2);
                C10048h.m7089b(downloadInfo, jSONObject);
                if (C10050jb.m7031ox(c9837ox)) {
                    C10050jb.m7040mb(jSONObject, "error_code", (Object) 1002);
                } else {
                    C9962ww.this.m7323mb(mo7479m, c9837ox, jSONObject);
                }
                AdEventHandler.m7315mb().m7293ox("download_notification_try_show", jSONObject, c9837ox);
            }
        }, j * 1000);
    }

    /* renamed from: mb */
    public static C9962ww m7325mb() {
        return C9966mb.f19212mb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m7323mb(int i, C9837ox c9837ox, JSONObject jSONObject) {
        if (!C10106hj.m6838mb()) {
            C10050jb.m7040mb(jSONObject, "error_code", (Object) 1004);
            return;
        }
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(i);
        if (downloadInfo == null) {
            C10050jb.m7040mb(jSONObject, "error_code", (Object) 1005);
            return;
        }
        if (DownloadNotificationManager.getInstance().getNotificationItem(i) != null) {
            DownloadNotificationManager.getInstance().cancelNotification(i);
        }
        C10110mb c10110mb = new C10110mb(C9940x.getContext(), i, downloadInfo.getTitle(), downloadInfo.getSavePath(), downloadInfo.getName(), downloadInfo.getExtra());
        c10110mb.setCurBytes(downloadInfo.getCurBytes());
        c10110mb.setTotalBytes(downloadInfo.getTotalBytes());
        c10110mb.refreshStatus(downloadInfo.getStatus(), null, false, false);
        DownloadNotificationManager.getInstance().addNotification(c10110mb);
        c10110mb.updateNotification(null, false);
        AdEventHandler.m7315mb().m7293ox("download_notification_show", jSONObject, c9837ox);
    }

    /* renamed from: ox */
    private void m7318ox(@NonNull final C9837ox c9837ox, long j) {
        final int mo7479m = c9837ox.mo7479m();
        if (DownloadSetting.obtain(mo7479m).optInt("notification_opt_2") != 1) {
            return;
        }
        m7324mb(mo7479m);
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.b.ww.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(mo7479m);
                JSONObject jSONObject = new JSONObject();
                C10050jb.m7040mb(jSONObject, "ttdownloader_type", (Object) 1);
                C10048h.m7089b(downloadInfo, jSONObject);
                if (downloadInfo == null || -2 != downloadInfo.getRealStatus() || downloadInfo.isPauseReserveOnWifi()) {
                    C10050jb.m7040mb(jSONObject, "error_code", (Object) 1001);
                } else {
                    C9962ww.this.m7323mb(mo7479m, c9837ox, jSONObject);
                }
                AdEventHandler.m7315mb().m7293ox("download_notification_try_show", jSONObject, c9837ox);
            }
        }, j * 1000);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:406)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:204)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:138)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:406)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:204)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:138)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String ww1672829046017dc(java.lang.String r4) {
        /*
        L0:
            r0 = 73
            r1 = 96
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L4f;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L0;
                case 95: goto L10;
                case 96: goto L4f;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L39;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L0
        L10:
            r0 = 18
            r1 = 1
            switch(r1) {
                case 60: goto L17;
                case 61: goto L23;
                case 62: goto L30;
                default: goto L16;
            }
        L16:
            goto L4f
        L17:
            int r3 = 0 - r1
            int r3 = r3 * r2
            r2 = 0
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r3 = r3 * r2
            int r3 = r3 % 6
            if (r3 == 0) goto L0
        L23:
            int r2 = 18 - r1
            int r2 = r2 * r0
            r3 = 18
            int r3 = r3 * 2
            int r3 = r3 - r1
            int r2 = r2 * r3
            int r2 = r2 % 6
            if (r2 == 0) goto L4f
        L30:
            r1 = 99
            int r1 = r1 * r1
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r1 = r1 - r0
            r0 = -1
            goto L0
        L39:
            char[] r4 = r4.toCharArray()
        L3d:
            int r0 = r4.length
            if (r2 >= r0) goto L49
            char r0 = r4[r2]
            r0 = r0 ^ r2
            char r0 = (char) r0
            r4[r2] = r0
            int r2 = r2 + 1
            goto L3d
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            return r0
        L4f:
            r0 = 74
            r1 = 55
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.p329b.C9962ww.ww1672829046017dc(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    public void m7329b(@NonNull C9837ox c9837ox) {
        m7328b(c9837ox, 5L);
    }

    /* renamed from: h */
    public void m7327h(@NonNull C9837ox c9837ox) {
        m7321mb(c9837ox, 5L);
    }

    /* renamed from: hj */
    public void m7326hj(@NonNull C9837ox c9837ox) {
        m7328b(c9837ox, DownloadSetting.obtain(c9837ox.mo7479m()).optInt("noti_install_delay_secs", 5));
    }

    /* renamed from: mb */
    public void m7324mb(int i) {
        DownloadInfo downloadInfo;
        if (C10103b.m6845mb().m6844mb(i) != null || (downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(i)) == null) {
            return;
        }
        C10103b.m6845mb().m6842mb(i, downloadInfo.getIconUrl());
    }

    /* renamed from: mb */
    public void m7322mb(C9837ox c9837ox) {
        m7318ox(c9837ox, 5L);
    }

    /* renamed from: mb */
    public void m7321mb(@NonNull final C9837ox c9837ox, long j) {
        final int mo7479m = c9837ox.mo7479m();
        if (DownloadSetting.obtain(mo7479m).optInt("notification_opt_2") != 1) {
            return;
        }
        m7324mb(mo7479m);
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.b.ww.3
            @Override // java.lang.Runnable
            public void run() {
                DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(mo7479m);
                JSONObject jSONObject = new JSONObject();
                C10050jb.m7040mb(jSONObject, "ttdownloader_type", (Object) 3);
                C10048h.m7089b(downloadInfo, jSONObject);
                if (C10050jb.m7062b(c9837ox.mo7489h())) {
                    C10050jb.m7040mb(jSONObject, "error_code", (Object) 1003);
                } else {
                    C9962ww.this.m7323mb(mo7479m, c9837ox, jSONObject);
                }
                AdEventHandler.m7315mb().m7293ox("download_notification_try_show", jSONObject, c9837ox);
            }
        }, j * 1000);
    }

    /* renamed from: ox */
    public void m7319ox(C9837ox c9837ox) {
        if (c9837ox == null) {
            return;
        }
        m7318ox(c9837ox, DownloadSetting.obtain(c9837ox.mo7479m()).optInt("noti_continue_delay_secs", 5));
    }

    /* renamed from: u */
    public void m7317u(@NonNull C9837ox c9837ox) {
        m7321mb(c9837ox, DownloadSetting.obtain(c9837ox.mo7479m()).optInt("noti_open_delay_secs", 5));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.b.ww$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9966mb {

        /* renamed from: mb */
        private static C9962ww f19212mb = new C9962ww();
    }
}
