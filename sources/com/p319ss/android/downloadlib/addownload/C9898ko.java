package com.p319ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.InterfaceC9795gm;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import com.p319ss.android.download.api.download.InterfaceC9816mb;
import com.p319ss.android.download.api.model.DownloadShortInfo;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9992ko;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.p328ox.C9935mb;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.event.C9970ox;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.p334ox.C10032mb;
import com.p319ss.android.downloadlib.utils.C10047b;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10059lz;
import com.p319ss.android.downloadlib.utils.HandlerC10051je;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.C10149u;
import com.p319ss.android.socialbase.downloader.depend.AbsDownloadExtListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.ko */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9898ko implements HandlerC10051je.InterfaceC10052mb {

    /* renamed from: b */
    private boolean f19038b = false;

    /* renamed from: h */
    private InterfaceC9903ox f19039h;

    /* renamed from: hj */
    private C9878h f19040hj;

    /* renamed from: mb */
    private long f19041mb;

    /* renamed from: ox */
    private C9916h f19042ox;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.ko$ox */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9903ox {
        /* renamed from: mb */
        void mo7538mb(DownloadInfo downloadInfo);
    }

    @Override // com.p319ss.android.downloadlib.utils.HandlerC10051je.InterfaceC10052mb
    /* renamed from: mb */
    public void mo7026mb(Message message) {
    }

    public C9898ko(C9878h c9878h) {
        this.f19040hj = c9878h;
    }

    /* renamed from: mb */
    public void m7561mb(long j) {
        this.f19041mb = j;
        this.f19042ox = C9923u.m7451mb().m7453h(j);
        if (this.f19042ox.m7475on()) {
            C9971b.m7285mb().m7284mb("setAdId ModelBox notValid");
        }
    }

    /* renamed from: mb */
    public void m7555mb(DownloadInfo downloadInfo) {
        this.f19038b = false;
        m7545ox(downloadInfo);
    }

    /* renamed from: ox */
    public void m7545ox(@Nullable DownloadInfo downloadInfo) {
        InterfaceC9903ox interfaceC9903ox = this.f19039h;
        if (interfaceC9903ox != null) {
            interfaceC9903ox.mo7538mb(downloadInfo);
            this.f19039h = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public boolean m7560mb(Context context, int i, boolean z) {
        if (C10050jb.m7050mb(this.f19042ox.f19103ox)) {
            C9837ox m7452hj = C9923u.m7451mb().m7452hj(this.f19042ox.f19102mb);
            if (m7452hj != null) {
                DownloadNotificationManager.getInstance().cancelNotification(m7452hj.mo7479m());
            }
            return C10032mb.m7124mb(this.f19042ox);
        } else if (m7562mb(i) && !TextUtils.isEmpty(this.f19042ox.f19103ox.getPackageName()) && C9940x.m7364lz().optInt("disable_market") != 1) {
            if (C10032mb.m7123mb(this.f19042ox, i)) {
                return true;
            }
            return this.f19040hj.m7624lz() && this.f19040hj.m7632hj(true);
        } else if (z && this.f19042ox.f19101hj.getDownloadMode() == 4 && !this.f19040hj.m7636h()) {
            this.f19040hj.m7638b(true);
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: mb */
    private boolean m7562mb(int i) {
        if (this.f19042ox.f19101hj.getDownloadMode() == 2 && i == 2) {
            return true;
        }
        return this.f19042ox.f19101hj.getDownloadMode() == 2 && i == 1 && C9940x.m7364lz().optInt("disable_lp_if_market", 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public boolean m7549mb(boolean z) {
        return !z && this.f19042ox.f19101hj.getDownloadMode() == 1;
    }

    /* renamed from: b */
    private boolean m7569b() {
        return m7565hj() && m7567h();
    }

    /* renamed from: hj */
    private boolean m7565hj() {
        return (this.f19042ox.f19103ox == null || TextUtils.isEmpty(this.f19042ox.f19103ox.getPackageName()) || TextUtils.isEmpty(this.f19042ox.f19103ox.getDownloadUrl())) ? false : true;
    }

    /* renamed from: h */
    private boolean m7567h() {
        return this.f19042ox.f19101hj.isAddToDownloadManage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7557mb(@NonNull final InterfaceC9795gm interfaceC9795gm) {
        if (!TextUtils.isEmpty(this.f19042ox.f19103ox.getFilePath())) {
            String filePath = this.f19042ox.f19103ox.getFilePath();
            if (filePath.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
                interfaceC9795gm.mo7541mb();
                return;
            }
            try {
                if (filePath.startsWith(C9940x.getContext().getExternalCacheDir().getParent())) {
                    interfaceC9795gm.mo7541mb();
                    return;
                }
            } catch (Exception unused) {
            }
        }
        m7547ox(new InterfaceC9795gm() { // from class: com.ss.android.downloadlib.addownload.ko.1
            @Override // com.p319ss.android.download.api.config.InterfaceC9795gm
            /* renamed from: mb */
            public void mo7541mb() {
                interfaceC9795gm.mo7541mb();
            }

            @Override // com.p319ss.android.download.api.config.InterfaceC9795gm
            /* renamed from: mb */
            public void mo7540mb(String str) {
                C9940x.m7377b().mo7905mb(1, C9940x.getContext(), C9898ko.this.f19042ox.f19103ox, "您已禁止使用存储权限，请授权后再下载", null, 1);
                AdEventHandler.m7315mb().m7296ox(C9898ko.this.f19041mb, 1);
                interfaceC9795gm.mo7540mb(str);
            }
        });
    }

    /* renamed from: ox */
    private void m7547ox(final InterfaceC9795gm interfaceC9795gm) {
        if (!C10059lz.m6992ox("android.permission.WRITE_EXTERNAL_STORAGE")) {
            C10059lz.m6993mb(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, new C10059lz.InterfaceC10060mb() { // from class: com.ss.android.downloadlib.addownload.ko.2
                @Override // com.p319ss.android.downloadlib.utils.C10059lz.InterfaceC10060mb
                /* renamed from: mb */
                public void mo6991mb() {
                    InterfaceC9795gm interfaceC9795gm2 = interfaceC9795gm;
                    if (interfaceC9795gm2 != null) {
                        interfaceC9795gm2.mo7541mb();
                    }
                }

                @Override // com.p319ss.android.downloadlib.utils.C10059lz.InterfaceC10060mb
                /* renamed from: mb */
                public void mo6990mb(String str) {
                    InterfaceC9795gm interfaceC9795gm2 = interfaceC9795gm;
                    if (interfaceC9795gm2 != null) {
                        interfaceC9795gm2.mo7540mb(str);
                    }
                }
            });
        } else if (interfaceC9795gm != null) {
            interfaceC9795gm.mo7541mb();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7558mb(Message message, DownloadShortInfo downloadShortInfo, Map<Integer, Object> map) {
        InterfaceC9903ox interfaceC9903ox;
        if (message == null || message.what != 3) {
            return;
        }
        DownloadInfo downloadInfo = (DownloadInfo) message.obj;
        if (message.arg1 != 1 && message.arg1 != 6 && message.arg1 == 2) {
            if (downloadInfo.getIsFirstDownload()) {
                C9992ko.m7236mb().m7234mb(this.f19042ox.f19103ox, this.f19042ox.f19101hj, this.f19042ox.f19100b);
                downloadInfo.setFirstDownload(false);
            }
            AdEventHandler.m7315mb().m7308mb(downloadInfo);
        }
        downloadShortInfo.updateFromNewDownloadInfo(downloadInfo);
        C9904lz.m7534mb(downloadShortInfo);
        int m6926mb = C10085b.m6926mb(downloadInfo.getStatus());
        long totalBytes = downloadInfo.getTotalBytes();
        int i = (totalBytes > 0L ? 1 : (totalBytes == 0L ? 0 : -1));
        int curBytes = i > 0 ? (int) ((downloadInfo.getCurBytes() * 100) / totalBytes) : 0;
        if ((i > 0 || DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) && (interfaceC9903ox = this.f19039h) != null) {
            interfaceC9903ox.mo7538mb(downloadInfo);
            this.f19039h = null;
        }
        for (DownloadStatusChangeListener downloadStatusChangeListener : m7550mb(map)) {
            switch (m6926mb) {
                case 1:
                    if (downloadInfo.getStatus() != 11) {
                        downloadStatusChangeListener.onDownloadActive(downloadShortInfo, C9904lz.m7536mb(downloadInfo.getId(), curBytes));
                        break;
                    } else {
                        for (InterfaceC9816mb interfaceC9816mb : m7544ox(map)) {
                            interfaceC9816mb.m7911mb(downloadInfo);
                        }
                        break;
                    }
                case 2:
                    downloadStatusChangeListener.onDownloadPaused(downloadShortInfo, C9904lz.m7536mb(downloadInfo.getId(), curBytes));
                    break;
                case 3:
                    if (downloadInfo.getStatus() == -4) {
                        downloadStatusChangeListener.onIdle();
                        break;
                    } else if (downloadInfo.getStatus() == -1) {
                        downloadStatusChangeListener.onDownloadFailed(downloadShortInfo);
                        break;
                    } else if (downloadInfo.getStatus() != -3) {
                        break;
                    } else if (C10050jb.m7050mb(this.f19042ox.f19103ox)) {
                        downloadStatusChangeListener.onInstalled(downloadShortInfo);
                        break;
                    } else {
                        downloadStatusChangeListener.onDownloadFinished(downloadShortInfo);
                        break;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m7568b(DownloadInfo downloadInfo) {
        if (!C9939ww.m7386mb(this.f19042ox.f19103ox) || this.f19038b) {
            return;
        }
        AdEventHandler.m7315mb().m7306mb("file_status", (downloadInfo == null || !C10050jb.m7030ox(downloadInfo.getTargetFilePath())) ? 2 : 1, this.f19042ox);
        this.f19038b = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7563mb() {
        if (this.f19039h == null) {
            this.f19039h = new InterfaceC9903ox() { // from class: com.ss.android.downloadlib.addownload.ko.3
                @Override // com.p319ss.android.downloadlib.addownload.C9898ko.InterfaceC9903ox
                /* renamed from: mb */
                public void mo7538mb(DownloadInfo downloadInfo) {
                    AdEventHandler.m7315mb().m7313mb(C9898ko.this.f19041mb, 2, downloadInfo);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: hj */
    public boolean m7564hj(DownloadInfo downloadInfo) {
        return m7543u() || m7566h(downloadInfo);
    }

    /* renamed from: h */
    private boolean m7566h(DownloadInfo downloadInfo) {
        return !C10050jb.m7050mb(this.f19042ox.f19103ox) && m7542u(downloadInfo);
    }

    /* renamed from: u */
    private boolean m7543u() {
        return C10050jb.m7050mb(this.f19042ox.f19103ox) && C9939ww.m7387mb(this.f19042ox.f19101hj.getLinkMode());
    }

    /* renamed from: u */
    private boolean m7542u(DownloadInfo downloadInfo) {
        return downloadInfo != null && downloadInfo.getStatus() == -3 && DownloadUtils.isFileExist(downloadInfo.getSavePath(), downloadInfo.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public int m7559mb(Context context, IDownloadListener iDownloadListener) {
        HttpHeader m7551mb;
        if (context == null) {
            return 0;
        }
        Map<String, String> headers = this.f19042ox.f19103ox.getHeaders();
        ArrayList arrayList = new ArrayList();
        if (C9940x.m7364lz().optInt("enable_send_click_id_in_apk", 1) == 1 && !TextUtils.isEmpty(this.f19042ox.f19103ox.getLogExtra()) && (m7551mb = m7551mb(this.f19042ox.f19103ox.getLogExtra())) != null) {
            arrayList.add(m7551mb);
        }
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry != null) {
                    arrayList.add(new HttpHeader(entry.getKey(), entry.getValue()));
                }
            }
        }
        String m7090mb = C10047b.m7090mb(String.valueOf(this.f19042ox.f19103ox.getId()), this.f19042ox.f19103ox.getNotificationJumpUrl(), this.f19042ox.f19103ox.isShowToast(), String.valueOf(this.f19042ox.f19103ox.getModelType()));
        DownloadSetting m7067ox = C10049hj.m7067ox(this.f19042ox.f19103ox);
        JSONObject m7072mb = C10049hj.m7072mb(this.f19042ox.f19103ox);
        if (!this.f19042ox.f19101hj.enableAH()) {
            m7072mb = C10050jb.m7042mb(m7072mb);
            C10050jb.m7040mb(m7072mb, "ah_plans", new JSONArray());
        }
        this.f19042ox.f19103ox.getExecutorGroup();
        int i = (this.f19042ox.f19103ox.isAd() || C9939ww.m7378ox(this.f19042ox.f19103ox)) ? 4 : 4;
        String m7552mb = m7552mb(m7067ox);
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(DownloadComponentManager.getDownloadId(this.f19042ox.f19103ox.getDownloadUrl(), m7552mb));
        if (downloadInfo != null && 3 == this.f19042ox.f19103ox.getModelType()) {
            downloadInfo.setFirstDownload(true);
        }
        C10149u m6653lc = new C10149u(context, this.f19042ox.f19103ox.getDownloadUrl()).m6623ox(this.f19042ox.f19103ox.getBackupUrls()).m6640mb(this.f19042ox.f19103ox.getName()).m6670hj(m7090mb).m6639mb(arrayList).m6637mb(this.f19042ox.f19103ox.isShowNotification()).m6687b(this.f19042ox.f19103ox.isNeedWifi()).m6624ox(this.f19042ox.f19103ox.getFileName()).m6688b(m7552mb).m6600x(this.f19042ox.f19103ox.getAppIcon()).m6657ko(this.f19042ox.f19103ox.getMd5()).m6651lz(this.f19042ox.f19103ox.getSdkMonitorScene()).m6646mb(this.f19042ox.f19103ox.getExpectFileLength()).m6641mb(iDownloadListener).m6662je(this.f19042ox.f19103ox.needIndependentProcess() || m7067ox.optInt("need_independent_process", 0) == 1).m6642mb(this.f19042ox.f19103ox.getDownloadFileUriProvider()).m6622ox(this.f19042ox.f19103ox.autoInstallWithoutNotification()).m6610u(this.f19042ox.f19103ox.getPackageName()).m6671hj(1000).m6675h(100).m6638mb(m7072mb).m6650lz(true).m6599x(true).m6625ox(m7067ox.optInt("retry_count", 5)).m6689b(m7067ox.optInt("backup_url_retry_count", 0)).m6599x(true).m6632nk(m7067ox.optInt("need_head_connection", 0) == 1).m6669hj(m7067ox.optInt("need_https_to_http_retry", 0) == 1).m6602ww(m7067ox.optInt("need_chunk_downgrade_retry", 1) == 1).m6656ko(m7067ox.optInt("need_retry_delay", 0) == 1).m6603ww(m7067ox.optString("retry_delay_time_array")).m6664jb(m7067ox.optInt("need_reuse_runnable", 0) == 1).m6611u(i).m6666io(this.f19042ox.f19103ox.isAutoInstall()).m6653lc(this.f19042ox.f19103ox.distinctDir());
        if (!TextUtils.isEmpty(this.f19042ox.f19103ox.getMimeType())) {
            m6653lc.m6674h(this.f19042ox.f19103ox.getMimeType());
        } else {
            m6653lc.m6674h("application/vnd.android.package-archive");
        }
        if (m7067ox.optInt("notification_opt_2", 0) == 1) {
            m6653lc.m6637mb(false);
            m6653lc.m6622ox(true);
        }
        C9935mb c9935mb = null;
        if (m7067ox.optInt("clear_space_use_disk_handler", 0) == 1) {
            c9935mb = new C9935mb();
            m6653lc.m6643mb(c9935mb);
        }
        int m7383mb = C9939ww.m7383mb(this.f19042ox, m7569b(), m6653lc);
        if (c9935mb != null) {
            c9935mb.m7406mb(m7383mb);
        }
        return m7383mb;
    }

    /* renamed from: mb */
    private String m7552mb(DownloadSetting downloadSetting) {
        if (!TextUtils.isEmpty(this.f19042ox.f19103ox.getFilePath())) {
            return this.f19042ox.f19103ox.getFilePath();
        }
        DownloadInfo m6810mb = C10112hj.m6786x().m6810mb(C9940x.getContext(), this.f19042ox.f19103ox.getDownloadUrl());
        boolean m6992ox = C10059lz.m6992ox("android.permission.WRITE_EXTERNAL_STORAGE");
        String m7548ox = m7548ox();
        if (m6810mb != null && !TextUtils.isEmpty(m6810mb.getSavePath())) {
            String savePath = m6810mb.getSavePath();
            if (m6992ox || savePath.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
                return savePath;
            }
            try {
                if (!TextUtils.isEmpty(m7548ox)) {
                    if (savePath.startsWith(m7548ox)) {
                        return savePath;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(m6810mb.getId());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("ttdownloader_code", Integer.valueOf(m6992ox ? 1 : 2));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AdEventHandler.m7315mb().m7298mb("label_external_permission", jSONObject, this.f19042ox);
        String str = null;
        try {
            str = C10085b.m6899ox();
        } catch (Exception unused) {
        }
        int m7070mb = C10049hj.m7070mb(downloadSetting);
        if (m7070mb != 0) {
            if (m7070mb == 4 || (!m6992ox && m7070mb == 2)) {
                File filesDir = C9940x.getContext().getFilesDir();
                if (!filesDir.exists()) {
                    filesDir.mkdirs();
                }
                return filesDir.exists() ? filesDir.getAbsolutePath() : str;
            } else if ((m7070mb == 3 || (!m6992ox && m7070mb == 1)) && !TextUtils.isEmpty(m7548ox)) {
                return m7548ox;
            }
        }
        return str;
    }

    @Nullable
    /* renamed from: ox */
    public String m7548ox() {
        File externalFilesDir = C9940x.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir != null) {
            if (!externalFilesDir.exists()) {
                externalFilesDir.mkdirs();
            }
            if (externalFilesDir.exists()) {
                return externalFilesDir.getAbsolutePath();
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7553mb(DownloadInfo downloadInfo, boolean z) {
        if (this.f19042ox.f19103ox == null || downloadInfo == null || downloadInfo.getId() == 0) {
            return;
        }
        int status = downloadInfo.getStatus();
        if (status == -1 || status == -4) {
            AdEventHandler.m7315mb().m7314mb(this.f19041mb, 2);
        } else if (C9939ww.m7386mb(this.f19042ox.f19103ox)) {
            AdEventHandler.m7315mb().m7314mb(this.f19041mb, 2);
        } else if (z && C9970ox.m7289mb().m7291b() && (status == -2 || status == -3)) {
            AdEventHandler.m7315mb().m7314mb(this.f19041mb, 2);
        }
        switch (status) {
            case -4:
            case -1:
                m7563mb();
                C9923u.m7451mb().m7445mb(new C9837ox(this.f19042ox.f19103ox, this.f19042ox.f19100b, this.f19042ox.f19101hj, downloadInfo.getId()));
                return;
            case -3:
                if (C10050jb.m7050mb(this.f19042ox.f19103ox)) {
                    C9971b.m7285mb().m7278ox("SUCCESSED isInstalledApp");
                    return;
                }
                AdEventHandler.m7315mb().m7313mb(this.f19041mb, 5, downloadInfo);
                if (z && C9970ox.m7289mb().m7287ox() && !C9970ox.m7289mb().m7286ox(this.f19041mb, this.f19042ox.f19103ox.getLogExtra())) {
                    AdEventHandler.m7315mb().m7314mb(this.f19041mb, 2);
                    return;
                }
                return;
            case -2:
                AdEventHandler.m7315mb().m7313mb(this.f19041mb, 4, downloadInfo);
                if (z && C9970ox.m7289mb().m7287ox() && !C9970ox.m7289mb().m7286ox(this.f19041mb, this.f19042ox.f19103ox.getLogExtra())) {
                    AdEventHandler.m7315mb().m7314mb(this.f19041mb, 2);
                    return;
                }
                return;
            case 0:
            case 6:
            default:
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                AdEventHandler.m7315mb().m7313mb(this.f19041mb, 3, downloadInfo);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7554mb(DownloadInfo downloadInfo, DownloadShortInfo downloadShortInfo, List<DownloadStatusChangeListener> list) {
        int i;
        if (list.isEmpty()) {
            return;
        }
        if (downloadInfo == null || downloadShortInfo == null) {
            for (DownloadStatusChangeListener downloadStatusChangeListener : list) {
                downloadStatusChangeListener.onIdle();
            }
            return;
        }
        try {
            i = downloadInfo.getTotalBytes() > 0 ? (int) ((downloadInfo.getCurBytes() * 100) / downloadInfo.getTotalBytes()) : 0;
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        int i2 = i >= 0 ? i : 0;
        downloadShortInfo.updateFromNewDownloadInfo(downloadInfo);
        C9904lz.m7534mb(downloadShortInfo);
        for (DownloadStatusChangeListener downloadStatusChangeListener2 : list) {
            switch (downloadInfo.getStatus()) {
                case -4:
                case 0:
                    if (C10050jb.m7050mb(this.f19042ox.f19103ox)) {
                        downloadShortInfo.status = -3;
                        downloadStatusChangeListener2.onInstalled(downloadShortInfo);
                        break;
                    } else {
                        downloadStatusChangeListener2.onIdle();
                        break;
                    }
                case -3:
                    if (C10050jb.m7050mb(this.f19042ox.f19103ox)) {
                        downloadStatusChangeListener2.onInstalled(downloadShortInfo);
                        break;
                    } else {
                        downloadStatusChangeListener2.onDownloadFinished(downloadShortInfo);
                        break;
                    }
                case -2:
                    downloadStatusChangeListener2.onDownloadPaused(downloadShortInfo, C9904lz.m7536mb(downloadInfo.getId(), i2));
                    break;
                case -1:
                    downloadStatusChangeListener2.onDownloadFailed(downloadShortInfo);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                    downloadStatusChangeListener2.onDownloadActive(downloadShortInfo, C9904lz.m7536mb(downloadInfo.getId(), i2));
                    break;
                case 11:
                    if (downloadStatusChangeListener2 instanceof InterfaceC9816mb) {
                        ((InterfaceC9816mb) downloadStatusChangeListener2).m7911mb(downloadInfo);
                        break;
                    } else {
                        downloadStatusChangeListener2.onDownloadActive(downloadShortInfo, C9904lz.m7536mb(downloadInfo.getId(), i2));
                        break;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.downloadlib.addownload.ko$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C9902mb extends AbsDownloadExtListener {

        /* renamed from: mb */
        private HandlerC10051je f19048mb;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C9902mb(HandlerC10051je handlerC10051je) {
            this.f19048mb = handlerC10051je;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onPrepare(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, 1);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onStart(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, 2);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onProgress(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, 4);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onPause(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, -2);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onSuccessed(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, -3);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
            m7539mb(downloadInfo, -1);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadListener, com.p319ss.android.socialbase.downloader.depend.IDownloadListener
        public void onCanceled(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, -4);
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.AbsDownloadExtListener, com.p319ss.android.socialbase.downloader.depend.IDownloadExtListener
        public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
            m7539mb(downloadInfo, 11);
        }

        /* renamed from: mb */
        private void m7539mb(DownloadInfo downloadInfo, int i) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            obtain.obj = downloadInfo;
            obtain.arg1 = i;
            this.f19048mb.sendMessage(obtain);
        }
    }

    @NonNull
    /* renamed from: mb */
    public static List<DownloadStatusChangeListener> m7550mb(Map<Integer, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (map == null || map.isEmpty()) {
            return arrayList;
        }
        for (Object obj : map.values()) {
            if (obj instanceof DownloadStatusChangeListener) {
                arrayList.add((DownloadStatusChangeListener) obj);
            } else if (obj instanceof SoftReference) {
                SoftReference softReference = (SoftReference) obj;
                if (softReference.get() instanceof DownloadStatusChangeListener) {
                    arrayList.add((DownloadStatusChangeListener) softReference.get());
                }
            }
        }
        return arrayList;
    }

    @NonNull
    /* renamed from: ox */
    public static List<InterfaceC9816mb> m7544ox(Map<Integer, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (map == null || map.isEmpty()) {
            return arrayList;
        }
        for (Object obj : map.values()) {
            if (obj instanceof InterfaceC9816mb) {
                arrayList.add((InterfaceC9816mb) obj);
            } else if (obj instanceof SoftReference) {
                SoftReference softReference = (SoftReference) obj;
                if (softReference.get() instanceof InterfaceC9816mb) {
                    arrayList.add((InterfaceC9816mb) softReference.get());
                }
            }
        }
        return arrayList;
    }

    /* renamed from: mb */
    private HttpHeader m7551mb(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new HttpHeader("clickid", new JSONObject(str).optString("clickid"));
        } catch (JSONException e) {
            C9940x.m7363m().mo7282mb(e, "parseLogExtra Error");
            return null;
        }
    }
}
