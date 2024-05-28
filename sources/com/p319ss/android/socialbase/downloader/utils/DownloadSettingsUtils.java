package com.p319ss.android.socialbase.downloader.utils;

import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.utils.DownloadSettingsUtils */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadSettingsUtils {
    public static boolean isOptimizeHeadRequest(DownloadInfo downloadInfo) {
        return downloadInfo != null && DownloadSetting.obtain(downloadInfo.getId()).optInt("optimize_head_request") == 1;
    }

    public static boolean isOptimizeSavePath(DownloadInfo downloadInfo) {
        return downloadInfo != null && DownloadSetting.obtain(downloadInfo.getId()).optInt("optimize_save_path") == 1;
    }

    public static boolean isOptimizeAddListener(DownloadInfo downloadInfo) {
        return downloadInfo != null && DownloadSetting.obtain(downloadInfo.getId()).optInt("optimize_add_listener") == 1;
    }
}
