package com.p319ss.android.downloadlib.addownload;

import com.p319ss.android.download.api.model.DownloadShortInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.lz */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9904lz {
    /* renamed from: mb */
    public static int m7536mb(int i, int i2) {
        return (i2 <= 0 || i2 >= 100 || !m7537mb(i)) ? i2 : (int) (Math.sqrt(i2) * 10.0d);
    }

    /* renamed from: mb */
    public static long m7535mb(int i, long j, long j2) {
        if (m7537mb(i)) {
            if (j <= 0) {
                return 0L;
            }
            return j2 <= 0 ? j : (j2 * m7536mb(i, (int) ((j * 100) / j2))) / 100;
        }
        return j;
    }

    /* renamed from: mb */
    public static DownloadShortInfo m7534mb(DownloadShortInfo downloadShortInfo) {
        if (downloadShortInfo == null || !m7537mb((int) downloadShortInfo.f18791id)) {
            return downloadShortInfo;
        }
        downloadShortInfo.currentBytes = m7535mb((int) downloadShortInfo.f18791id, downloadShortInfo.currentBytes, downloadShortInfo.totalBytes);
        return downloadShortInfo;
    }

    /* renamed from: mb */
    private static boolean m7537mb(int i) {
        return DownloadSetting.obtain(i).optInt("pause_optimise_pretend_download_percent_switch", 0) == 1 && DownloadSetting.obtain(i).optInt("pause_optimise_switch", 0) == 1;
    }
}
