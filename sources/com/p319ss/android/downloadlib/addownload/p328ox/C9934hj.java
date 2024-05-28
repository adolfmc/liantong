package com.p319ss.android.downloadlib.addownload.p328ox;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.ss.android.downloadlib.addownload.ox.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9934hj {

    /* renamed from: mb */
    private static volatile C9934hj f19147mb;

    /* renamed from: ox */
    private long f19151ox = 0;

    /* renamed from: b */
    private ConcurrentHashMap<String, InterfaceC9933h> f19148b = new ConcurrentHashMap<>();

    /* renamed from: hj */
    private HashMap<String, Integer> f19150hj = new HashMap<>();

    /* renamed from: h */
    private List<String> f19149h = new CopyOnWriteArrayList();

    /* renamed from: mb */
    public static C9934hj m7414mb() {
        if (f19147mb == null) {
            synchronized (C9934hj.class) {
                if (f19147mb == null) {
                    f19147mb = new C9934hj();
                }
            }
        }
        return f19147mb;
    }

    /* renamed from: mb */
    public void m7410mb(String str, InterfaceC9933h interfaceC9933h) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f19148b.put(str, interfaceC9933h);
    }

    /* renamed from: mb */
    public void m7411mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f19148b.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: ox */
    public long m7409ox() {
        return this.f19151ox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m7415b() {
        this.f19151ox = System.currentTimeMillis();
    }

    /* renamed from: ox */
    public int m7408ox(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (this.f19150hj == null) {
            this.f19150hj = new HashMap<>();
        }
        if (this.f19150hj.containsKey(str)) {
            return this.f19150hj.get(str).intValue();
        }
        return 0;
    }

    @WorkerThread
    /* renamed from: mb */
    public static void m7413mb(C9837ox c9837ox) {
        DownloadInfo downloadInfo;
        if (c9837ox == null || c9837ox.mo7474ox() <= 0 || (downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(c9837ox.mo7479m())) == null) {
            return;
        }
        m7412mb(downloadInfo);
    }

    @WorkerThread
    /* renamed from: mb */
    public static void m7412mb(DownloadInfo downloadInfo) {
        if (downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt("delete_file_after_install", 0) == 0) {
            return;
        }
        try {
            String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
            if (TextUtils.isEmpty(str)) {
                return;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
