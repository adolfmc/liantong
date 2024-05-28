package com.p319ss.android.downloadlib;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.p319ss.android.downloadad.api.InterfaceC9835mb;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9878h;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9917hj;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.downloadlib.addownload.p326hj.C9894mb;
import com.p319ss.android.downloadlib.addownload.p327mb.C9911mb;
import com.p319ss.android.downloadlib.addownload.p328ox.C9934hj;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.p329b.C9962ww;
import com.p319ss.android.downloadlib.p334ox.C10026h;
import com.p319ss.android.downloadlib.p334ox.C10032mb;
import com.p319ss.android.downloadlib.p334ox.C10036ox;
import com.p319ss.android.downloadlib.p334ox.C10039u;
import com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj;
import com.p319ss.android.downloadlib.p334ox.InterfaceC10030ko;
import com.p319ss.android.downloadlib.utils.C10048h;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10061mb;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10126mb;
import com.p319ss.android.socialbase.appdownloader.C10140ox;
import com.p319ss.android.socialbase.appdownloader.p336h.C10106hj;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.constants.DownloadStatus;
import com.p319ss.android.socialbase.downloader.depend.IOpenInstallerListener;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.network.NetTrafficManager;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9998mb implements InterfaceC9835mb, C10140ox.InterfaceC10143b, AppStatusManager.AppStatusChangeListener, IOpenInstallerListener {

    /* renamed from: hj */
    private static volatile C9998mb f19268hj = null;

    /* renamed from: mb */
    private static String f19269mb = "mb";

    /* renamed from: b */
    private RunnableC10005ox f19270b;

    /* renamed from: ox */
    private long f19271ox;

    private C9998mb() {
        C10140ox.m6709mb(this);
        AppStatusManager.getInstance().registerAppSwitchListener(this);
    }

    /* renamed from: mb */
    public static C9998mb m7215mb() {
        if (f19268hj == null) {
            synchronized (C9998mb.class) {
                if (f19268hj == null) {
                    f19268hj = new C9998mb();
                }
            }
        }
        return f19268hj;
    }

    @WorkerThread
    /* renamed from: mb */
    public static synchronized void m7205mb(DownloadInfo downloadInfo, C9837ox c9837ox) {
        synchronized (C9998mb.class) {
            if (downloadInfo == null) {
                C9971b.m7285mb().m7284mb("onDownloadFinish info null");
            } else if (c9837ox == null) {
                C9971b.m7285mb().m7284mb("onDownloadFinish nativeModel null");
            } else if (c9837ox.m7749sw() != 1) {
            } else {
                C9962ww.m7325mb().m7326hj(c9837ox);
                String m7217b = m7217b(downloadInfo, c9837ox);
                C9923u.m7451mb().m7434ox(downloadInfo.getUrl(), m7217b);
                Map<Long, C9837ox> m7440mb = C9923u.m7451mb().m7440mb(downloadInfo.getUrl(), m7217b);
                c9837ox.m7746u(System.currentTimeMillis());
                c9837ox.m7807h(2);
                c9837ox.m7758ox(m7217b);
                m7440mb.put(Long.valueOf(c9837ox.mo7474ox()), c9837ox);
                C9926ww.m7430mb().m7427mb(m7440mb.values());
                m7197ox(c9837ox);
                C9992ko.m7236mb().m7229mb(downloadInfo, m7217b);
                if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
                    m7215mb().m7211mb(c9837ox);
                    m7215mb().m7196ox(downloadInfo, c9837ox);
                    if (c9837ox.m7821al()) {
                        C9911mb.m7514mb().m7513mb(downloadInfo.getId(), c9837ox.mo7474ox(), c9837ox.mo7485je(), m7217b, downloadInfo.getTitle(), c9837ox.mo7488hj(), downloadInfo.getTargetFilePath());
                    }
                    C9894mb.m7574mb(downloadInfo, c9837ox.mo7474ox(), c9837ox.mo7488hj(), m7217b);
                }
            }
        }
    }

    @WorkerThread
    /* renamed from: mb */
    public synchronized void m7203mb(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (C10050jb.m7057mb()) {
            throw new RuntimeException("handleAppInstalled in main thread.");
        }
        final C9837ox m7441mb = C9923u.m7451mb().m7441mb(str);
        if (m7441mb == null) {
            C9917hj.m7468mb().m7466mb(str);
            return;
        }
        C9878h m7228mb = C9992ko.m7236mb().m7228mb(m7441mb.mo7478mb());
        if (m7228mb != null) {
            m7228mb.m7605u();
        }
        if (m7441mb.f18842b.get()) {
            return;
        }
        if (DownloadSetting.obtain(m7441mb.mo7479m()).optInt("notification_opt_2") == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(m7441mb.mo7479m());
        }
        new C10036ox().m7114mb(m7441mb, new InterfaceC10030ko() { // from class: com.ss.android.downloadlib.mb.1
            @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10030ko
            /* renamed from: mb */
            public void mo7129mb(boolean z) {
                String str2 = C9998mb.f19269mb;
                Logger.m6475d(str2, "appBackForeground->" + z);
                if (z) {
                    if (!(C10039u.m7113b(m7441mb) ? C10032mb.m7122mb(str, m7441mb) : false) && C10039u.m7111hj(m7441mb) && m7441mb.m7741w() == 4) {
                        C9911mb.m7514mb().m7507mb(m7441mb);
                    }
                } else if (C10032mb.m7122mb(str, m7441mb) || m7441mb.m7741w() != 4) {
                } else {
                    C9911mb.m7514mb().m7507mb(m7441mb);
                }
            }
        }, C10049hj.m7071mb(m7441mb).optInt("try_applink_delay_after_installed", 0));
        C9962ww.m7325mb().m7317u(m7441mb);
        m7202mb(str, m7441mb);
        C9911mb.m7514mb().m7505ox(str);
        DownloadInfo m7201mb = m7201mb(Downloader.getInstance(C9940x.getContext()).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive"), str);
        if (m7201mb != null) {
            if (DownloadSetting.obtain(m7201mb.getId()).optInt("no_hide_notification") != 1) {
                DownloadNotificationManager.getInstance().hideNotification(m7201mb.getId());
            }
            C9992ko.m7236mb().m7220ox(m7201mb, str);
            C9934hj.m7412mb(m7201mb);
        } else {
            C9992ko.m7236mb().m7220ox(null, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7204mb(DownloadInfo downloadInfo, C9837ox c9837ox, int i) {
        long max;
        if (downloadInfo == null || c9837ox == null) {
            return;
        }
        m7198ox();
        long currentTimeMillis = System.currentTimeMillis();
        c9837ox.m7759ox(currentTimeMillis);
        c9837ox.m7784ko(C10050jb.m7047mb(Environment.getDataDirectory(), -1L));
        if (i != 2000) {
            max = 2000;
        } else {
            long optLong = DownloadSetting.obtain(downloadInfo.getId()).optLong("check_install_failed_delay_time", 120000L);
            if (optLong < 0) {
                return;
            }
            max = Math.max(optLong, 30000L);
        }
        RunnableC10005ox runnableC10005ox = new RunnableC10005ox(c9837ox.mo7474ox(), downloadInfo.getId(), currentTimeMillis, i);
        C9982hj.m7254mb().m7252mb(runnableC10005ox, max);
        this.f19270b = runnableC10005ox;
        C9926ww.m7430mb().m7429mb(c9837ox);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.downloadlib.mb$ox */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class RunnableC10005ox implements Runnable {

        /* renamed from: b */
        private long f19287b;

        /* renamed from: h */
        private long f19288h;

        /* renamed from: hj */
        private int f19289hj;

        /* renamed from: mb */
        private long f19290mb;

        /* renamed from: ox */
        private int f19291ox;

        private RunnableC10005ox(long j, int i, long j2, int i2) {
            this.f19290mb = j;
            this.f19291ox = i;
            this.f19287b = j2;
            this.f19289hj = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: ox */
        public void m7182ox() {
            this.f19288h = System.currentTimeMillis();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (m7185mb()) {
                    C9998mb.m7215mb().m7212mb(this.f19290mb, this.f19291ox);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0063, code lost:
            if (r9 < r1) goto L18;
         */
        /* renamed from: mb */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean m7185mb() {
            /*
                Method dump skipped, instructions count: 264
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.C9998mb.RunnableC10005ox.m7185mb():boolean");
        }

        /* renamed from: mb */
        private int m7183mb(boolean z, C9837ox c9837ox, DownloadInfo downloadInfo, boolean z2, JSONObject jSONObject) {
            DownloadSetting obtain = DownloadSetting.obtain(downloadInfo.getId());
            int i = 1;
            if (obtain.optInt("install_failed_check_ttmd5", 1) == 1) {
                int checkMd5Status = downloadInfo.checkMd5Status();
                try {
                    jSONObject.put("ttmd5_status", checkMd5Status);
                } catch (Throwable unused) {
                }
                if (!DownloadUtils.isMd5Valid(checkMd5Status)) {
                    return 2005;
                }
            }
            int i2 = this.f19289hj;
            if (i2 != 2000) {
                return i2;
            }
            if (obtain.optInt("install_failed_check_signature", 1) == 1 && C10050jb.m7060hj(C9940x.getContext(), c9837ox.mo7489h())) {
                if (!C10050jb.m7038mb(C10050jb.m7027ww(C9940x.getContext(), downloadInfo.getTargetFilePath()), C10050jb.m7059ko(C9940x.getContext(), c9837ox.mo7489h()))) {
                    return 2006;
                }
            }
            if (z) {
                long j = this.f19288h;
                long j2 = this.f19287b;
                if (j > j2) {
                    try {
                        jSONObject.put("install_time", j - j2);
                        if (c9837ox.m7732xa() <= this.f19287b) {
                            i = 0;
                        }
                        jSONObject.put("install_again", i);
                    } catch (Throwable unused2) {
                    }
                    return !z2 ? 2003 : 2004;
                }
                return 2000;
            }
            return 2002;
        }
    }

    /* renamed from: mb */
    public void m7212mb(final long j, int i) {
        long optLong = DownloadSetting.obtain(i).optLong("check_install_finish_hijack_delay_time", 900000L);
        if (optLong < 0) {
            return;
        }
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.mb.2
            @Override // java.lang.Runnable
            public void run() {
                C9998mb.m7215mb().m7213mb(j);
            }
        }, Math.max(optLong, 300000L));
    }

    /* renamed from: mb */
    public void m7213mb(long j) {
        C9917hj.C9919mb m7467mb;
        int i;
        try {
            C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
            if (m7452hj != null && !C10050jb.m7031ox(m7452hj) && !m7452hj.f18842b.get()) {
                Pair<C9917hj.C9919mb, Integer> m7463ox = C9917hj.m7468mb().m7463ox(m7452hj);
                if (m7463ox != null) {
                    m7467mb = (C9917hj.C9919mb) m7463ox.first;
                    i = ((Integer) m7463ox.second).intValue();
                } else {
                    m7467mb = C9917hj.m7468mb().m7467mb(m7452hj);
                    i = -1;
                }
                if (m7467mb == null) {
                    return;
                }
                C9917hj.m7468mb().m7462ox(m7467mb.f19111mb);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("installed_app_name", m7467mb.f19110hj);
                jSONObject.put("installed_pkg_name", m7467mb.f19111mb);
                if (i != -1) {
                    jSONObject.put("error_code", i);
                    C10048h.m7086mb(jSONObject, m7452hj.mo7479m());
                    AdEventHandler.m7315mb().m7293ox("install_finish_hijack", jSONObject, m7452hj);
                    return;
                }
                AdEventHandler.m7315mb().m7293ox("install_finish_may_hijack", jSONObject, m7452hj);
            }
        } catch (Throwable th) {
            C9971b.m7285mb().mo7282mb(th, "trySendInstallFinishHijack");
        }
    }

    /* renamed from: mb */
    public void m7202mb(String str, C9837ox c9837ox) {
        if (c9837ox != null && C10050jb.m7031ox(c9837ox) && c9837ox.f18842b.compareAndSet(false, true)) {
            AdEventHandler.m7315mb().m7300mb(c9837ox.mo7470x(), "install_finish", m7209mb(c9837ox, str, c9837ox.m7741w() != 4 ? 3 : 4), c9837ox);
            C9926ww.m7430mb().m7429mb(c9837ox);
        }
    }

    /* renamed from: mb */
    private static DownloadInfo m7201mb(List<DownloadInfo> list, String str) {
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            return null;
        }
        for (DownloadInfo downloadInfo : list) {
            if (downloadInfo != null) {
                if (str.equals(downloadInfo.getPackageName())) {
                    return downloadInfo;
                }
                if (C10050jb.m7051mb(C9940x.getContext(), downloadInfo.getTargetFilePath(), str)) {
                    return downloadInfo;
                }
            }
        }
        return null;
    }

    /* renamed from: mb */
    public static JSONObject m7199mb(JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (jSONObject == null || downloadInfo == null) {
            return jSONObject;
        }
        int i = 1;
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt("download_event_opt", 1) == 0) {
            return jSONObject;
        }
        try {
            jSONObject.put("download_id", downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
            jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
            jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
            jSONObject.put("network_quality", downloadInfo.getNetworkQuality());
            jSONObject.put("current_network_quality", NetTrafficManager.getInstance().getCurrentNetworkQuality().name());
            jSONObject.put("only_wifi", downloadInfo.isOnlyWifi() ? 1 : 0);
            jSONObject.put("need_https_degrade", downloadInfo.isNeedHttpsToHttpRetry() ? 1 : 0);
            jSONObject.put("https_degrade_retry_used", downloadInfo.isHttpsToHttpRetryUsed() ? 1 : 0);
            jSONObject.put("chunk_count", downloadInfo.getChunkCount());
            jSONObject.put("retry_count", downloadInfo.getRetryCount());
            jSONObject.put("cur_retry_time", downloadInfo.getCurRetryTime());
            jSONObject.put("need_retry_delay", downloadInfo.isNeedRetryDelay() ? 1 : 0);
            jSONObject.put("backup_url_used", downloadInfo.isBackUpUrlUsed() ? 1 : 0);
            jSONObject.put("head_connection_error_msg", downloadInfo.getHeadConnectionException() != null ? downloadInfo.getHeadConnectionException() : "");
            jSONObject.put("need_independent_process", downloadInfo.isNeedIndependentProcess() ? 1 : 0);
            jSONObject.put("total_retry_count", downloadInfo.getTotalRetryCount());
            jSONObject.put("cur_retry_time_in_total", downloadInfo.getCurRetryTimeInTotal());
            jSONObject.put("real_download_time", downloadInfo.getRealDownloadTime());
            jSONObject.put("first_speed_time", downloadInfo.getFirstSpeedTime());
            jSONObject.put("all_connect_time", downloadInfo.getAllConnectTime());
            jSONObject.put("download_prepare_time", downloadInfo.getDownloadPrepareTime());
            jSONObject.put("download_time", downloadInfo.getRealDownloadTime() + downloadInfo.getAllConnectTime() + downloadInfo.getDownloadPrepareTime());
            jSONObject.put("chunk_downgrade_retry_used", downloadInfo.isChunkDowngradeRetryUsed() ? 1 : 0);
            jSONObject.put("need_chunk_downgrade_retry", downloadInfo.isNeedChunkDowngradeRetry() ? 1 : 0);
            jSONObject.put("failed_resume_count", downloadInfo.getFailedResumeCount());
            jSONObject.put("preconnect_level", downloadInfo.getPreconnectLevel());
            jSONObject.put("md5", downloadInfo.getMd5());
            jSONObject.put("expect_file_length", downloadInfo.getExpectFileLength());
            jSONObject.put("retry_schedule_count", downloadInfo.getRetryScheduleCount());
            jSONObject.put("rw_concurrent", downloadInfo.isRwConcurrent() ? 1 : 0);
            double curBytes = downloadInfo.getCurBytes() / 1048576.0d;
            double realDownloadTime = downloadInfo.getRealDownloadTime() / 1000.0d;
            if (curBytes > 0.0d && realDownloadTime > 0.0d) {
                double d = curBytes / realDownloadTime;
                try {
                    jSONObject.put("download_speed", d);
                } catch (Exception unused) {
                }
                String str = f19269mb;
                Logger.m6475d(str, "download speed : " + d + "MB/s");
            }
            try {
                jSONObject.put("is_download_service_foreground", Downloader.getInstance(C9940x.getContext()).isDownloadServiceForeground(downloadInfo.getId()) ? 1 : 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (downloadInfo.getBackUpUrls() != null) {
                jSONObject.put("backup_url_count", downloadInfo.getBackUpUrls().size());
                jSONObject.put("cur_backup_url_index", downloadInfo.getCurBackUpUrlIndex());
            }
            jSONObject.put("clear_space_restart_times", C9934hj.m7414mb().m7408ox(downloadInfo.getUrl()));
            jSONObject.put("mime_type", downloadInfo.getMimeType());
            if (!DownloadUtils.isNetworkConnected(C9940x.getContext())) {
                i = 2;
            }
            jSONObject.put("network_available", i);
            jSONObject.put("status_code", downloadInfo.getHttpStatusCode());
            m7194ox(jSONObject, downloadInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: ox */
    public static JSONObject m7194ox(JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (jSONObject == null || downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt("download_event_opt", 1) == 0) {
            return jSONObject;
        }
        try {
            long m7055mb = C10050jb.m7055mb(0L);
            double d = m7055mb;
            jSONObject.put("available_space", d / 1048576.0d);
            long totalBytes = downloadInfo.getTotalBytes();
            double d2 = totalBytes;
            jSONObject.put("apk_size", d2 / 1048576.0d);
            if (m7055mb > 0 && totalBytes > 0) {
                jSONObject.put("available_space_ratio", d / d2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: ox */
    public void m7196ox(DownloadInfo downloadInfo, final C9837ox c9837ox) {
        if (downloadInfo == null || c9837ox == null || DownloadSetting.obtain(downloadInfo.getId()).optInt("install_finish_check_ttmd5", 1) == 0) {
            return;
        }
        final String targetFilePath = downloadInfo.getTargetFilePath();
        if (TextUtils.isEmpty(targetFilePath)) {
            return;
        }
        C9982hj.m7254mb().m7249ox(new Runnable() { // from class: com.ss.android.downloadlib.mb.3
            @Override // java.lang.Runnable
            public void run() {
                String m6987mb = C10061mb.m6987mb(targetFilePath);
                if (TextUtils.isEmpty(m6987mb)) {
                    return;
                }
                C9940x.getContext().getSharedPreferences("sp_ttdownloader_md5", 0).edit().putString(String.valueOf(c9837ox.mo7474ox()), m6987mb).apply();
            }
        });
    }

    /* renamed from: ox */
    private static void m7197ox(C9837ox c9837ox) {
        if (c9837ox == null) {
            return;
        }
        String m7729z = TextUtils.isEmpty(c9837ox.m7729z()) ? "" : c9837ox.m7729z();
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(c9837ox.mo7479m());
        c9837ox.m7792je("");
        C9926ww.m7430mb().m7429mb(c9837ox);
        JSONObject m7199mb = m7199mb(new JSONObject(), downloadInfo);
        int i = 1;
        try {
            m7199mb.putOpt("finish_reason", m7729z);
            m7199mb.putOpt("finish_from_reserve_wifi", Integer.valueOf(downloadInfo.isDownloadFromReserveWifi() ? 1 : 0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        C10048h.m7086mb(m7199mb, downloadInfo.getId());
        try {
            m7199mb.put("download_failed_times", m7442mb.m7762on());
            m7199mb.put("can_show_notification", C10106hj.m6838mb() ? 1 : 2);
            if (downloadInfo.getExpectFileLength() > 0 && downloadInfo.getTotalBytes() > 0) {
                m7199mb.put("file_length_gap", downloadInfo.getExpectFileLength() - downloadInfo.getTotalBytes());
            }
            m7199mb.put("ttmd5_status", downloadInfo.getTTMd5CheckStatus());
            m7199mb.put("has_send_download_failed_finally", m7442mb.f18855hj.get() ? 1 : 2);
            if (!m7442mb.m7753qa()) {
                i = 2;
            }
            m7199mb.put("is_update_download", i);
            C10048h.m7088mb(m7442mb, m7199mb);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AdEventHandler.m7315mb().m7293ox("download_finish", m7199mb, c9837ox);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    /* renamed from: b */
    public void m7218b(C9837ox c9837ox) {
        SystemClock.sleep(20000L);
        int i = 15;
        while (i > 0) {
            if (C10050jb.m7031ox(c9837ox)) {
                m7203mb(c9837ox.mo7489h());
                return;
            }
            i--;
            if (i == 0) {
                return;
            }
            SystemClock.sleep(20000L);
        }
    }

    /* renamed from: mb */
    private int m7210mb(C9837ox c9837ox, DownloadInfo downloadInfo, String str, JSONObject jSONObject) {
        int m6894ox = C10085b.m6894ox(C9940x.getContext(), downloadInfo);
        int m7032ox = C10050jb.m7032ox(C9940x.getContext(), str);
        if (m6894ox > 0 && m7032ox > 0 && m6894ox != m7032ox) {
            return m7032ox > m6894ox ? 3011 : 3010;
        } else if (DownloadSetting.obtain(c9837ox.mo7479m()).optInt("install_finish_check_ttmd5", 1) == 1) {
            String string = C9940x.getContext().getSharedPreferences("sp_ttdownloader_md5", 0).getString(String.valueOf(c9837ox.mo7474ox()), null);
            if (TextUtils.isEmpty(string) && downloadInfo != null) {
                string = C10061mb.m6987mb(downloadInfo.getTargetFilePath());
            }
            int m6986mb = C10061mb.m6986mb(string, C10061mb.m6983ox(str));
            try {
                jSONObject.put("ttmd5_status", m6986mb);
            } catch (Throwable unused) {
            }
            if (m6986mb == 0) {
                return 3000;
            }
            return m6986mb == 1 ? 3002 : 3001;
        } else {
            return 3001;
        }
    }

    /* renamed from: b */
    public static String m7217b(@NonNull DownloadInfo downloadInfo, @NonNull C9837ox c9837ox) {
        File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
        String str = null;
        if (file.exists()) {
            try {
                PackageInfo packageArchiveInfo = C9940x.getContext().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), C10085b.m6927mb());
                if (packageArchiveInfo != null) {
                    str = packageArchiveInfo.packageName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(str) && !str.equals(downloadInfo.getPackageName())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("real_package_name", str);
                jSONObject.put("input_package_name", downloadInfo.getPackageName());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            AdEventHandler.m7315mb().m7300mb("embeded_ad", "package_name_error", jSONObject, c9837ox);
            return str;
        }
        return downloadInfo.getPackageName();
    }

    @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppForeground() {
        Logger.m6475d(f19269mb, "onAppForeground()");
        m7198ox();
        mo7214mb(5);
    }

    @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppBackground() {
        Logger.m6475d(f19269mb, "onAppBackground()");
        mo7214mb(6);
    }

    /* renamed from: ox */
    synchronized void m7198ox() {
        RunnableC10005ox runnableC10005ox = this.f19270b;
        if (runnableC10005ox != null) {
            runnableC10005ox.m7182ox();
            this.f19270b = null;
        }
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9835mb
    /* renamed from: mb */
    public void mo7214mb(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f19271ox < 120000) {
            return;
        }
        C9982hj.m7254mb().m7252mb(new RunnableC10004mb(i), this.f19271ox > 0 ? 2000L : 8000L);
        this.f19271ox = currentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    /* renamed from: mb */
    public void m7200mb(@NonNull ConcurrentHashMap<Long, C9837ox> concurrentHashMap, int i) {
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        for (C9837ox c9837ox : concurrentHashMap.values()) {
            if (c9837ox.f18842b.get()) {
                if (currentTimeMillis - c9837ox.m7740wn() >= DownloadSetting.obtain(c9837ox.mo7479m()).optInt("start_event_expire_hours", 168) * 60 * 60 * 1000) {
                    arrayList.add(Long.valueOf(c9837ox.mo7474ox()));
                }
            } else if (c9837ox.m7749sw() == 1) {
                if (m7216hj(c9837ox) <= 0 && currentTimeMillis - c9837ox.m7740wn() >= DownloadSetting.obtain(c9837ox.mo7479m()).optInt("start_event_expire_hours", 168) * 60 * 60 * 1000) {
                    arrayList.add(Long.valueOf(c9837ox.mo7474ox()));
                }
            } else if (c9837ox.m7749sw() == 2) {
                if (!c9837ox.m7752s()) {
                    if (C10050jb.m7031ox(c9837ox)) {
                        if (c9837ox.m7741w() == 4) {
                            i = c9837ox.m7741w();
                        }
                        AdEventHandler.m7315mb().m7297mb(m7209mb(c9837ox, c9837ox.mo7489h(), i), c9837ox);
                        arrayList.add(Long.valueOf(c9837ox.mo7474ox()));
                        C9934hj.m7413mb(c9837ox);
                    } else if (currentTimeMillis - c9837ox.m7740wn() >= DownloadSetting.obtain(c9837ox.mo7479m()).optInt("finish_event_expire_hours", 168) * 60 * 60 * 1000) {
                        arrayList.add(Long.valueOf(c9837ox.mo7474ox()));
                    } else if (TextUtils.isEmpty(c9837ox.mo7489h())) {
                        arrayList.add(Long.valueOf(c9837ox.mo7474ox()));
                    }
                }
            } else {
                arrayList.add(Long.valueOf(c9837ox.mo7474ox()));
            }
        }
        C9923u.m7451mb().m7439mb(arrayList);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.C10140ox.InterfaceC10143b
    /* renamed from: mb */
    public void mo6696mb(DownloadInfo downloadInfo, C10126mb c10126mb) {
        JSONObject m7195ox;
        if (downloadInfo == null || c10126mb == null) {
            return;
        }
        JSONArray optJSONArray = DownloadSetting.obtain(downloadInfo.getId()).optJSONArray("ah_report_config");
        if (c10126mb.f19537ox != 0) {
            downloadInfo.getTempCacheData().remove("intent");
        }
        if (optJSONArray == null || (m7195ox = m7195ox(downloadInfo, c10126mb)) == null) {
            return;
        }
        downloadInfo.getTempCacheData().put("ah_ext_json", m7195ox);
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IOpenInstallerListener
    public void onOpenInstaller(@Nullable final DownloadInfo downloadInfo, @Nullable String str) {
        if (downloadInfo == null) {
            C9971b.m7285mb().m7284mb("info is null");
        } else if ((DownloadSetting.obtain(downloadInfo).optInt("check_applink_mode") & 2) != 0) {
            final JSONObject jSONObject = (JSONObject) downloadInfo.getTempCacheData().get("ah_ext_json");
            C10026h.m7135mb().m7131ox(new InterfaceC10029hj() { // from class: com.ss.android.downloadlib.mb.4
                @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj
                /* renamed from: mb */
                public void mo7115mb(boolean z) {
                    if (!z) {
                        Intent intent = (Intent) downloadInfo.getTempCacheData().get("intent");
                        if (intent != null) {
                            downloadInfo.getTempCacheData().remove("intent");
                            C10085b.m6914mb(C9940x.getContext(), intent);
                            C10050jb.m7040mb(jSONObject, "backup", (Object) 1);
                        } else {
                            C10050jb.m7040mb(jSONObject, "backup", (Object) 2);
                        }
                    }
                    C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
                    if (m7442mb != null) {
                        AdEventHandler.m7315mb().m7298mb(z ? "installer_delay_success" : "installer_delay_failed", jSONObject, m7442mb);
                    } else {
                        C9971b.m7285mb().m7278ox("ah nativeModel=null");
                    }
                    if (z) {
                        C9940x.m7373gm().mo7340mb(C9940x.getContext(), null, null, null, null, 1);
                    }
                }
            });
        }
    }

    /* renamed from: hj */
    private int m7216hj(C9837ox c9837ox) {
        int realStatus;
        double optDouble = DownloadSetting.obtain(c9837ox.mo7479m()).optDouble("download_failed_finally_hours", 48.0d);
        if (optDouble <= 0.0d) {
            return -1;
        }
        if (System.currentTimeMillis() - c9837ox.m7740wn() < optDouble * 60.0d * 60.0d * 1000.0d) {
            return 1;
        }
        if (c9837ox.f18855hj.get()) {
            return 0;
        }
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(c9837ox.mo7479m());
        if (downloadInfo == null || (realStatus = downloadInfo.getRealStatus()) == -3 || realStatus == -4) {
            return -1;
        }
        if (!DownloadStatus.isDownloading(realStatus) && c9837ox.f18855hj.compareAndSet(false, true)) {
            try {
                JSONObject jSONObject = new JSONObject();
                m7199mb(jSONObject, downloadInfo);
                jSONObject.putOpt("download_status", Integer.valueOf(realStatus));
                jSONObject.putOpt("fail_status", Integer.valueOf(c9837ox.m7811fu()));
                jSONObject.putOpt("fail_msg", c9837ox.m7813ep());
                jSONObject.put("download_failed_times", c9837ox.m7762on());
                if (downloadInfo.getTotalBytes() > 0) {
                    jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
                }
                jSONObject.put("is_update_download", c9837ox.m7753qa() ? 1 : 2);
                AdEventHandler.m7315mb().m7300mb(c9837ox.mo7470x(), "download_failed_finally", jSONObject, c9837ox);
                C9926ww.m7430mb().m7429mb(c9837ox);
                return 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return 1;
    }

    /* renamed from: ox */
    private JSONObject m7195ox(@NonNull DownloadInfo downloadInfo, C10126mb c10126mb) {
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        c10126mb.m6737mb(jSONObject);
        try {
            jSONObject.put("download_id", downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        C10048h.m7086mb(jSONObject, downloadInfo.getId());
        AdEventHandler.m7315mb().m7300mb("embeded_ad", "ah_result", jSONObject, m7442mb);
        return jSONObject;
    }

    /* renamed from: mb */
    public void m7211mb(C9837ox c9837ox) {
        C9982hj.m7254mb().m7253mb(new RunnableC10003b(c9837ox));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.downloadlib.mb$b */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC10003b implements Runnable {

        /* renamed from: ox */
        private final C9837ox f19284ox;

        public RunnableC10003b(C9837ox c9837ox) {
            this.f19284ox = c9837ox;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    this.f19284ox.m7733x(true);
                    C9998mb.this.m7218b(this.f19284ox);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                this.f19284ox.m7733x(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    /* renamed from: com.ss.android.downloadlib.mb$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC10004mb implements Runnable {

        /* renamed from: ox */
        private final int f19286ox;

        public RunnableC10004mb(int i) {
            this.f19286ox = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C9923u.m7451mb().m7438ox();
                ConcurrentHashMap<Long, C9837ox> m7455b = C9923u.m7451mb().m7455b();
                if (m7455b == null || m7455b.isEmpty()) {
                    return;
                }
                C9998mb.this.m7200mb(m7455b, this.f19286ox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: mb */
    private JSONObject m7209mb(C9837ox c9837ox, String str, int i) {
        C10126mb m6738mb;
        JSONObject jSONObject = new JSONObject();
        try {
            DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(c9837ox.mo7479m());
            jSONObject.putOpt("scene", Integer.valueOf(i));
            C10048h.m7086mb(jSONObject, c9837ox.mo7479m());
            C10048h.m7088mb(c9837ox, jSONObject);
            jSONObject.put("is_update_download", c9837ox.m7753qa() ? 1 : 2);
            jSONObject.put("install_after_back_app", c9837ox.m7764nq() ? 1 : 2);
            jSONObject.putOpt("clean_space_install_params", c9837ox.m7761ot() ? "1" : "2");
            if (downloadInfo != null) {
                m7199mb(jSONObject, downloadInfo);
                try {
                    jSONObject.put("uninstall_resume_count", downloadInfo.getUninstallResumeCount());
                    if (c9837ox.m7732xa() > 0) {
                        jSONObject.put("install_time", System.currentTimeMillis() - c9837ox.m7732xa());
                    }
                } catch (Throwable unused) {
                }
                String string = DownloadUtils.getString(downloadInfo.getTempCacheData().get("ah_attempt"), null);
                if (!TextUtils.isEmpty(string) && (m6738mb = C10126mb.m6738mb(string)) != null) {
                    m6738mb.m6737mb(jSONObject);
                }
            }
            int m7210mb = m7210mb(c9837ox, downloadInfo, str, jSONObject);
            jSONObject.put("fail_status", m7210mb);
            if (m7210mb == 3000) {
                jSONObject.put("hijack", 2);
            } else if (m7210mb == 3001) {
                jSONObject.put("hijack", 0);
            } else {
                jSONObject.put("hijack", 1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: mb */
    public void m7206mb(DownloadInfo downloadInfo, long j, long j2, long j3, long j4, long j5, boolean z) {
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb == null) {
            C9971b.m7285mb().m7284mb("trySendClearSpaceEvent nativeModel null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("space_before", Double.valueOf(j / 1048576.0d));
            jSONObject.putOpt("space_cleaned", Double.valueOf((j2 - j) / 1048576.0d));
            jSONObject.putOpt("clean_up_time_cost", Long.valueOf(j4));
            jSONObject.putOpt("is_download_restarted", Integer.valueOf(z ? 1 : 0));
            jSONObject.putOpt("byte_required", Long.valueOf(j3));
            jSONObject.putOpt("byte_required_after", Double.valueOf((j3 - j2) / 1048576.0d));
            jSONObject.putOpt("clear_sleep_time", Long.valueOf(j5));
            C10048h.m7089b(downloadInfo, jSONObject);
            AdEventHandler.m7315mb().m7298mb("cleanup", jSONObject, m7442mb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
