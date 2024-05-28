package com.p319ss.android.downloadlib.p334ox;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.p319ss.android.download.api.model.DeepLink;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.addownload.C9939ww;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.OpenAppResult;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.event.C9970ox;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10048h;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10069ww;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.ox.mb */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10032mb {
    /* renamed from: mb */
    public static boolean m7124mb(@NonNull C9916h c9916h) {
        boolean z;
        DeepLink deepLink = c9916h.f19103ox.getDeepLink();
        String openUrl = deepLink == null ? null : deepLink.getOpenUrl();
        JSONObject m7085mb = C10048h.m7085mb(new JSONObject(), c9916h);
        C10050jb.m7040mb(m7085mb, "applink_source", "click_by_sdk");
        AdEventHandler.m7315mb().m7293ox("applink_click", m7085mb, c9916h);
        OpenAppResult m6972mb = C10069ww.m6972mb(openUrl, c9916h);
        if (m6972mb.getType() == 2) {
            if (!TextUtils.isEmpty(openUrl)) {
                m7117ox("by_url", m6972mb, m7085mb, c9916h);
            }
            m6972mb = C10069ww.m6974mb(C9940x.getContext(), c9916h.f19103ox.getPackageName(), c9916h);
        }
        boolean z2 = false;
        if (m7127mb(c9916h.f19102mb) && C9940x.m7364lz().optInt("link_ad_click_event") == 1) {
            if (c9916h.f19103ox instanceof AdDownloadModel) {
                ((AdDownloadModel) c9916h.f19103ox).setFunnelType(4);
            }
            AdEventHandler.m7315mb().m7314mb(c9916h.f19102mb, 0);
            z = true;
        } else {
            z = false;
        }
        int type = m6972mb.getType();
        if (type != 1) {
            switch (type) {
                case 3:
                    m7120mb("by_package", m7085mb, c9916h);
                    z2 = true;
                    break;
                case 4:
                    m7121mb("by_package", m6972mb, m7085mb, c9916h);
                    break;
                default:
                    C9971b.m7285mb().m7278ox("AppLinkClick default");
                    break;
            }
        } else {
            m7116ox("by_url", m7085mb, c9916h);
            z2 = true;
        }
        if (z2 && !z && ((C9970ox.m7289mb().m7287ox() && !C9970ox.m7289mb().m7286ox(c9916h.f19102mb, c9916h.f19103ox.getLogExtra())) || C9970ox.m7289mb().m7291b())) {
            AdEventHandler.m7315mb().m7314mb(c9916h.f19102mb, 2);
        }
        return z2;
    }

    /* renamed from: mb */
    public static void m7126mb(@NonNull C9837ox c9837ox) {
        String mo7472u = c9837ox.mo7472u();
        JSONObject m7085mb = C10048h.m7085mb(new JSONObject(), c9837ox);
        C10050jb.m7040mb(m7085mb, "applink_source", "notify_click_by_sdk");
        AdEventHandler.m7315mb().m7293ox("applink_click", m7085mb, c9837ox);
        OpenAppResult m6972mb = C10069ww.m6972mb(mo7472u, c9837ox);
        if (m6972mb.getType() == 2) {
            if (!TextUtils.isEmpty(mo7472u)) {
                m7117ox("notify_by_url", m6972mb, m7085mb, c9837ox);
            }
            m6972mb = C10069ww.m6974mb(C9940x.getContext(), c9837ox.mo7489h(), c9837ox);
        }
        int type = m6972mb.getType();
        if (type != 1) {
            switch (type) {
                case 3:
                    m7120mb("notify_by_package", m7085mb, c9837ox);
                    return;
                case 4:
                    m7121mb("notify_by_package", m6972mb, m7085mb, c9837ox);
                    return;
                default:
                    C9971b.m7285mb().m7278ox("AppLinkClickNotification default");
                    return;
            }
        }
        m7116ox("notify_by_url", m7085mb, c9837ox);
    }

    /* renamed from: ox */
    public static void m7118ox(C9837ox c9837ox) {
        if (c9837ox == null) {
            return;
        }
        String mo7472u = DownloadSetting.obtainGlobal().optInt("app_link_opt") == 1 ? c9837ox.mo7472u() : null;
        JSONObject m7085mb = C10048h.m7085mb(new JSONObject(), c9837ox);
        C10050jb.m7040mb(m7085mb, "applink_source", "dialog_click_by_sdk");
        AdEventHandler.m7315mb().m7293ox("applink_click", m7085mb, c9837ox);
        OpenAppResult m6972mb = C10069ww.m6972mb(mo7472u, c9837ox);
        if (m6972mb.getType() == 2) {
            if (!TextUtils.isEmpty(mo7472u)) {
                m7117ox("dialog_by_url", m6972mb, m7085mb, c9837ox);
            }
            m6972mb = C10069ww.m6974mb(C9940x.getContext(), c9837ox.mo7489h(), c9837ox);
        }
        int type = m6972mb.getType();
        if (type != 1) {
            switch (type) {
                case 3:
                    m7120mb("dialog_by_package", m7085mb, c9837ox);
                    return;
                case 4:
                    m7121mb("dialog_by_package", m6972mb, m7085mb, c9837ox);
                    return;
                default:
                    C9971b.m7285mb().m7278ox("AppLinkClickDialog default");
                    return;
            }
        }
        m7116ox("dialog_by_url", m7085mb, c9837ox);
    }

    /* renamed from: mb */
    public static boolean m7122mb(String str, @NonNull C9837ox c9837ox) {
        if (C9939ww.m7379ox(c9837ox.m7730yr())) {
            if (TextUtils.isEmpty(c9837ox.mo7472u()) && TextUtils.isEmpty(str)) {
                return false;
            }
            DownloadNotificationManager.getInstance().cancelNotification(c9837ox.mo7479m());
            JSONObject jSONObject = new JSONObject();
            C10048h.m7085mb(jSONObject, c9837ox);
            C10050jb.m7040mb(jSONObject, "applink_source", "auto_click");
            AdEventHandler.m7315mb().m7294ox("applink_click", c9837ox);
            OpenAppResult m6973mb = C10069ww.m6973mb(c9837ox, c9837ox.mo7472u(), c9837ox.mo7489h());
            switch (m6973mb.getType()) {
                case 1:
                    m7116ox("auto_by_url", jSONObject, c9837ox);
                    return true;
                case 2:
                    m7117ox("auto_by_url", m6973mb, jSONObject, c9837ox);
                    return false;
                case 3:
                    m7120mb("auto_by_package", jSONObject, c9837ox);
                    return true;
                case 4:
                    m7121mb("auto_by_package", m6973mb, jSONObject, c9837ox);
                    return false;
                default:
                    return false;
            }
        }
        return false;
    }

    /* renamed from: mb */
    public static void m7120mb(String str, @NonNull final JSONObject jSONObject, @NonNull final InterfaceC9836mb interfaceC9836mb) {
        char c;
        C10050jb.m7040mb(jSONObject, "applink_source", str);
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(interfaceC9836mb.mo7490gm()));
        AdEventHandler.m7315mb().m7293ox("deeplink_app_open", jSONObject, interfaceC9836mb);
        int hashCode = str.hashCode();
        if (hashCode == -1282070764) {
            if (str.equals("notify_by_package")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == -441514770) {
            if (str.equals("auto_by_package")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != -185950114) {
            if (hashCode == 368401333 && str.equals("dialog_by_package")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("by_package")) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                if ((C9940x.m7364lz().optInt("check_applink_mode") & 1) != 0) {
                    C10050jb.m7040mb(jSONObject, "check_applink_result_by_sdk", (Object) 1);
                    C10026h.m7135mb().m7133mb(new InterfaceC10029hj() { // from class: com.ss.android.downloadlib.ox.mb.1
                        @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj
                        /* renamed from: mb */
                        public void mo7115mb(boolean z) {
                            AdEventHandler.m7315mb().m7293ox(z ? "deeplink_success" : "deeplink_failed", jSONObject, interfaceC9836mb);
                            if (z) {
                                C9940x.m7373gm().mo7340mb(C9940x.getContext(), interfaceC9836mb.mo7491g(), interfaceC9836mb.mo7473r(), interfaceC9836mb.mo7493df(), interfaceC9836mb.mo7489h(), 0);
                            }
                        }
                    });
                    return;
                }
                C9940x.m7348ox().mo7342mb(C9940x.getContext(), interfaceC9836mb.mo7491g(), interfaceC9836mb.mo7473r(), interfaceC9836mb.mo7493df(), interfaceC9836mb.mo7489h(), str);
                return;
            default:
                return;
        }
    }

    /* renamed from: ox */
    public static void m7116ox(String str, @NonNull final JSONObject jSONObject, @NonNull final InterfaceC9836mb interfaceC9836mb) {
        char c;
        C10050jb.m7040mb(jSONObject, "applink_source", str);
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(interfaceC9836mb.mo7490gm()));
        AdEventHandler.m7315mb().m7293ox("deeplink_url_open", jSONObject, interfaceC9836mb);
        int hashCode = str.hashCode();
        if (hashCode == -1721882089) {
            if (str.equals("auto_by_url")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == -1374618233) {
            if (str.equals("by_url")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != -129544387) {
            if (hashCode == 829750366 && str.equals("dialog_by_url")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("notify_by_url")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                if ((C9940x.m7364lz().optInt("check_applink_mode") & 1) != 0) {
                    C10050jb.m7040mb(jSONObject, "check_applink_result_by_sdk", (Object) 1);
                    C10026h.m7135mb().m7133mb(new InterfaceC10029hj() { // from class: com.ss.android.downloadlib.ox.mb.2
                        @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj
                        /* renamed from: mb */
                        public void mo7115mb(boolean z) {
                            AdEventHandler.m7315mb().m7293ox(z ? "deeplink_success" : "deeplink_failed", jSONObject, interfaceC9836mb);
                            if (z) {
                                C9940x.m7373gm().mo7340mb(C9940x.getContext(), interfaceC9836mb.mo7491g(), interfaceC9836mb.mo7473r(), interfaceC9836mb.mo7493df(), interfaceC9836mb.mo7489h(), 0);
                            }
                        }
                    });
                    return;
                }
                C9940x.m7348ox().mo7342mb(C9940x.getContext(), interfaceC9836mb.mo7491g(), interfaceC9836mb.mo7473r(), interfaceC9836mb.mo7493df(), interfaceC9836mb.mo7489h(), str);
                return;
            default:
                return;
        }
    }

    /* renamed from: mb */
    public static void m7121mb(String str, @NonNull OpenAppResult openAppResult, @NonNull JSONObject jSONObject, @NonNull InterfaceC9836mb interfaceC9836mb) {
        C10050jb.m7040mb(jSONObject, "applink_source", str);
        C10050jb.m7040mb(jSONObject, "error_code", Integer.valueOf(openAppResult.m7500mb()));
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(interfaceC9836mb.mo7490gm()));
        AdEventHandler.m7315mb().m7293ox("deeplink_app_open_fail", jSONObject, interfaceC9836mb);
    }

    /* renamed from: ox */
    public static void m7117ox(String str, @NonNull OpenAppResult openAppResult, @NonNull JSONObject jSONObject, @NonNull InterfaceC9836mb interfaceC9836mb) {
        C10050jb.m7040mb(jSONObject, "applink_source", str);
        C10050jb.m7040mb(jSONObject, "error_code", Integer.valueOf(openAppResult.m7500mb()));
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(interfaceC9836mb.mo7490gm()));
        AdEventHandler.m7315mb().m7293ox("deeplink_url_open_fail", jSONObject, interfaceC9836mb);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: mb */
    public static boolean m7123mb(@NonNull C9916h c9916h, int i) {
        JSONObject jSONObject = new JSONObject();
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(c9916h.mo7490gm()));
        AdEventHandler.m7315mb().m7293ox("market_click_open", jSONObject, c9916h);
        OpenAppResult m6976mb = C10069ww.m6976mb(C9940x.getContext(), c9916h, c9916h.f19103ox.getPackageName());
        String m7036mb = C10050jb.m7036mb(m6976mb.m7499ox(), "open_market");
        switch (m6976mb.getType()) {
            case 5:
                m7119mb(m7036mb, jSONObject, c9916h, true);
                break;
            case 6:
                C10050jb.m7040mb(jSONObject, "error_code", Integer.valueOf(m6976mb.m7500mb()));
                C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(c9916h.mo7490gm()));
                AdEventHandler.m7315mb().m7293ox("market_open_failed", jSONObject, c9916h);
                return false;
            case 7:
                break;
            default:
                return false;
        }
        AdEventHandler.m7315mb().m7314mb(c9916h.f19102mb, i);
        return true;
    }

    /* renamed from: mb */
    public static void m7119mb(final String str, @Nullable final JSONObject jSONObject, final C9916h c9916h, boolean z) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e) {
                C9971b.m7285mb().mo7282mb(e, "onMarketSuccess");
                return;
            }
        }
        C10050jb.m7040mb(jSONObject, "applink_source", str);
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(c9916h.mo7490gm()));
        if (z) {
            AdEventHandler.m7315mb().m7293ox("market_open_success", jSONObject, c9916h);
        }
        if ((C9940x.m7364lz().optInt("check_applink_mode") & 4) != 0) {
            C10026h.m7135mb().m7131ox(new InterfaceC10029hj() { // from class: com.ss.android.downloadlib.ox.mb.3
                @Override // com.p319ss.android.downloadlib.p334ox.InterfaceC10029hj
                /* renamed from: mb */
                public void mo7115mb(boolean z2) {
                    if (!z2 && !"open_market".equals(str)) {
                        Context context = C9940x.getContext();
                        C10032mb.m7125mb(C10069ww.m6977mb(context, Uri.parse("market://details?id=" + c9916h.mo7489h())), c9916h, false);
                    }
                    AdEventHandler.m7315mb().m7298mb(z2 ? "market_delay_success" : "market_delay_failed", jSONObject, c9916h);
                    if (z2) {
                        C9940x.m7373gm().mo7340mb(C9940x.getContext(), c9916h.f19103ox, c9916h.f19101hj, c9916h.f19100b, c9916h.f19103ox.getPackageName(), 2);
                    }
                }
            });
        } else {
            C9940x.m7348ox().mo7342mb(C9940x.getContext(), c9916h.f19103ox, c9916h.f19101hj, c9916h.f19100b, c9916h.f19103ox.getPackageName(), str);
        }
        C9837ox c9837ox = new C9837ox(c9916h.f19103ox, c9916h.f19100b, c9916h.f19101hj);
        c9837ox.m7807h(2);
        c9837ox.m7746u(System.currentTimeMillis());
        c9837ox.m7739ww(4);
        c9837ox.m7779lz(2);
        C9923u.m7451mb().m7445mb(c9837ox);
    }

    /* renamed from: mb */
    public static void m7125mb(OpenAppResult openAppResult, C9916h c9916h, boolean z) {
        String m7036mb = C10050jb.m7036mb(openAppResult.m7499ox(), "open_market");
        JSONObject jSONObject = new JSONObject();
        C10050jb.m7040mb(jSONObject, "ttdownloader_type", "backup");
        switch (openAppResult.getType()) {
            case 5:
                m7119mb(m7036mb, jSONObject, c9916h, z);
                return;
            case 6:
                C10050jb.m7040mb(jSONObject, "error_code", Integer.valueOf(openAppResult.m7500mb()));
                C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(c9916h.mo7490gm()));
                AdEventHandler.m7315mb().m7293ox("market_open_failed", jSONObject, c9916h);
                return;
            default:
                return;
        }
    }

    /* renamed from: mb */
    public static boolean m7127mb(long j) {
        return C9923u.m7451mb().m7452hj(j) == null;
    }
}
