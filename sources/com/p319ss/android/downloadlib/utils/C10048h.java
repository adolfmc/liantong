package com.p319ss.android.downloadlib.utils;

import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.socialbase.appdownloader.C10123ko;
import com.p319ss.android.socialbase.appdownloader.C10140ox;
import com.p319ss.android.socialbase.appdownloader.p336h.C10106hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10153mb;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10048h {
    /* renamed from: b */
    public static void m7089b(DownloadInfo downloadInfo, JSONObject jSONObject) {
        if (downloadInfo != null) {
            try {
                jSONObject.putOpt("total_bytes", Long.valueOf(downloadInfo.getTotalBytes()));
                jSONObject.putOpt("cur_bytes", Long.valueOf(downloadInfo.getCurBytes()));
                jSONObject.putOpt("chunk_count", Integer.valueOf(downloadInfo.getChunkCount()));
                jSONObject.putOpt("app_name", downloadInfo.getTitle());
                jSONObject.putOpt("network_quality", downloadInfo.getNetworkQuality());
                jSONObject.putOpt("save_path", downloadInfo.getSavePath());
                jSONObject.putOpt("file_name", downloadInfo.getName());
                jSONObject.putOpt("download_status", Integer.valueOf(downloadInfo.getRealStatus()));
                C9837ox m7450mb = C9923u.m7451mb().m7450mb(downloadInfo.getId());
                if (m7450mb != null) {
                    jSONObject.putOpt("click_download_time", Long.valueOf(m7450mb.m7751sa()));
                    jSONObject.putOpt("click_download_size", Long.valueOf(m7450mb.m7750sr()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        int i = 1;
        jSONObject.putOpt("permission_notification", Integer.valueOf(C10106hj.m6838mb() ? 1 : 2));
        jSONObject.putOpt("network_available", Integer.valueOf(DownloadUtils.isNetworkConnected(C9940x.getContext()) ? 1 : 2));
        if (!DownloadUtils.isWifi(C9940x.getContext())) {
            i = 2;
        }
        jSONObject.putOpt("network_is_wifi", Integer.valueOf(i));
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
    public static java.lang.String h1672829046082dc(java.lang.String r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.utils.C10048h.h1672829046082dc(java.lang.String):java.lang.String");
    }

    /* renamed from: mb */
    public static JSONObject m7085mb(@NonNull JSONObject jSONObject, InterfaceC9836mb interfaceC9836mb) {
        C10050jb.m7040mb(jSONObject, "open_url", C10050jb.m7036mb(interfaceC9836mb.mo7472u(), "open_url_not_exist"));
        return jSONObject;
    }

    /* renamed from: mb */
    public static void m7088mb(C9837ox c9837ox, JSONObject jSONObject) {
        if (jSONObject == null || c9837ox == null) {
            return;
        }
        try {
            jSONObject.put("is_patch_apply_handled", c9837ox.m7769n() ? 1 : 0);
            jSONObject.put("origin_mime_type", c9837ox.m7748tl());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: mb */
    public static void m7087mb(DownloadInfo downloadInfo, JSONObject jSONObject) {
        try {
            m7089b(downloadInfo, jSONObject);
            C9837ox m7442mb = C9923u.m7451mb().m7442mb(downloadInfo);
            if (m7442mb == null) {
                return;
            }
            jSONObject.put("is_update_download", m7442mb.m7753qa() ? 1 : 2);
            m7088mb(m7442mb, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: mb */
    public static void m7086mb(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            return;
        }
        JSONArray optJSONArray = DownloadSetting.obtain(i).optJSONArray("ah_report_config");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    String string = optJSONArray.getString(i2);
                    C10123ko.C10124mb m6559mb = C10153mb.m6559mb(string);
                    if (m6559mb != null) {
                        String replaceAll = string.replaceAll("\\.", "_");
                        jSONObject.put(replaceAll, m6559mb.m6749u() + "_" + m6559mb.m6757ko());
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        try {
            jSONObject.put("is_unknown_source_enabled", C10140ox.m6720mb(DownloadComponentManager.getAppContext()) ? 1 : 2);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: ox */
    public static JSONObject m7083ox(@NonNull JSONObject jSONObject, InterfaceC9836mb interfaceC9836mb) {
        C10050jb.m7040mb(jSONObject, C10152hj.m6570lz().replaceAll("\\.", "_"), Integer.valueOf(C10050jb.m7032ox(C9940x.getContext(), C10152hj.m6570lz())));
        return jSONObject;
    }

    /* renamed from: ox */
    public static void m7084ox(DownloadInfo downloadInfo, JSONObject jSONObject) {
        C9837ox m7442mb;
        if (jSONObject == null || (m7442mb = C9923u.m7451mb().m7442mb(downloadInfo)) == null) {
            return;
        }
        try {
            m7089b(downloadInfo, jSONObject);
            jSONObject.putOpt("time_after_click", Long.valueOf(System.currentTimeMillis() - m7442mb.m7751sa()));
            jSONObject.putOpt("click_download_size", Long.valueOf(m7442mb.m7750sr()));
            jSONObject.putOpt("download_length", Long.valueOf(downloadInfo.getCurBytes()));
            jSONObject.putOpt("download_apk_size", Long.valueOf(downloadInfo.getTotalBytes()));
            m7442mb.m7767ng();
            C9926ww.m7430mb().m7429mb(m7442mb);
            jSONObject.put("click_pause_times", m7442mb.m7822a());
            long totalBytes = downloadInfo.getTotalBytes();
            long curBytes = downloadInfo.getCurBytes();
            jSONObject.put("download_percent", (curBytes < 0 || totalBytes <= 0) ? 0.0d : curBytes / totalBytes);
            jSONObject.put("download_status", downloadInfo.getRealStatus());
            long currentTimeMillis = System.currentTimeMillis();
            long m7740wn = m7442mb.m7740wn();
            if (m7740wn > 0) {
                jSONObject.put("time_from_start_download", currentTimeMillis - m7740wn);
            }
            long m7809ge = m7442mb.m7809ge();
            if (m7809ge > 0) {
                jSONObject.put("time_from_download_resume", currentTimeMillis - m7809ge);
            }
            jSONObject.putOpt("fail_status", Integer.valueOf(m7442mb.m7811fu()));
            jSONObject.putOpt("fail_msg", m7442mb.m7813ep());
            jSONObject.put("download_failed_times", m7442mb.m7762on());
            jSONObject.put("can_show_notification", C10106hj.m6838mb() ? 1 : 2);
            jSONObject.put("first_speed_time", downloadInfo.getFirstSpeedTime());
            jSONObject.put("all_connect_time", downloadInfo.getAllConnectTime());
            jSONObject.put("download_prepare_time", downloadInfo.getDownloadPrepareTime());
            jSONObject.put("download_time", downloadInfo.getRealDownloadTime() + downloadInfo.getAllConnectTime() + downloadInfo.getDownloadPrepareTime());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
