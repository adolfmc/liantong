package com.p319ss.android.downloadlib.event;

import android.os.Build;
import android.support.annotation.NonNull;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.model.C9832ox;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.C9998mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10048h;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.appdownloader.p336h.C10106hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.event.AdEventHandler */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdEventHandler {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.event.AdEventHandler$EventType */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface EventType {
        public static final int CLICK_CONTINUE = 4;
        public static final int CLICK_INSTALL = 5;
        public static final int CLICK_PAUSE = 3;
        public static final int CLICK_START = 2;
        public static final int STORAGE_DENY = 1;
    }

    private AdEventHandler() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r1 = (99 * 99) - ((18 * 18) * 34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0000, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String AdEventHandler1672829046082dc(java.lang.String r4) {
        /*
        L0:
            r0 = 74
            r1 = 55
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L0;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L4f;
                case 95: goto L10;
                case 96: goto L39;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L4f;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L4f
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
            goto L39
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
            r0 = 73
            r1 = 96
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.event.AdEventHandler.AdEventHandler1672829046082dc(java.lang.String):java.lang.String");
    }

    /* renamed from: mb */
    public static AdEventHandler m7315mb() {
        return C9968mb.f19213mb;
    }

    /* renamed from: mb */
    private JSONObject m7309mb(InterfaceC9836mb interfaceC9836mb) {
        JSONObject jSONObject = new JSONObject();
        try {
            C10050jb.m7039mb(interfaceC9836mb.mo7483ko(), jSONObject);
            C10050jb.m7039mb(interfaceC9836mb.mo7487io(), jSONObject);
            jSONObject.putOpt("download_url", interfaceC9836mb.mo7478mb());
            jSONObject.putOpt("package_name", interfaceC9836mb.mo7489h());
            jSONObject.putOpt("android_int", Integer.valueOf(Build.VERSION.SDK_INT));
            jSONObject.putOpt("rom_name", C10152hj.m6572ko());
            jSONObject.putOpt("rom_version", C10152hj.m6562ww());
            jSONObject.putOpt("ttdownloader", 1);
            jSONObject.putOpt("funnel_type", Integer.valueOf(interfaceC9836mb.mo7471ww()));
            if (interfaceC9836mb.mo7471ww() == 2) {
                C10048h.m7083ox(jSONObject, interfaceC9836mb);
            }
        } catch (Exception e) {
            C9940x.m7363m().mo7282mb(e, "getBaseJson");
        }
        return jSONObject;
    }

    /* renamed from: mb */
    private void m7310mb(C9832ox c9832ox) {
        if (C9940x.m7362mb() == null) {
            return;
        }
        if (c9832ox.m7858nk()) {
            C9940x.m7362mb().mo7946mb(c9832ox);
        } else {
            C9940x.m7362mb().mo7945ox(c9832ox);
        }
    }

    /* renamed from: mb */
    private void m7301mb(String str, String str2, JSONObject jSONObject, long j, int i, InterfaceC9836mb interfaceC9836mb) {
        C9971b m7285mb;
        String str3;
        if (interfaceC9836mb == null) {
            m7285mb = C9971b.m7285mb();
            str3 = "onEvent data null";
        } else if (!(interfaceC9836mb instanceof C9916h) || !((C9916h) interfaceC9836mb).m7475on()) {
            try {
                C9832ox.C9833mb m7851b = new C9832ox.C9833mb().m7836mb(C10050jb.m7036mb(str, interfaceC9836mb.mo7470x(), "embeded_ad")).m7828ox(str2).m7826ox(interfaceC9836mb.mo7494b()).m7839mb(interfaceC9836mb.mo7474ox()).m7851b(interfaceC9836mb.mo7488hj());
                if (j <= 0) {
                    j = interfaceC9836mb.mo7485je();
                }
                C9832ox.C9833mb m7837mb = m7851b.m7830ox(j).m7848hj(interfaceC9836mb.mo7480lz()).m7835mb(interfaceC9836mb.mo7476o()).m7834mb(C10050jb.m7035mb(m7309mb(interfaceC9836mb), jSONObject)).m7827ox(interfaceC9836mb.mo7486jb()).m7837mb(interfaceC9836mb.mo7481lc());
                if (i <= 0) {
                    i = 2;
                }
                m7310mb(m7837mb.m7840mb(i).m7833mb(interfaceC9836mb.mo7477nk()).m7841mb());
                return;
            } catch (Exception e) {
                C9971b.m7285mb().mo7282mb(e, "onEvent");
                return;
            }
        } else {
            m7285mb = C9971b.m7285mb();
            str3 = "onEvent ModelBox notValid";
        }
        m7285mb.m7284mb(str3);
    }

    /* renamed from: mb */
    public void m7314mb(long j, int i) {
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        if (m7453h.m7475on()) {
            C9971b.m7285mb().m7284mb("sendClickEvent ModelBox notValid");
        } else if (m7453h.f19100b.isEnableClickEvent()) {
            int i2 = 1;
            String clickItemTag = i == 1 ? m7453h.f19100b.getClickItemTag() : m7453h.f19100b.getClickButtonTag();
            String m7036mb = C10050jb.m7036mb(m7453h.f19100b.getClickLabel(), "click");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("download_click_type", Integer.valueOf(i));
                jSONObject.putOpt("permission_notification", Integer.valueOf(C10106hj.m6838mb() ? 1 : 2));
                if (!DownloadUtils.isNetworkConnected(C9940x.getContext())) {
                    i2 = 2;
                }
                jSONObject.putOpt("network_available", Integer.valueOf(i2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            m7300mb(clickItemTag, m7036mb, jSONObject, m7453h);
            if (!"click".equals(m7036mb) || m7453h.f19103ox == null) {
                return;
            }
            C9970ox.m7289mb().m7288mb(j, m7453h.f19103ox.getLogExtra());
        }
    }

    /* renamed from: mb */
    public void m7313mb(long j, @EventType int i, DownloadInfo downloadInfo) {
        String[] strArr;
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        if (m7453h.m7475on()) {
            C9971b.m7285mb().m7284mb("sendEvent ModelBox notValid");
            return;
        }
        String str = null;
        JSONObject jSONObject = new JSONObject();
        C10050jb.m7040mb(jSONObject, "download_scene", Integer.valueOf(m7453h.mo7490gm()));
        switch (i) {
            case 1:
                strArr = new String[]{m7453h.f19100b.getStorageDenyLabel(), "storage_deny"};
                str = C10050jb.m7036mb(strArr);
                break;
            case 2:
                str = C10050jb.m7036mb(m7453h.f19100b.getClickStartLabel(), "click_start");
                C10048h.m7087mb(downloadInfo, jSONObject);
                break;
            case 3:
                str = C10050jb.m7036mb(m7453h.f19100b.getClickPauseLabel(), "click_pause");
                C10048h.m7084ox(downloadInfo, jSONObject);
                break;
            case 4:
                str = C10050jb.m7036mb(m7453h.f19100b.getClickContinueLabel(), "click_continue");
                C10048h.m7089b(downloadInfo, jSONObject);
                break;
            case 5:
                if (downloadInfo != null) {
                    try {
                        C10048h.m7086mb(jSONObject, downloadInfo.getId());
                        C9998mb.m7194ox(jSONObject, downloadInfo);
                    } catch (Throwable unused) {
                    }
                }
                strArr = new String[]{m7453h.f19100b.getClickInstallLabel(), "click_install"};
                str = C10050jb.m7036mb(strArr);
                break;
        }
        m7301mb(null, str, jSONObject, 0L, 1, m7453h);
    }

    /* renamed from: mb */
    public void m7312mb(long j, BaseException baseException) {
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("download_time", 0);
            if (baseException != null) {
                jSONObject.putOpt("fail_status", Integer.valueOf(baseException.getErrorCode()));
                jSONObject.putOpt("fail_msg", baseException.getErrorMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m7293ox("download_failed", jSONObject, m7453h);
    }

    /* renamed from: mb */
    public void m7311mb(long j, boolean z, int i) {
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        if (m7453h.m7475on()) {
            C9971b.m7285mb().m7284mb("sendQuickAppEvent ModelBox notValid");
        } else if (m7453h.f19103ox.getQuickAppModel() == null) {
        } else {
            if (m7453h.f19103ox instanceof AdDownloadModel) {
                ((AdDownloadModel) m7453h.f19103ox).setFunnelType(3);
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("download_click_type", Integer.valueOf(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            m7293ox(z ? "deeplink_quickapp_success" : "deeplink_quickapp_failed", jSONObject, m7453h);
        }
    }

    /* renamed from: mb */
    public void m7308mb(DownloadInfo downloadInfo) {
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            C10048h.m7089b(downloadInfo, jSONObject);
            m7442mb.m7773mb(System.currentTimeMillis());
            m7300mb(m7442mb.mo7470x(), "download_resume", jSONObject, m7442mb);
            C9926ww.m7430mb().m7429mb(m7442mb);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: mb */
    public void m7307mb(DownloadInfo downloadInfo, BaseException baseException) {
        C9837ox m7442mb;
        if (downloadInfo == null || (m7442mb = C9923u.m7451mb().m7442mb(downloadInfo)) == null || m7442mb.f18842b.get()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            C9998mb.m7199mb(jSONObject, downloadInfo);
            jSONObject.putOpt("fail_status", Integer.valueOf(m7442mb.m7811fu()));
            jSONObject.putOpt("fail_msg", m7442mb.m7813ep());
            jSONObject.put("download_failed_times", m7442mb.m7762on());
            if (downloadInfo.getTotalBytes() > 0) {
                jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
            }
            jSONObject.put("download_status", downloadInfo.getRealStatus());
            long currentTimeMillis = System.currentTimeMillis();
            if (m7442mb.m7740wn() > 0) {
                jSONObject.put("time_from_start_download", currentTimeMillis - m7442mb.m7740wn());
            }
            if (m7442mb.m7809ge() > 0) {
                jSONObject.put("time_from_download_resume", currentTimeMillis - m7442mb.m7809ge());
            }
            int i = 1;
            jSONObject.put("is_update_download", m7442mb.m7753qa() ? 1 : 2);
            jSONObject.put("can_show_notification", C10106hj.m6838mb() ? 1 : 2);
            if (!m7442mb.f18855hj.get()) {
                i = 2;
            }
            jSONObject.put("has_send_download_failed_finally", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m7300mb(m7442mb.mo7470x(), "download_cancel", jSONObject, m7442mb);
    }

    /* renamed from: mb */
    public void m7306mb(String str, int i, C9916h c9916h) {
        m7301mb(null, str, null, i, 0, c9916h);
    }

    /* renamed from: mb */
    public void m7305mb(String str, long j) {
        C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7452hj != null) {
            m7294ox(str, m7452hj);
        } else {
            m7294ox(str, C9923u.m7451mb().m7453h(j));
        }
    }

    /* renamed from: mb */
    public void m7304mb(String str, @NonNull DownloadModel downloadModel, @NonNull DownloadEventConfig downloadEventConfig, @NonNull DownloadController downloadController) {
        m7294ox(str, new C9916h(downloadModel.getId(), downloadModel, downloadEventConfig, downloadController));
    }

    /* renamed from: mb */
    public void m7303mb(String str, InterfaceC9836mb interfaceC9836mb) {
        m7298mb(str, (JSONObject) null, interfaceC9836mb);
    }

    /* renamed from: mb */
    public void m7302mb(String str, String str2, InterfaceC9836mb interfaceC9836mb) {
        m7300mb(str, str2, (JSONObject) null, interfaceC9836mb);
    }

    /* renamed from: mb */
    public void m7300mb(String str, String str2, JSONObject jSONObject, InterfaceC9836mb interfaceC9836mb) {
        m7301mb(str, str2, jSONObject, 0L, 0, interfaceC9836mb);
    }

    /* renamed from: mb */
    public void m7299mb(String str, JSONObject jSONObject, long j) {
        InterfaceC9836mb m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7452hj != null) {
            m7298mb(str, jSONObject, m7452hj);
            return;
        }
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        if (m7453h.m7475on()) {
            C9971b.m7285mb().m7284mb("sendUnityEvent ModelBox notValid");
        } else {
            m7298mb(str, jSONObject, m7453h);
        }
    }

    /* renamed from: mb */
    public void m7298mb(String str, JSONObject jSONObject, InterfaceC9836mb interfaceC9836mb) {
        JSONObject jSONObject2 = new JSONObject();
        C10050jb.m7040mb(jSONObject2, "unity_label", str);
        m7300mb("embeded_ad", "ttdownloader_unity", C10050jb.m7039mb(jSONObject, jSONObject2), interfaceC9836mb);
    }

    /* renamed from: mb */
    public void m7297mb(JSONObject jSONObject, @NonNull C9837ox c9837ox) {
        m7300mb(c9837ox.mo7470x(), "install_finish", jSONObject, c9837ox);
    }

    /* renamed from: ox */
    public void m7296ox(long j, @EventType int i) {
        m7313mb(j, i, (DownloadInfo) null);
    }

    /* renamed from: ox */
    public void m7295ox(DownloadInfo downloadInfo, BaseException baseException) {
        if (downloadInfo == null) {
            return;
        }
        C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
        if (m7442mb == null) {
            C9971b.m7285mb().m7284mb("sendDownloadFailedEvent nativeModel null");
        } else if (m7442mb.f18842b.get()) {
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                C10048h.m7089b(downloadInfo, jSONObject);
                C9998mb.m7199mb(jSONObject, downloadInfo);
                if (baseException != null) {
                    jSONObject.putOpt("fail_status", Integer.valueOf(baseException.getErrorCode()));
                    jSONObject.putOpt("fail_msg", baseException.getErrorMessage());
                    m7442mb.m7802hj(baseException.getErrorCode());
                    m7442mb.m7772mb(baseException.getErrorMessage());
                }
                m7442mb.m7789jq();
                jSONObject.put("download_failed_times", m7442mb.m7762on());
                if (downloadInfo.getTotalBytes() > 0) {
                    jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
                }
                int i = 1;
                jSONObject.put("has_send_download_failed_finally", m7442mb.f18855hj.get() ? 1 : 2);
                C10048h.m7088mb(m7442mb, jSONObject);
                if (!m7442mb.m7753qa()) {
                    i = 2;
                }
                jSONObject.put("is_update_download", i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            m7300mb(m7442mb.mo7470x(), "download_failed", jSONObject, m7442mb);
            C9926ww.m7430mb().m7429mb(m7442mb);
        }
    }

    /* renamed from: ox */
    public void m7294ox(String str, InterfaceC9836mb interfaceC9836mb) {
        m7302mb((String) null, str, interfaceC9836mb);
    }

    /* renamed from: ox */
    public void m7293ox(String str, JSONObject jSONObject, InterfaceC9836mb interfaceC9836mb) {
        m7300mb((String) null, str, jSONObject, interfaceC9836mb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.event.AdEventHandler$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9968mb {

        /* renamed from: mb */
        private static AdEventHandler f19213mb = new AdEventHandler();
    }
}
