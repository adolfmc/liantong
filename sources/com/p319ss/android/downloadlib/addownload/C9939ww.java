package com.p319ss.android.downloadlib.addownload;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.p329b.C9958mb;
import com.p319ss.android.downloadlib.p329b.C9960ox;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.C10126mb;
import com.p319ss.android.socialbase.appdownloader.C10140ox;
import com.p319ss.android.socialbase.appdownloader.C10149u;
import com.p319ss.android.socialbase.appdownloader.p340u.C10153mb;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.ww */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9939ww {
    /* renamed from: mb */
    public static boolean m7387mb(int i) {
        return i == 0 || i == 1;
    }

    /* renamed from: ox */
    public static boolean m7379ox(int i) {
        return i == 2 || i == 1;
    }

    /* renamed from: mb */
    public static boolean m7386mb(DownloadModel downloadModel) {
        return downloadModel.isAd() && (downloadModel instanceof AdDownloadModel) && downloadModel.getModelType() == 1;
    }

    /* renamed from: ox */
    public static boolean m7378ox(DownloadModel downloadModel) {
        return downloadModel != null && downloadModel.getModelType() == 2;
    }

    /* renamed from: mb */
    public static boolean m7385mb(DownloadModel downloadModel, IDownloadButtonClickListener iDownloadButtonClickListener) {
        return downloadModel.isAd() && iDownloadButtonClickListener != null;
    }

    /* renamed from: mb */
    public static int m7383mb(@NonNull C9916h c9916h, boolean z, C10149u c10149u) {
        int i;
        String str;
        if (c10149u == null || TextUtils.isEmpty(c10149u.m6648mb()) || c10149u.getContext() == null) {
            return 0;
        }
        try {
            i = m7381mb(c10149u, c10149u.m6648mb());
        } catch (Throwable th) {
            C9940x.m7363m().mo7282mb(th, "redirectSavePathIfPossible");
            i = 4;
        }
        c10149u.m6647mb(i);
        if (i == 0) {
            c10149u.m6644mb(new C9958mb());
        }
        if (!c10149u.m6685cd()) {
            c10149u.m6644mb(new C9960ox());
        }
        int m6802mb = C10112hj.m6786x().m6802mb(c10149u);
        C9837ox m7384mb = m7384mb(c9916h, m6802mb);
        C9923u.m7451mb().m7445mb(m7384mb);
        m7384mb.m7785ko(m6802mb);
        m7384mb.m7738ww(System.currentTimeMillis());
        m7384mb.m7778lz(0L);
        DownloadSetting obtain = DownloadSetting.obtain(c10149u.m6660kg());
        if (!m7382mb(c10149u, obtain, m6802mb) && c9916h.f19103ox.isShowToast()) {
            String startToast = c9916h.f19103ox.getStartToast();
            if (TextUtils.isEmpty(startToast)) {
                startToast = obtain.optString("download_start_toast_text");
            }
            if (TextUtils.isEmpty(startToast)) {
                str = z ? "已开始下载，可在\"我的\"里查看管理" : "已开始下载";
            } else {
                str = startToast;
            }
            C9940x.m7377b().mo7905mb(2, c10149u.getContext(), c9916h.f19103ox, str, null, 0);
        }
        return m6802mb;
    }

    /* renamed from: mb */
    private static C9837ox m7384mb(C9916h c9916h, int i) {
        C9837ox c9837ox = new C9837ox(c9916h.f19103ox, c9916h.f19100b, c9916h.f19101hj, i);
        if (DownloadSetting.obtain(i).optInt("download_event_opt", 1) > 1) {
            try {
                String packageName = c9916h.f19103ox.getPackageName();
                if (!TextUtils.isEmpty(packageName)) {
                    c9837ox.m7736ww(C9940x.getContext().getPackageManager().getPackageInfo(packageName, 0) != null);
                }
            } catch (Throwable unused) {
            }
        }
        return c9837ox;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: mb */
    private static boolean m7382mb(C10149u c10149u, @NonNull DownloadSetting downloadSetting, int i) {
        String optString;
        char c;
        JSONArray optJSONArray = downloadSetting.optJSONArray("ah_plans");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return false;
        }
        int length = optJSONArray.length();
        int i2 = 0;
        JSONObject jSONObject = null;
        while (true) {
            if (i2 < length) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null && ((optString = optJSONObject.optString("type")) == "plan_c" || C10153mb.m6555mb(optJSONObject))) {
                    switch (optString.hashCode()) {
                        case -985763637:
                            if (optString.equals("plan_a")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763636:
                            if (optString.equals("plan_b")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763635:
                            if (optString.equals("plan_c")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763634:
                            if (optString.equals("plan_d")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763633:
                            if (optString.equals("plan_e")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763632:
                            if (optString.equals("plan_f")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763631:
                            if (optString.equals("plan_g")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case -985763630:
                            if (optString.equals("plan_h")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            if (C10140ox.m6704mb(optJSONObject, downloadSetting).f19537ox == 0) {
                                break;
                            } else {
                                continue;
                            }
                        case 6:
                            if (C10140ox.m6697ox(optJSONObject, downloadSetting).f19537ox == 0) {
                                break;
                            } else {
                                continue;
                            }
                        case 7:
                            jSONObject = optJSONObject;
                            continue;
                    }
                }
                i2++;
            }
        }
        if (jSONObject != null) {
            if (jSONObject.optInt("show_unknown_source_on_startup") == 1) {
                return C10140ox.m6716mb(DownloadComponentManager.getAppContext(), (Intent) null, jSONObject, i, new C10126mb());
            }
        }
        return false;
    }

    /* renamed from: mb */
    public static String m7380mb(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return null;
        }
        try {
            String extra = downloadInfo.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                return new JSONObject(extra).optString("notification_jump_url", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: mb */
    private static int m7381mb(C10149u c10149u, String str) {
        DownloadSetting obtain = DownloadSetting.obtain(c10149u.m6660kg());
        JSONObject optJSONObject = obtain.optJSONObject("download_dir");
        if (optJSONObject == null || TextUtils.isEmpty(optJSONObject.optString("dir_name"))) {
            return -1;
        }
        String m6626ox = c10149u.m6626ox();
        String m6691al = c10149u.m6691al();
        if (TextUtils.isEmpty(m6691al)) {
            m6691al = C10085b.m6900mb(str, m6626ox, c10149u.m6663je(), true);
        }
        if (m6691al.length() > 255) {
            m6691al = m6691al.substring(m6691al.length() - 255);
        }
        if (TextUtils.isEmpty(m6626ox)) {
            m6626ox = m6691al;
        }
        String m6690b = c10149u.m6690b();
        if (TextUtils.isEmpty(m6690b)) {
            m6690b = C10085b.m6899ox();
        }
        String str2 = m6690b + File.separator + C10085b.m6901mb(m6626ox, obtain);
        DownloadInfo m6810mb = C10112hj.m6786x().m6810mb(c10149u.getContext(), str);
        if (m6810mb != null && m6810mb.isSavePathRedirected()) {
            c10149u.m6688b(m6810mb.getSavePath());
            try {
                c10149u.m6638mb(new JSONObject(m6810mb.getDownloadSettingString()));
                return 0;
            } catch (Throwable unused) {
                return 0;
            }
        } else if (m6810mb != null || !"application/vnd.android.package-archive".equalsIgnoreCase(C10112hj.m6786x().m6796mb(m6691al, c10149u.m6663je()))) {
            return m6810mb != null ? 8 : 9;
        } else {
            int m6707mb = C10140ox.m6707mb(obtain);
            if (m6707mb == 0) {
                c10149u.m6688b(str2);
                return m6707mb;
            }
            return m6707mb;
        }
    }
}
