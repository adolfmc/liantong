package com.p319ss.android.downloadlib.addownload.p327mb;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.InterfaceC9800je;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C10071ww;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9921mb;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.mb.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9911mb {

    /* renamed from: mb */
    private static final String f19070mb = "mb";

    /* renamed from: ox */
    private static C9911mb f19071ox;

    /* renamed from: h */
    private String f19073h;

    /* renamed from: hj */
    private boolean f19074hj = false;

    /* renamed from: u */
    private C9914ox f19075u = new C9914ox();
    @NonNull

    /* renamed from: b */
    private CopyOnWriteArrayList<C9921mb> f19072b = this.f19075u.m7503mb("sp_ad_install_back_dialog", "key_uninstalled_list");

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.mb.mb$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9913mb {
        /* renamed from: mb */
        void mo7504mb();
    }

    private C9911mb() {
    }

    /* renamed from: mb */
    public static C9911mb m7514mb() {
        if (f19071ox == null) {
            f19071ox = new C9911mb();
        }
        return f19071ox;
    }

    /* renamed from: mb */
    private boolean m7512mb(Activity activity, DownloadInfo downloadInfo, boolean z, InterfaceC9913mb interfaceC9913mb) {
        if (downloadInfo == null) {
            try {
                if (this.f19072b.isEmpty()) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (activity != null && !activity.isFinishing()) {
            boolean z2 = true;
            if (downloadInfo != null && this.f19072b.isEmpty()) {
                m7508mb(activity, new C9921mb(downloadInfo.getId(), 0L, 0L, downloadInfo.getPackageName(), downloadInfo.getTitle(), null, downloadInfo.getTargetFilePath()), z, interfaceC9913mb);
                return true;
            }
            long lastModified = downloadInfo != null ? new File(downloadInfo.getTargetFilePath()).lastModified() : 0L;
            ListIterator<C9921mb> listIterator = this.f19072b.listIterator(this.f19072b.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    z2 = false;
                    break;
                }
                C9921mb previous = listIterator.previous();
                if (previous != null && !C10050jb.m7060hj(C9940x.getContext(), previous.f19116hj) && C10050jb.m7046mb(previous.f19117ko)) {
                    if (new File(previous.f19117ko).lastModified() >= lastModified) {
                        m7508mb(activity, previous, z, interfaceC9913mb);
                    } else {
                        m7508mb(activity, new C9921mb(downloadInfo.getId(), 0L, 0L, downloadInfo.getPackageName(), downloadInfo.getTitle(), null, downloadInfo.getTargetFilePath()), z, interfaceC9913mb);
                    }
                }
            }
            C10070x.m6967mb(f19070mb, "tryShowInstallDialog isShow:" + z2, null);
            return z2;
        }
        return false;
    }

    @MainThread
    /* renamed from: mb */
    public boolean m7511mb(Activity activity, boolean z, InterfaceC9913mb interfaceC9913mb) {
        if (C9940x.m7364lz().optInt("disable_install_app_dialog") == 1 || this.f19074hj) {
            return false;
        }
        return m7512mb(activity, m7510mb(activity), z, interfaceC9913mb);
    }

    /* renamed from: mb */
    public void m7508mb(Context context, C9921mb c9921mb, boolean z, InterfaceC9913mb interfaceC9913mb) {
        this.f19072b.clear();
        m7509mb(context, c9921mb, interfaceC9913mb, z);
        this.f19074hj = true;
        C10071ww.m6960mb(context).m6965b();
        this.f19075u.m7501ox("sp_ad_install_back_dialog", "key_uninstalled_list");
        C10070x.m6967mb(f19070mb, "tryShowInstallDialog isShow:true", null);
    }

    /* renamed from: mb */
    public DownloadInfo m7510mb(Context context) {
        long m6950ox;
        List<DownloadInfo> successedDownloadInfosWithMimeType;
        DownloadInfo downloadInfo = null;
        try {
            m6950ox = C10071ww.m6960mb(context).m6950ox();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (C9940x.m7364lz().optInt("enable_miniapp_dialog", 0) != 0 && (successedDownloadInfosWithMimeType = Downloader.getInstance(context).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive")) != null && !successedDownloadInfosWithMimeType.isEmpty()) {
            long j = 0;
            for (DownloadInfo downloadInfo2 : successedDownloadInfosWithMimeType) {
                if (downloadInfo2 != null && !C10050jb.m7060hj(context, downloadInfo2.getPackageName()) && C10050jb.m7046mb(downloadInfo2.getTargetFilePath())) {
                    long lastModified = new File(downloadInfo2.getTargetFilePath()).lastModified();
                    if (lastModified >= m6950ox && downloadInfo2.getExtra() != null) {
                        try {
                            if (new JSONObject(downloadInfo2.getExtra()).has("isMiniApp") && (j == 0 || lastModified > j)) {
                                downloadInfo = downloadInfo2;
                                j = lastModified;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            return downloadInfo;
        }
        return null;
    }

    /* renamed from: mb */
    public void m7513mb(long j, long j2, long j3, String str, String str2, String str3, String str4) {
        for (int i = 0; i < this.f19072b.size(); i++) {
            C9921mb c9921mb = this.f19072b.get(i);
            if (c9921mb != null && c9921mb.f19119ox == j2) {
                this.f19072b.set(i, new C9921mb(j, j2, j3, str, str2, str3, str4));
                this.f19075u.m7502mb("sp_ad_install_back_dialog", "key_uninstalled_list", this.f19072b);
                return;
            }
        }
        this.f19072b.add(new C9921mb(j, j2, j3, str, str2, str3, str4));
        this.f19075u.m7502mb("sp_ad_install_back_dialog", "key_uninstalled_list", this.f19072b);
    }

    /* renamed from: mb */
    private void m7509mb(final Context context, final C9921mb c9921mb, final InterfaceC9913mb interfaceC9913mb, boolean z) {
        final C9837ox m7452hj = C9923u.m7451mb().m7452hj(c9921mb.f19119ox);
        if (m7452hj == null) {
            C9971b.m7285mb().m7284mb("showBackInstallDialog nativeModel null");
            return;
        }
        InterfaceC9800je m7377b = C9940x.m7377b();
        DownloadAlertDialogInfo.C9826mb m7888mb = new DownloadAlertDialogInfo.C9826mb(context).m7888mb(z ? "应用安装确认" : "退出确认");
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(c9921mb.f19115h) ? "刚刚下载的应用" : c9921mb.f19115h;
        m7377b.mo7903ox(m7888mb.m7885ox(String.format("%1$s下载完成，是否立即安装？", objArr)).m7898b("立即安装").m7895hj(z ? "暂不安装" : String.format("退出%1$s", context.getResources().getString(context.getApplicationContext().getApplicationInfo().labelRes))).m7887mb(false).m7891mb(C10050jb.m7052mb(context, c9921mb.f19117ko)).m7889mb(new DownloadAlertDialogInfo.InterfaceC9827ox() { // from class: com.ss.android.downloadlib.addownload.mb.mb.1
            @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
            /* renamed from: mb */
            public void mo7140mb(DialogInterface dialogInterface) {
                AdEventHandler.m7315mb().m7294ox("backdialog_install", m7452hj);
                C10112hj.m6812mb(context, (int) c9921mb.f19118mb);
                dialogInterface.dismiss();
            }

            @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
            /* renamed from: ox */
            public void mo7139ox(DialogInterface dialogInterface) {
                AdEventHandler.m7315mb().m7294ox("backdialog_exit", m7452hj);
                InterfaceC9913mb interfaceC9913mb2 = interfaceC9913mb;
                if (interfaceC9913mb2 != null) {
                    interfaceC9913mb2.mo7504mb();
                }
                C9911mb.this.m7505ox("");
                dialogInterface.dismiss();
            }

            @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
            /* renamed from: b */
            public void mo7141b(DialogInterface dialogInterface) {
                C9911mb.this.m7505ox("");
            }
        }).m7892mb(1).m7893mb());
        AdEventHandler.m7315mb().m7294ox("backdialog_show", m7452hj);
        this.f19073h = c9921mb.f19116hj;
    }

    /* renamed from: mb */
    public boolean m7506mb(String str) {
        return TextUtils.equals(this.f19073h, str);
    }

    /* renamed from: ox */
    public void m7505ox(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f19073h = "";
        } else if (TextUtils.equals(this.f19073h, str)) {
            this.f19073h = "";
        }
    }

    /* renamed from: mb */
    public void m7507mb(C9837ox c9837ox) {
        if (C9940x.m7364lz().optInt("enable_open_app_dialog", 0) == 1 && !c9837ox.m7815cd() && c9837ox.mo7492e()) {
            c9837ox.m7794jb(true);
            TTDelegateActivity.m7722mb(c9837ox);
        }
    }
}
