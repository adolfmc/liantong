package com.p319ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.p319ss.android.downloadlib.C9992ko;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;

/* renamed from: com.ss.android.downloadlib.addownload.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9930ox {

    /* renamed from: mb */
    private static volatile C9930ox f19143mb;

    /* renamed from: ox */
    private Handler f19144ox = null;

    /* renamed from: mb */
    public static C9930ox m7423mb() {
        if (f19143mb == null) {
            synchronized (C9930ox.class) {
                if (f19143mb == null) {
                    f19143mb = new C9930ox();
                }
            }
        }
        return f19143mb;
    }

    /* renamed from: mb */
    public void m7422mb(Context context, DownloadInfo downloadInfo) {
        if (m7421ox() && downloadInfo != null) {
            try {
                File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.f19144ox == null) {
                this.f19144ox = new Handler(Looper.getMainLooper());
            }
            final String url = downloadInfo.getUrl();
            Downloader.getInstance(context).clearDownloadData(downloadInfo.getId());
            this.f19144ox.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.ox.1
                @Override // java.lang.Runnable
                public void run() {
                    C9940x.m7377b().mo7905mb(3, C9940x.getContext(), null, "下载失败，请重试！", null, 0);
                    C9878h m7228mb = C9992ko.m7236mb().m7228mb(url);
                    if (m7228mb != null) {
                        m7228mb.m7628ko();
                    }
                }
            });
        }
    }

    /* renamed from: ox */
    public boolean m7421ox() {
        return C9940x.m7364lz().optInt("forbid_invalidte_download_file_install", 0) == 1;
    }
}
