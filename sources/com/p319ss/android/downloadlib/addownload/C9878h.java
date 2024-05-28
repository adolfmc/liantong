package com.p319ss.android.downloadlib.addownload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.C9806mb;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.config.InterfaceC9795gm;
import com.p319ss.android.download.api.config.OnItemClickListener;
import com.p319ss.android.download.api.download.C9813b;
import com.p319ss.android.download.api.download.C9818ox;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import com.p319ss.android.download.api.model.DownloadShortInfo;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9898ko;
import com.p319ss.android.downloadlib.addownload.compliance.C9873ox;
import com.p319ss.android.downloadlib.addownload.model.C9915b;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9920ko;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.downloadlib.addownload.p325b.C9851u;
import com.p319ss.android.downloadlib.addownload.p325b.InterfaceC9843b;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.p329b.C9962ww;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10059lz;
import com.p319ss.android.downloadlib.utils.C10064ox;
import com.p319ss.android.downloadlib.utils.C10069ww;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.downloadlib.utils.HandlerC10051je;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.downloader.depend.IDownloadListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9878h implements InterfaceC9938u, HandlerC10051je.InterfaceC10052mb {

    /* renamed from: mb */
    private static final String f18979mb = "h";

    /* renamed from: e */
    private SoftReference<OnItemClickListener> f18981e;

    /* renamed from: gm */
    private SoftReference<IDownloadButtonClickListener> f18982gm;

    /* renamed from: h */
    private WeakReference<Context> f18983h;

    /* renamed from: jb */
    private boolean f18986jb;

    /* renamed from: je */
    private long f18987je;

    /* renamed from: ko */
    private DownloadShortInfo f18988ko;

    /* renamed from: l */
    private boolean f18989l;

    /* renamed from: lz */
    private AsyncTaskC9884b f18991lz;

    /* renamed from: ww */
    private DownloadInfo f18997ww;

    /* renamed from: ox */
    private final HandlerC10051je f18995ox = new HandlerC10051je(Looper.getMainLooper(), this);

    /* renamed from: u */
    private final Map<Integer, Object> f18996u = new ConcurrentHashMap();

    /* renamed from: x */
    private final IDownloadListener f18998x = new C9898ko.C9902mb(this.f18995ox);

    /* renamed from: nk */
    private long f18993nk = -1;

    /* renamed from: o */
    private DownloadModel f18994o = null;

    /* renamed from: lc */
    private DownloadEventConfig f18990lc = null;

    /* renamed from: io */
    private DownloadController f18985io = null;

    /* renamed from: b */
    private C9898ko f18980b = new C9898ko(this);

    /* renamed from: hj */
    private C9887hj f18984hj = new C9887hj(this.f18995ox);

    /* renamed from: m */
    private final boolean f18992m = DownloadSetting.obtainGlobal().optBugFix("ttdownloader_callback_twice");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.h$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9885mb {
        /* renamed from: mb */
        void mo7596mb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.h$ox */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9886ox {
        /* renamed from: mb */
        void mo7575mb(long j);
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public C9878h mo7392ox(Context context) {
        if (context != null) {
            this.f18983h = new WeakReference<>(context);
        }
        C9940x.m7347ox(context);
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public C9878h mo7393ox(int i, DownloadStatusChangeListener downloadStatusChangeListener) {
        if (downloadStatusChangeListener != null) {
            if (C9940x.m7364lz().optInt("back_use_softref_listener") == 1) {
                this.f18996u.put(Integer.valueOf(i), downloadStatusChangeListener);
            } else {
                this.f18996u.put(Integer.valueOf(i), new SoftReference(downloadStatusChangeListener));
            }
        }
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public C9878h mo7389ox(DownloadModel downloadModel) {
        if (downloadModel != null) {
            if (downloadModel.isAd()) {
                if (downloadModel.getId() <= 0 || TextUtils.isEmpty(downloadModel.getLogExtra())) {
                    C9971b.m7285mb().m7284mb("setDownloadModel ad error");
                }
            } else if (downloadModel.getId() == 0 && (downloadModel instanceof AdDownloadModel)) {
                C9971b.m7285mb().m7281mb(false, "setDownloadModel id=0");
                if (DownloadSetting.obtainGlobal().optBugFix("fix_model_id")) {
                    ((AdDownloadModel) downloadModel).setId(downloadModel.getDownloadUrl().hashCode());
                }
            }
            C9923u.m7451mb().m7446mb(downloadModel);
            this.f18993nk = downloadModel.getId();
            this.f18994o = downloadModel;
            if (C9939ww.m7386mb(downloadModel)) {
                ((AdDownloadModel) downloadModel).setExtraValue(3L);
                C9837ox m7452hj = C9923u.m7451mb().m7452hj(this.f18993nk);
                if (m7452hj != null && m7452hj.mo7485je() != 3) {
                    m7452hj.m7806h(3L);
                    C9926ww.m7430mb().m7429mb(m7452hj);
                }
            }
        }
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public C9878h mo7391ox(DownloadController downloadController) {
        JSONObject extra;
        this.f18985io = downloadController;
        if (C10049hj.m7067ox(this.f18994o).optInt("force_auto_open") == 1) {
            m7610nk().setLinkMode(1);
        }
        if (DownloadSetting.obtainGlobal().optBugFix("fix_show_dialog") && (extra = this.f18994o.getExtra()) != null && extra.optInt("subprocess") > 0) {
            m7610nk().setEnableNewActivity(false);
        }
        C9923u.m7451mb().m7448mb(this.f18993nk, m7610nk());
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public C9878h mo7390ox(DownloadEventConfig downloadEventConfig) {
        this.f18990lc = downloadEventConfig;
        this.f18989l = m7629je().getDownloadScene() == 0;
        C9923u.m7451mb().m7447mb(this.f18993nk, m7629je());
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public InterfaceC9938u mo7397mb(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            this.f18981e = null;
        } else {
            this.f18981e = new SoftReference<>(onItemClickListener);
        }
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public void mo7401mb() {
        this.f18986jb = true;
        C9923u.m7451mb().m7447mb(this.f18993nk, m7629je());
        C9923u.m7451mb().m7448mb(this.f18993nk, m7610nk());
        this.f18980b.m7561mb(this.f18993nk);
        m7631io();
        if (C9940x.m7364lz().optInt("enable_empty_listener", 1) == 1 && this.f18996u.get(Integer.MIN_VALUE) == null) {
            mo7393ox(Integer.MIN_VALUE, new C9806mb());
        }
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public boolean mo7400mb(int i) {
        if (i == 0) {
            this.f18996u.clear();
        } else {
            this.f18996u.remove(Integer.valueOf(i));
        }
        if (this.f18996u.isEmpty()) {
            this.f18986jb = false;
            this.f18987je = System.currentTimeMillis();
            if (this.f18997ww != null) {
                Downloader.getInstance(C9940x.getContext()).removeTaskMainListener(this.f18997ww.getId());
            }
            AsyncTaskC9884b asyncTaskC9884b = this.f18991lz;
            if (asyncTaskC9884b != null && asyncTaskC9884b.getStatus() != AsyncTask.Status.FINISHED) {
                this.f18991lz.cancel(true);
            }
            this.f18980b.m7555mb(this.f18997ww);
            String str = f18979mb;
            StringBuilder sb = new StringBuilder();
            sb.append("onUnbind removeCallbacksAndMessages, downloadUrl:");
            DownloadInfo downloadInfo = this.f18997ww;
            sb.append(downloadInfo == null ? "" : downloadInfo.getUrl());
            C10070x.m6967mb(str, sb.toString(), null);
            this.f18995ox.removeCallbacksAndMessages(null);
            this.f18988ko = null;
            this.f18997ww = null;
            return true;
        }
        if (this.f18996u.size() == 1 && this.f18996u.containsKey(Integer.MIN_VALUE)) {
            this.f18980b.m7545ox(this.f18997ww);
        }
        return false;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public void mo7396mb(boolean z) {
        if (this.f18997ww != null) {
            if (z) {
                InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
                if (m6792ox != null) {
                    m6792ox.mo6879mb(this.f18997ww);
                }
                Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(this.f18997ww.getId(), true);
                return;
            }
            Intent intent = new Intent(C9940x.getContext(), DownloadHandlerService.class);
            intent.setAction("android.ss.intent.action.DOWNLOAD_DELETE");
            intent.putExtra("extra_click_download_ids", this.f18997ww.getId());
            C9940x.getContext().startService(intent);
        }
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: ox */
    public boolean mo7395ox() {
        return this.f18986jb;
    }

    /* renamed from: b */
    public boolean m7641b() {
        DownloadInfo downloadInfo = this.f18997ww;
        return (downloadInfo == null || downloadInfo.getStatus() == 0) ? false : true;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: hj */
    public long mo7402hj() {
        return this.f18987je;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public InterfaceC9938u mo7399mb(long j) {
        if (j != 0) {
            DownloadModel m7449mb = C9923u.m7451mb().m7449mb(j);
            if (m7449mb != null) {
                this.f18994o = m7449mb;
                this.f18993nk = j;
                this.f18980b.m7561mb(this.f18993nk);
            }
        } else {
            C9971b.m7285mb().m7281mb(false, "setModelId");
        }
        return this;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: ox */
    public void mo7394ox(int i) {
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("error actionType");
        }
        this.f18980b.m7561mb(this.f18993nk);
        if (!C9923u.m7451mb().m7453h(this.f18993nk).m7484jq()) {
            C9971b.m7285mb().m7284mb("handleDownload ModelBox !isStrictValid");
        }
        if (this.f18980b.m7560mb(getContext(), i, this.f18989l)) {
            return;
        }
        boolean m7640b = m7640b(i);
        switch (i) {
            case 1:
                if (m7640b) {
                    return;
                }
                String str = f18979mb;
                C10070x.m6967mb(str, "handleDownload id:" + this.f18993nk + ",pIC:", null);
                m7638b(true);
                return;
            case 2:
                if (m7640b) {
                    return;
                }
                String str2 = f18979mb;
                C10070x.m6967mb(str2, "handleDownload id:" + this.f18993nk + ",pBC:", null);
                m7606ox(true);
                return;
            default:
                return;
        }
    }

    /* renamed from: h */
    public boolean m7636h() {
        return C9940x.m7364lz().optInt("quick_app_enable_switch", 0) == 0 && this.f18994o.getQuickAppModel() != null && !TextUtils.isEmpty(this.f18994o.getQuickAppModel().m7882mb()) && C9841b.m7703mb(this.f18997ww) && C10050jb.m7053mb(getContext(), new Intent("android.intent.action.VIEW", Uri.parse(this.f18994o.getQuickAppModel().m7882mb())));
    }

    /* renamed from: b */
    private boolean m7640b(int i) {
        if (m7636h()) {
            int i2 = -1;
            String m7882mb = this.f18994o.getQuickAppModel().m7882mb();
            switch (i) {
                case 1:
                    i2 = 5;
                    break;
                case 2:
                    i2 = 4;
                    break;
            }
            DownloadModel downloadModel = this.f18994o;
            if (downloadModel instanceof AdDownloadModel) {
                ((AdDownloadModel) downloadModel).setFunnelType(3);
            }
            boolean m6978b = C10069ww.m6978b(C9940x.getContext(), m7882mb);
            if (m6978b) {
                AdEventHandler.m7315mb().m7314mb(this.f18993nk, i);
                Message obtain = Message.obtain();
                obtain.what = i2;
                obtain.obj = Long.valueOf(this.f18994o.getId());
                C9841b.m7707mb().m7705mb(this, i2, this.f18994o);
            } else {
                AdEventHandler.m7315mb().m7311mb(this.f18993nk, false, 0);
            }
            return m6978b;
        }
        return false;
    }

    /* renamed from: ox */
    public void m7606ox(boolean z) {
        m7634h(z);
    }

    /* renamed from: jb */
    private void m7630jb() {
        SoftReference<OnItemClickListener> softReference = this.f18981e;
        if (softReference != null && softReference.get() != null) {
            this.f18981e.get().onItemClick(this.f18994o, m7629je(), m7610nk());
            this.f18981e = null;
            return;
        }
        C9940x.m7348ox().mo7343mb(getContext(), this.f18994o, m7610nk(), m7629je());
    }

    /* renamed from: b */
    public void m7638b(boolean z) {
        if (z) {
            AdEventHandler.m7315mb().m7314mb(this.f18993nk, 1);
        }
        m7609o();
    }

    /* renamed from: h */
    private void m7634h(boolean z) {
        if (C10049hj.m7067ox(this.f18994o).optInt("notification_opt_2") == 1 && this.f18997ww != null) {
            DownloadNotificationManager.getInstance().cancelNotification(this.f18997ww.getId());
        }
        m7603u(z);
    }

    /* renamed from: u */
    public void m7605u() {
        this.f18995ox.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.h.1
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadStatusChangeListener downloadStatusChangeListener : C9898ko.m7550mb(C9878h.this.f18996u)) {
                    downloadStatusChangeListener.onInstalled(C9878h.this.m7637e());
                }
            }
        });
    }

    @Override // com.p319ss.android.downloadlib.utils.HandlerC10051je.InterfaceC10052mb
    /* renamed from: mb */
    public void mo7026mb(Message message) {
        if (message != null && this.f18986jb && message.what == 3) {
            this.f18997ww = (DownloadInfo) message.obj;
            this.f18980b.m7558mb(message, m7637e(), this.f18996u);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getContext() {
        WeakReference<Context> weakReference = this.f18983h;
        if (weakReference != null && weakReference.get() != null) {
            return this.f18983h.get();
        }
        return C9940x.getContext();
    }

    @NonNull
    /* renamed from: je */
    private DownloadEventConfig m7629je() {
        DownloadEventConfig downloadEventConfig = this.f18990lc;
        return downloadEventConfig == null ? new C9813b.C9815mb().m7919mb() : downloadEventConfig;
    }

    @NonNull
    /* renamed from: nk */
    private DownloadController m7610nk() {
        if (this.f18985io == null) {
            this.f18985io = new C9818ox();
        }
        return this.f18985io;
    }

    /* renamed from: o */
    private void m7609o() {
        C10070x.m6967mb(f18979mb, "pICD", null);
        if (this.f18980b.m7564hj(this.f18997ww)) {
            C10070x.m6967mb(f18979mb, "pICD BC", null);
            m7603u(false);
            return;
        }
        C10070x.m6967mb(f18979mb, "pICD IC", null);
        m7630jb();
    }

    /* renamed from: u */
    private void m7603u(final boolean z) {
        DownloadModel downloadModel;
        C10070x.m6967mb(f18979mb, "pBCD", null);
        if (m7625lc()) {
            C9916h m7453h = C9923u.m7451mb().m7453h(this.f18993nk);
            if (this.f18989l) {
                if (m7624lz()) {
                    if (m7632hj(false) && m7453h.f19101hj != null && m7453h.f19101hj.isAutoDownloadOnCardShow()) {
                        m7611mb(z, true);
                        return;
                    }
                    return;
                }
                m7611mb(z, true);
                return;
            } else if (this.f18994o.isAd() && m7453h.f19101hj != null && m7453h.f19101hj.enableShowComplianceDialog() && m7453h.f19103ox != null && C9873ox.m7653mb().m7649mb(m7453h.f19103ox) && C9873ox.m7653mb().m7647mb(m7453h)) {
                return;
            } else {
                m7611mb(z, true);
                return;
            }
        }
        String str = f18979mb;
        C10070x.m6967mb(str, "pBCD continue download, status:" + this.f18997ww.getStatus(), null);
        DownloadInfo downloadInfo = this.f18997ww;
        if (downloadInfo != null && (downloadModel = this.f18994o) != null) {
            downloadInfo.setOnlyWifi(downloadModel.isNeedWifi());
        }
        final int status = this.f18997ww.getStatus();
        final int id = this.f18997ww.getId();
        final C9837ox m7442mb = C9923u.m7451mb().m7442mb(this.f18997ww);
        if (status == -2 || status == -1) {
            this.f18980b.m7553mb(this.f18997ww, z);
            if (m7442mb != null) {
                m7442mb.m7738ww(System.currentTimeMillis());
                m7442mb.m7778lz(this.f18997ww.getCurBytes());
            }
            this.f18997ww.setDownloadFromReserveWifi(false);
            this.f18984hj.m7583mb(new C9916h(this.f18993nk, this.f18994o, m7629je(), m7610nk()));
            this.f18984hj.m7591mb(id, this.f18997ww.getCurBytes(), this.f18997ww.getTotalBytes(), new InterfaceC9885mb() { // from class: com.ss.android.downloadlib.addownload.h.2
                @Override // com.p319ss.android.downloadlib.addownload.C9878h.InterfaceC9885mb
                /* renamed from: mb */
                public void mo7596mb() {
                    if (C9878h.this.f18984hj.m7593mb()) {
                        return;
                    }
                    C9878h c9878h = C9878h.this;
                    c9878h.m7622mb(id, status, c9878h.f18997ww);
                }
            });
        } else if (C9896je.m7572mb(status)) {
            if (this.f18994o.enablePause()) {
                this.f18984hj.m7578mb(true);
                C9962ww.m7325mb().m7319ox(C9923u.m7451mb().m7452hj(this.f18993nk));
                C9851u.m7689mb().m7688mb(m7442mb, status, new InterfaceC9843b() { // from class: com.ss.android.downloadlib.addownload.h.3
                    @Override // com.p319ss.android.downloadlib.addownload.p325b.InterfaceC9843b
                    /* renamed from: mb */
                    public void mo7599mb(C9837ox c9837ox) {
                        if (C9878h.this.f18997ww == null && DownloadSetting.obtainGlobal().optBugFix("fix_handle_pause")) {
                            C9878h.this.f18997ww = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(id);
                        }
                        C9878h.this.f18980b.m7553mb(C9878h.this.f18997ww, z);
                        if (C9878h.this.f18997ww != null && DownloadUtils.isWifi(C9940x.getContext()) && C9878h.this.f18997ww.isPauseReserveOnWifi()) {
                            C9878h.this.f18997ww.stopPauseReserveOnWifi();
                            AdEventHandler.m7315mb().m7294ox("pause_reserve_wifi_cancel_on_wifi", m7442mb);
                            return;
                        }
                        C9878h c9878h = C9878h.this;
                        c9878h.m7622mb(id, status, c9878h.f18997ww);
                    }
                });
            }
        } else {
            this.f18980b.m7553mb(this.f18997ww, z);
            m7622mb(id, status, this.f18997ww);
        }
    }

    /* renamed from: mb */
    public void m7611mb(boolean z, final boolean z2) {
        if (z) {
            AdEventHandler.m7315mb().m7314mb(this.f18993nk, 2);
        }
        if (!C10059lz.m6992ox("android.permission.WRITE_EXTERNAL_STORAGE") && !m7610nk().enableNewActivity()) {
            this.f18994o.setFilePath(this.f18980b.m7548ox());
        }
        if (C10049hj.m7080b(this.f18994o) == 0) {
            C10070x.m6967mb(f18979mb, "pBCD not start", null);
            this.f18980b.m7557mb(new InterfaceC9795gm() { // from class: com.ss.android.downloadlib.addownload.h.4
                @Override // com.p319ss.android.download.api.config.InterfaceC9795gm
                /* renamed from: mb */
                public void mo7541mb() {
                    C10070x.m6967mb(C9878h.f18979mb, "pBCD start download", null);
                    C9878h.this.m7626ko(z2);
                }

                @Override // com.p319ss.android.download.api.config.InterfaceC9795gm
                /* renamed from: mb */
                public void mo7540mb(String str) {
                    C10070x.m6967mb(C9878h.f18979mb, "pBCD onDenied", null);
                }
            });
            return;
        }
        m7626ko(z2);
    }

    /* renamed from: lc */
    private boolean m7625lc() {
        if (DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) {
            DownloadInfo downloadInfo = this.f18997ww;
            if (downloadInfo == null) {
                return true;
            }
            if ((downloadInfo.getStatus() == -3 && this.f18997ww.getCurBytes() <= 0) || this.f18997ww.getStatus() == 0 || this.f18997ww.getStatus() == -4) {
                return true;
            }
            return DownloadUtils.isDownloadSuccessAndFileNotExist(this.f18997ww.getStatus(), this.f18997ww.getSavePath(), this.f18997ww.getName());
        }
        DownloadInfo downloadInfo2 = this.f18997ww;
        if (downloadInfo2 == null) {
            return true;
        }
        return !(downloadInfo2.getStatus() == -3 || Downloader.getInstance(C9940x.getContext()).canResume(this.f18997ww.getId())) || this.f18997ww.getStatus() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m7622mb(int i, int i2, @NonNull DownloadInfo downloadInfo) {
        if (DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) {
            if (i2 != -3 && !DownloadProcessDispatcher.getInstance().canResume(i)) {
                m7611mb(false, false);
                return;
            } else {
                C10112hj.m6786x().m6811mb(C9940x.getContext(), i, i2);
                return;
            }
        }
        C10112hj.m6786x().m6811mb(C9940x.getContext(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ko */
    public void m7626ko(final boolean z) {
        this.f18984hj.m7583mb(new C9916h(this.f18993nk, this.f18994o, m7629je(), m7610nk()));
        this.f18984hj.m7591mb(0, 0L, 0L, new InterfaceC9885mb() { // from class: com.ss.android.downloadlib.addownload.h.5
            @Override // com.p319ss.android.downloadlib.addownload.C9878h.InterfaceC9885mb
            /* renamed from: mb */
            public void mo7596mb() {
                if (C9878h.this.f18984hj.m7593mb()) {
                    return;
                }
                C9878h.this.m7601ww(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ww */
    public void m7601ww(boolean z) {
        for (DownloadStatusChangeListener downloadStatusChangeListener : C9898ko.m7550mb(this.f18996u)) {
            downloadStatusChangeListener.onDownloadStart(this.f18994o, m7610nk());
        }
        int m7559mb = this.f18980b.m7559mb(C9940x.getContext(), this.f18998x);
        String str = f18979mb;
        C10070x.m6967mb(str, "beginDown id:" + m7559mb, null);
        if (m7559mb != 0) {
            if (this.f18997ww != null && !DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) {
                this.f18980b.m7553mb(this.f18997ww, false);
            } else if (z) {
                this.f18980b.m7563mb();
            }
        } else {
            DownloadInfo build = new DownloadInfo.Builder(this.f18994o.getDownloadUrl()).build();
            build.setStatus(-1);
            m7612mb(build);
            AdEventHandler.m7315mb().m7312mb(this.f18993nk, new BaseException(2, "start download failed, id=0"));
            C9971b.m7285mb().m7278ox("beginDown");
        }
        if (this.f18980b.m7549mb(m7641b())) {
            String str2 = f18979mb;
            C10070x.m6967mb(str2, "beginDown IC id:" + m7559mb, null);
            m7630jb();
        }
    }

    /* renamed from: ko */
    public void m7628ko() {
        if (this.f18996u.size() == 0) {
            return;
        }
        for (DownloadStatusChangeListener downloadStatusChangeListener : C9898ko.m7550mb(this.f18996u)) {
            downloadStatusChangeListener.onIdle();
        }
        DownloadInfo downloadInfo = this.f18997ww;
        if (downloadInfo != null) {
            downloadInfo.setStatus(-4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.downloadlib.addownload.h$b */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC9884b extends AsyncTask<String, Void, DownloadInfo> {
        private AsyncTaskC9884b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: mb */
        public DownloadInfo doInBackground(String... strArr) {
            DownloadInfo downloadInfo = null;
            if (strArr == null || (strArr.length >= 1 && TextUtils.isEmpty(strArr[0]))) {
                return null;
            }
            String str = strArr[0];
            if (C9878h.this.f18994o != null && !TextUtils.isEmpty(C9878h.this.f18994o.getFilePath())) {
                downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(str, C9878h.this.f18994o.getFilePath());
            }
            return downloadInfo == null ? C10112hj.m6786x().m6810mb(C9940x.getContext(), str) : downloadInfo;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: mb */
        public void onPostExecute(DownloadInfo downloadInfo) {
            super.onPostExecute(downloadInfo);
            if (isCancelled() || C9878h.this.f18994o == null) {
                return;
            }
            try {
                C9915b m7044mb = C10050jb.m7044mb(C9878h.this.f18994o.getPackageName(), C9878h.this.f18994o.getVersionCode(), C9878h.this.f18994o.getVersionName());
                C9920ko.m7461mb().m7460mb(C9878h.this.f18994o.getVersionCode(), m7044mb.m7496ox(), C9923u.m7451mb().m7442mb(downloadInfo));
                boolean m7498mb = m7044mb.m7498mb();
                if (downloadInfo != null && downloadInfo.getId() != 0 && (m7498mb || !Downloader.getInstance(C9940x.getContext()).isDownloadSuccessAndFileNotExist(downloadInfo))) {
                    Downloader.getInstance(C9940x.getContext()).removeTaskMainListener(downloadInfo.getId());
                    if (C9878h.this.f18997ww == null || C9878h.this.f18997ww.getStatus() != -4) {
                        C9878h.this.f18997ww = downloadInfo;
                        if (C9878h.this.f18992m) {
                            Downloader.getInstance(C9940x.getContext()).setMainThreadListener(C9878h.this.f18997ww.getId(), C9878h.this.f18998x, false);
                        } else {
                            Downloader.getInstance(C9940x.getContext()).setMainThreadListener(C9878h.this.f18997ww.getId(), C9878h.this.f18998x);
                        }
                    } else {
                        C9878h.this.f18997ww = null;
                    }
                    C9878h.this.f18980b.m7554mb(C9878h.this.f18997ww, C9878h.this.m7637e(), C9898ko.m7550mb(C9878h.this.f18996u));
                } else {
                    if (downloadInfo != null && Downloader.getInstance(C9940x.getContext()).isDownloadSuccessAndFileNotExist(downloadInfo)) {
                        DownloadNotificationManager.getInstance().cancelNotification(downloadInfo.getId());
                        C9878h.this.f18997ww = null;
                    }
                    if (C9878h.this.f18997ww != null) {
                        Downloader.getInstance(C9940x.getContext()).removeTaskMainListener(C9878h.this.f18997ww.getId());
                        if (C9878h.this.f18992m) {
                            Downloader.getInstance(C9878h.this.getContext()).setMainThreadListener(C9878h.this.f18997ww.getId(), C9878h.this.f18998x, false);
                        } else {
                            Downloader.getInstance(C9878h.this.getContext()).setMainThreadListener(C9878h.this.f18997ww.getId(), C9878h.this.f18998x);
                        }
                    }
                    if (!m7498mb) {
                        for (DownloadStatusChangeListener downloadStatusChangeListener : C9898ko.m7550mb(C9878h.this.f18996u)) {
                            downloadStatusChangeListener.onIdle();
                        }
                        C9878h.this.f18997ww = null;
                    } else {
                        C9878h.this.f18997ww = new DownloadInfo.Builder(C9878h.this.f18994o.getDownloadUrl()).build();
                        C9878h.this.f18997ww.setStatus(-3);
                        C9878h.this.f18980b.m7554mb(C9878h.this.f18997ww, C9878h.this.m7637e(), C9898ko.m7550mb(C9878h.this.f18996u));
                    }
                }
                C9878h.this.f18980b.m7568b(C9878h.this.f18997ww);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: mb */
    private void m7612mb(DownloadInfo downloadInfo) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = downloadInfo;
        this.f18995ox.sendMessage(obtain);
    }

    /* renamed from: io */
    private void m7631io() {
        AsyncTaskC9884b asyncTaskC9884b = this.f18991lz;
        if (asyncTaskC9884b != null && asyncTaskC9884b.getStatus() != AsyncTask.Status.FINISHED) {
            this.f18991lz.cancel(true);
        }
        this.f18991lz = new AsyncTaskC9884b();
        C10064ox.m6982mb(this.f18991lz, this.f18994o.getDownloadUrl(), this.f18994o.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public DownloadShortInfo m7637e() {
        if (this.f18988ko == null) {
            this.f18988ko = new DownloadShortInfo();
        }
        return this.f18988ko;
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: ww */
    public void mo7388ww() {
        C9923u.m7451mb().m7433u(this.f18993nk);
    }

    @Override // com.p319ss.android.downloadlib.addownload.InterfaceC9938u
    /* renamed from: mb */
    public InterfaceC9938u mo7398mb(IDownloadButtonClickListener iDownloadButtonClickListener) {
        if (iDownloadButtonClickListener == null) {
            this.f18982gm = null;
        } else {
            this.f18982gm = new SoftReference<>(iDownloadButtonClickListener);
        }
        return this;
    }

    /* renamed from: lz */
    public boolean m7624lz() {
        SoftReference<IDownloadButtonClickListener> softReference = this.f18982gm;
        if (softReference == null) {
            return false;
        }
        return C9939ww.m7385mb(this.f18994o, softReference.get());
    }

    /* renamed from: hj */
    public boolean m7632hj(boolean z) {
        SoftReference<IDownloadButtonClickListener> softReference = this.f18982gm;
        if (softReference != null && softReference.get() != null) {
            try {
                if (!z) {
                    this.f18982gm.get().handleComplianceDialog(true);
                } else {
                    this.f18982gm.get().handleMarketFailedComplianceDialog();
                }
                this.f18982gm = null;
                return true;
            } catch (Exception unused) {
                C9971b.m7285mb().m7278ox("mDownloadButtonClickListener has recycled");
                return false;
            }
        }
        C9971b.m7285mb().m7278ox("mDownloadButtonClickListener has recycled");
        return false;
    }
}
