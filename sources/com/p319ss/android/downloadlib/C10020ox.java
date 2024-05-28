package com.p319ss.android.downloadlib;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.download.C9813b;
import com.p319ss.android.download.api.download.C9818ox;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.DownloadStatusChangeListener;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;
import com.p319ss.android.download.api.p320b.C9778mb;
import com.p319ss.android.downloadad.api.InterfaceC9838ox;
import com.p319ss.android.downloadad.api.download.AdDownloadController;
import com.p319ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9939ww;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.compliance.C9873ox;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.OpenAppResult;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9974ox;
import com.p319ss.android.downloadlib.p334ox.C10032mb;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10069ww;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C10020ox implements InterfaceC9838ox {

    /* renamed from: mb */
    private static String f19318mb = "ox";

    /* renamed from: ox */
    private static volatile C10020ox f19319ox;

    /* renamed from: b */
    private C10071ww f19320b = C10071ww.m6960mb(C9940x.getContext());

    private C10020ox() {
    }

    /* renamed from: mb */
    public static C10020ox m7160mb() {
        if (f19319ox == null) {
            synchronized (C10020ox.class) {
                if (f19319ox == null) {
                    f19319ox = new C10020ox();
                }
            }
        }
        return f19319ox;
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public Dialog mo7153mb(Context context, String str, boolean z, @NonNull DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        return m7151mb(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, false);
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public Dialog mo7152mb(Context context, String str, boolean z, @NonNull DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, IDownloadButtonClickListener iDownloadButtonClickListener) {
        return m7150mb(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, false, iDownloadButtonClickListener);
    }

    /* renamed from: mb */
    public Dialog m7151mb(Context context, String str, boolean z, @NonNull DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, boolean z2) {
        return m7150mb(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, z2, null);
    }

    /* renamed from: mb */
    public Dialog m7150mb(final Context context, final String str, final boolean z, @NonNull final DownloadModel downloadModel, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final DownloadStatusChangeListener downloadStatusChangeListener, final int i, final boolean z2, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        return (Dialog) C9974ox.m7274mb(new C9974ox.InterfaceC9976mb<Dialog>() { // from class: com.ss.android.downloadlib.ox.1
            @Override // com.p319ss.android.downloadlib.exception.C9974ox.InterfaceC9976mb
            /* renamed from: mb */
            public Dialog mo7137ox() {
                return C10020ox.this.m7143ox(context, str, z, downloadModel, downloadEventConfig, downloadController, downloadStatusChangeListener, i, z2, iDownloadButtonClickListener);
            }
        });
    }

    /* renamed from: ox */
    public Dialog m7143ox(Context context, String str, boolean z, final DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, DownloadStatusChangeListener downloadStatusChangeListener, int i, boolean z2, IDownloadButtonClickListener iDownloadButtonClickListener) {
        if (mo7159mb(downloadModel.getId())) {
            if (z2) {
                m7157mb(downloadModel.getId(), downloadEventConfig, downloadController);
            } else {
                m7145ox(downloadModel.getId());
            }
            return null;
        } else if (context == null || TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            return null;
        } else {
            this.f19320b.m6959mb(context, i, downloadStatusChangeListener, downloadModel);
            final DownloadEventConfig downloadEventConfig2 = (DownloadEventConfig) C10050jb.m7037mb(downloadEventConfig, m7161b());
            final DownloadController downloadController2 = (DownloadController) C10050jb.m7037mb(downloadController, m7146ox());
            downloadEventConfig2.setDownloadScene(1);
            boolean z3 = (C9940x.m7364lz().optInt("disable_lp_dialog", 0) == 1) | z;
            if (downloadController2.enableShowComplianceDialog() && C9873ox.m7653mb().m7649mb(downloadModel)) {
                z3 = true;
            }
            if (z3) {
                this.f19320b.m6953mb(downloadModel.getDownloadUrl(), downloadModel.getId(), 2, downloadEventConfig2, downloadController2, iDownloadButtonClickListener);
                return null;
            }
            C10070x.m6967mb(f19318mb, "tryStartDownload show dialog appName:" + downloadModel.getDownloadUrl(), null);
            Dialog mo7903ox = C9940x.m7377b().mo7903ox(new DownloadAlertDialogInfo.C9826mb(context).m7888mb(downloadModel.getName()).m7885ox("确认要下载此应用吗？").m7898b("确认").m7895hj("取消").m7889mb(new DownloadAlertDialogInfo.InterfaceC9827ox() { // from class: com.ss.android.downloadlib.ox.2
                @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
                /* renamed from: mb */
                public void mo7140mb(DialogInterface dialogInterface) {
                    C10020ox.this.f19320b.m6954mb(downloadModel.getDownloadUrl(), downloadModel.getId(), 2, downloadEventConfig2, downloadController2);
                    AdEventHandler.m7315mb().m7304mb("landing_download_dialog_confirm", downloadModel, downloadEventConfig2, downloadController2);
                    dialogInterface.dismiss();
                }

                @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
                /* renamed from: ox */
                public void mo7139ox(DialogInterface dialogInterface) {
                    AdEventHandler.m7315mb().m7304mb("landing_download_dialog_cancel", downloadModel, downloadEventConfig2, downloadController2);
                    dialogInterface.dismiss();
                }

                @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
                /* renamed from: b */
                public void mo7141b(DialogInterface dialogInterface) {
                    AdEventHandler.m7315mb().m7304mb("landing_download_dialog_cancel", downloadModel, downloadEventConfig2, downloadController2);
                }
            }).m7892mb(0).m7893mb());
            AdEventHandler.m7315mb().m7304mb("landing_download_dialog_show", downloadModel, downloadEventConfig2, downloadController2);
            return mo7903ox;
        }
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public boolean mo7156mb(Context context, long j, String str, DownloadStatusChangeListener downloadStatusChangeListener, int i) {
        C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7452hj != null) {
            this.f19320b.m6959mb(context, i, downloadStatusChangeListener, m7452hj.m7803he());
            return true;
        }
        DownloadModel m7449mb = C9923u.m7451mb().m7449mb(j);
        if (m7449mb != null) {
            this.f19320b.m6959mb(context, i, downloadStatusChangeListener, m7449mb);
            return true;
        }
        return false;
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public boolean mo7158mb(long j, int i) {
        DownloadModel m7449mb = C9923u.m7451mb().m7449mb(j);
        if (m7449mb != null) {
            this.f19320b.m6955mb(m7449mb.getDownloadUrl(), i);
            return true;
        }
        return false;
    }

    /* renamed from: mb */
    public void m7157mb(long j, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        DownloadModel m7449mb = C9923u.m7451mb().m7449mb(j);
        C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7449mb == null && m7452hj != null) {
            m7449mb = m7452hj.m7803he();
        }
        if (m7449mb == null) {
            return;
        }
        if (downloadEventConfig == null || downloadController == null || (downloadEventConfig instanceof C9813b) || (downloadController instanceof C9818ox)) {
            m7145ox(j);
            return;
        }
        downloadEventConfig.setDownloadScene(1);
        this.f19320b.m6954mb(m7449mb.getDownloadUrl(), j, 2, downloadEventConfig, downloadController);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.ss.android.download.api.download.DownloadController] */
    /* renamed from: ox */
    public void m7145ox(long j) {
        DownloadEventConfig downloadEventConfig;
        AdDownloadController adDownloadController;
        DownloadModel m7449mb = C9923u.m7451mb().m7449mb(j);
        C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7449mb == null && m7452hj != null) {
            m7449mb = m7452hj.m7803he();
        }
        if (m7449mb == null) {
            return;
        }
        DownloadEventConfig m7437ox = C9923u.m7451mb().m7437ox(j);
        DownloadController m7454b = C9923u.m7451mb().m7454b(j);
        if (m7437ox instanceof C9813b) {
            m7437ox = null;
        }
        if (m7454b instanceof C9818ox) {
            m7454b = null;
        }
        if (m7452hj == null) {
            if (m7437ox == null) {
                m7437ox = m7161b();
            }
            if (m7454b == 0) {
                downloadEventConfig = m7437ox;
                adDownloadController = m7146ox();
            } else {
                downloadEventConfig = m7437ox;
                adDownloadController = m7454b;
            }
        } else {
            if (m7437ox == null) {
                m7437ox = new AdDownloadEventConfig.Builder().setClickButtonTag(m7452hj.mo7470x()).setRefer(m7452hj.mo7480lz()).setIsEnableV3Event(m7452hj.mo7477nk()).setIsEnableClickEvent(false).setClickStartLabel("click_start_detail").setClickPauseLabel("click_pause_detail").setClickContinueLabel("click_continue_detail").setClickInstallLabel("click_install_detail").setStorageDenyLabel("storage_deny_detail").build();
            }
            if (m7454b == null) {
                downloadEventConfig = m7437ox;
                adDownloadController = m7452hj.m7790jm();
            } else {
                downloadEventConfig = m7437ox;
                adDownloadController = m7454b;
            }
        }
        downloadEventConfig.setDownloadScene(1);
        this.f19320b.m6954mb(m7449mb.getDownloadUrl(), j, 2, downloadEventConfig, adDownloadController);
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public boolean mo7159mb(long j) {
        return (C9923u.m7451mb().m7449mb(j) == null && C9923u.m7451mb().m7452hj(j) == null) ? false : true;
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public boolean mo7155mb(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        return mo7154mb(context, uri, downloadModel, downloadEventConfig, downloadController, null);
    }

    @Override // com.p319ss.android.downloadad.api.InterfaceC9838ox
    /* renamed from: mb */
    public boolean mo7154mb(final Context context, final Uri uri, final DownloadModel downloadModel, final DownloadEventConfig downloadEventConfig, final DownloadController downloadController, final IDownloadButtonClickListener iDownloadButtonClickListener) {
        return ((Boolean) C9974ox.m7274mb(new C9974ox.InterfaceC9976mb<Boolean>() { // from class: com.ss.android.downloadlib.ox.3
            @Override // com.p319ss.android.downloadlib.exception.C9974ox.InterfaceC9976mb
            /* renamed from: mb */
            public Boolean mo7137ox() {
                return Boolean.valueOf(C10020ox.this.m7144ox(context, uri, downloadModel, downloadEventConfig, downloadController, iDownloadButtonClickListener));
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public boolean m7144ox(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener) {
        DownloadController m7146ox;
        if (C9778mb.m7972mb(uri) && C9940x.m7364lz().optInt("disable_market") != 1) {
            Context context2 = context == null ? C9940x.getContext() : context;
            String m7971ox = C9778mb.m7971ox(uri);
            if (downloadModel == null) {
                return C10069ww.m6975mb(context2, m7971ox).getType() == 5;
            }
            if (!TextUtils.isEmpty(m7971ox) && (downloadModel instanceof AdDownloadModel)) {
                ((AdDownloadModel) downloadModel).setPackageName(m7971ox);
            }
            if (downloadController != null) {
                downloadController.setDownloadMode(2);
                m7146ox = downloadController;
            } else if ((downloadModel instanceof AdDownloadModel) && TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
                ((AdDownloadModel) downloadModel).setDownloadUrl(uri.toString());
                m7146ox = m7147mb(true);
            } else if (downloadModel.getDownloadUrl().startsWith("market")) {
                m7146ox = m7147mb(true);
            } else {
                m7146ox = m7146ox();
            }
            C9916h c9916h = new C9916h(downloadModel.getId(), downloadModel, (DownloadEventConfig) C10050jb.m7037mb(downloadEventConfig, m7161b()), m7146ox);
            C9923u.m7451mb().m7446mb(c9916h.f19103ox);
            C9923u.m7451mb().m7447mb(c9916h.f19102mb, c9916h.f19100b);
            C9923u.m7451mb().m7448mb(c9916h.f19102mb, c9916h.f19101hj);
            if (C10050jb.m7050mb(downloadModel) && DownloadSetting.obtainGlobal().optInt("app_link_opt") == 1 && C10032mb.m7124mb(c9916h)) {
                return true;
            }
            JSONObject jSONObject = new JSONObject();
            C10050jb.m7040mb(jSONObject, "market_url", uri.toString());
            C10050jb.m7040mb(jSONObject, "download_scene", (Object) 1);
            AdEventHandler.m7315mb().m7293ox("market_click_open", jSONObject, c9916h);
            OpenAppResult m6976mb = C10069ww.m6976mb(context2, c9916h, m7971ox);
            String m7036mb = C10050jb.m7036mb(m6976mb.m7499ox(), "open_market");
            if (m6976mb.getType() == 5) {
                C10032mb.m7119mb(m7036mb, jSONObject, c9916h, true);
                return true;
            } else if (m6976mb.getType() == 6) {
                C10050jb.m7040mb(jSONObject, "error_code", Integer.valueOf(m6976mb.m7500mb()));
                AdEventHandler.m7315mb().m7293ox("market_open_failed", jSONObject, c9916h);
                if (C9939ww.m7385mb(downloadModel, iDownloadButtonClickListener)) {
                    iDownloadButtonClickListener.handleMarketFailedComplianceDialog();
                }
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /* renamed from: ox */
    public static DownloadController m7146ox() {
        return m7147mb(false);
    }

    /* renamed from: mb */
    public static DownloadController m7147mb(boolean z) {
        AdDownloadController.Builder shouldUseNewWebView = new AdDownloadController.Builder().setLinkMode(0).setIsEnableBackDialog(true).setIsEnableMultipleDownload(false).setShouldUseNewWebView(false);
        if (z) {
            shouldUseNewWebView.setDownloadMode(2);
        } else {
            shouldUseNewWebView.setDownloadMode(0);
        }
        return shouldUseNewWebView.build();
    }

    /* renamed from: b */
    public static DownloadEventConfig m7161b() {
        return new AdDownloadEventConfig.Builder().setClickButtonTag("landing_h5_download_ad_button").setClickItemTag("landing_h5_download_ad_button").setClickStartLabel("click_start_detail").setClickPauseLabel("click_pause_detail").setClickContinueLabel("click_continue_detail").setClickInstallLabel("click_install_detail").setClickOpenLabel("click_open_detail").setStorageDenyLabel("storage_deny_detail").setDownloadScene(1).setIsEnableClickEvent(false).setIsEnableNoChargeClickEvent(true).setIsEnableV3Event(false).build();
    }
}
