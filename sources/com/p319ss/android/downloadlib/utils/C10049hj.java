package com.p319ss.android.downloadlib.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.hj */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10049hj {
    @Nullable
    /* renamed from: mb */
    public static JSONObject m7074mb() {
        return C9940x.m7364lz().optJSONObject("ad");
    }

    /* renamed from: mb */
    public static JSONObject m7072mb(DownloadModel downloadModel) {
        if (downloadModel == null) {
            return null;
        }
        if (downloadModel.isAd()) {
            return m7074mb();
        }
        return downloadModel.getDownloadSettings();
    }

    @NonNull
    /* renamed from: ox */
    public static DownloadSetting m7067ox(DownloadModel downloadModel) {
        return DownloadSetting.obtain(m7072mb(downloadModel));
    }

    @NonNull
    /* renamed from: mb */
    public static DownloadSetting m7071mb(InterfaceC9836mb interfaceC9836mb) {
        if (interfaceC9836mb == null) {
            return DownloadSetting.obtainGlobal();
        }
        if (interfaceC9836mb.mo7479m() != 0) {
            return DownloadSetting.obtain(interfaceC9836mb.mo7479m());
        }
        if (interfaceC9836mb.mo7494b()) {
            return DownloadSetting.obtain(m7074mb());
        }
        if (interfaceC9836mb.mo7482l() != null) {
            return DownloadSetting.obtain(interfaceC9836mb.mo7482l());
        }
        return DownloadSetting.obtainGlobal();
    }

    /* renamed from: mb */
    public static int m7070mb(@NonNull DownloadSetting downloadSetting) {
        return downloadSetting.optInt("external_storage_permission_path_type", 0);
    }

    /* renamed from: b */
    public static int m7080b(@NonNull DownloadModel downloadModel) {
        return m7070mb(m7067ox(downloadModel));
    }

    /* renamed from: ox */
    public static boolean m7066ox(InterfaceC9836mb interfaceC9836mb) {
        return m7071mb(interfaceC9836mb).optInt("pause_reserve_on_wifi", 0) == 1 && interfaceC9836mb.mo7492e();
    }

    /* renamed from: mb */
    public static double m7073mb(int i) {
        return DownloadSetting.obtain(i).optDouble("clean_min_install_size", 0.0d);
    }

    /* renamed from: ox */
    public static long m7068ox(int i) {
        return DownloadSetting.obtain(i).optLong("storage_min_size", 0L);
    }

    /* renamed from: b */
    public static long m7081b(int i) {
        return DownloadSetting.obtain(i).optLong("clean_fetch_apk_head_time_out", 800L);
    }

    /* renamed from: hj */
    public static boolean m7076hj(int i) {
        return DownloadSetting.obtain(i).optLong("clean_fetch_apk_switch", 0L) == 1;
    }

    /* renamed from: h */
    public static boolean m7078h(int i) {
        return DownloadSetting.obtain(i).optLong("clean_space_before_download_switch", 0L) == 1;
    }

    /* renamed from: u */
    public static boolean m7064u(int i) {
        return DownloadSetting.obtain(i).optInt("clean_space_switch", 0) == 1;
    }

    /* renamed from: ko */
    public static boolean m7075ko(int i) {
        return DownloadSetting.obtain(i).optInt("clean_app_cache_dir", 0) == 1;
    }

    /* renamed from: ox */
    public static boolean m7065ox(DownloadSetting downloadSetting) {
        return downloadSetting != null && downloadSetting.optInt("kllk_need_rename_apk", 0) == 1;
    }

    /* renamed from: ox */
    public static boolean m7069ox() {
        return DownloadSetting.obtainGlobal().optBugFix("fix_notification_anr");
    }

    /* renamed from: b */
    public static boolean m7082b() {
        return C9940x.m7364lz().optInt("is_enable_start_install_again") == 1;
    }

    /* renamed from: hj */
    public static long m7077hj() {
        long optLong = C9940x.m7364lz().optLong("start_install_interval");
        if (optLong == 0) {
            return 300000L;
        }
        return optLong;
    }

    /* renamed from: h */
    public static long m7079h() {
        long optLong = C9940x.m7364lz().optLong("next_install_min_interval");
        if (optLong == 0) {
            return 10000L;
        }
        return optLong;
    }
}
