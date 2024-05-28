package com.p319ss.android.downloadlib.addownload.p328ox;

import android.content.Context;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.ox.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9932b {
    /* renamed from: mb */
    public static void m7420mb() {
        List<DownloadInfo> m6813mb = C10112hj.m6786x().m6813mb(C9940x.getContext());
        if (m6813mb == null || m6813mb.size() <= 0) {
            return;
        }
        for (int i = 0; i < m6813mb.size(); i++) {
            DownloadInfo downloadInfo = m6813mb.get(i);
            File file = new File(downloadInfo.getTempPath(), downloadInfo.getTempName());
            long lastModified = file.lastModified();
            long optInt = DownloadSetting.obtain(downloadInfo.getId()).optInt("download_file_expire_hours", 0) * 3600000;
            if (optInt <= 0) {
                optInt = 604800000;
            }
            if (file.isFile() && file.exists() && System.currentTimeMillis() - lastModified >= optInt) {
                m7418mb(file);
                Downloader.getInstance(C9940x.getContext()).clearDownloadData(downloadInfo.getId());
            }
        }
    }

    /* renamed from: ox */
    public static void m7416ox() {
        DownloadInfo downloadInfo;
        List successedDownloadInfosWithMimeType = Downloader.getInstance(C9940x.getContext()).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive");
        if (successedDownloadInfosWithMimeType == null || successedDownloadInfosWithMimeType.isEmpty()) {
            return;
        }
        for (int i = 0; i < successedDownloadInfosWithMimeType.size(); i++) {
            if (((DownloadInfo) successedDownloadInfosWithMimeType.get(i)) != null) {
                String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
                File file = new File(str);
                if (file.exists()) {
                    long currentTimeMillis = System.currentTimeMillis() - file.lastModified();
                    long optInt = DownloadSetting.obtain(downloadInfo.getId()).optInt("download_complete_file_expire_hours", 0) * 3600000;
                    if (optInt <= 0) {
                        optInt = 604800000;
                    }
                    int i2 = (currentTimeMillis > optInt ? 1 : (currentTimeMillis == optInt ? 0 : -1));
                    boolean z = true;
                    if (i2 < 0 && !C10050jb.m7061h(C9940x.getContext(), str)) {
                        z = false;
                    }
                    if (z) {
                        m7418mb(file);
                    }
                }
            }
        }
    }

    /* renamed from: mb */
    public static void m7419mb(Context context) {
        File externalCacheDir;
        if (context == null || (externalCacheDir = context.getExternalCacheDir()) == null) {
            return;
        }
        try {
            m7417mb(externalCacheDir.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m7418mb(java.io.File r3) {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L18
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L18
            java.lang.String r0 = "1"
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2d
            r1.write(r0)     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2d
            r1.close()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2d
            goto L29
        L13:
            r0 = move-exception
            goto L1c
        L15:
            r3 = move-exception
            r1 = r0
            goto L2e
        L18:
            r1 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L1c:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2d
            if (r1 == 0) goto L29
            r1.close()     // Catch: java.lang.Exception -> L25
            goto L29
        L25:
            r0 = move-exception
            r0.printStackTrace()
        L29:
            r3.delete()
            return
        L2d:
            r3 = move-exception
        L2e:
            if (r1 == 0) goto L38
            r1.close()     // Catch: java.lang.Exception -> L34
            goto L38
        L34:
            r0 = move-exception
            r0.printStackTrace()
        L38:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.addownload.p328ox.C9932b.m7418mb(java.io.File):void");
    }

    /* renamed from: mb */
    private static void m7417mb(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null) {
                return;
            }
            for (String str2 : list) {
                if (str2 != null) {
                    String str3 = str.endsWith(File.separator) ? str + str2 : str + File.separator + str2;
                    File file2 = new File(str3);
                    if (file2.isFile()) {
                        file2.delete();
                    }
                    if (file2.isDirectory()) {
                        m7417mb(str3);
                    }
                }
            }
            file.delete();
        }
    }
}
