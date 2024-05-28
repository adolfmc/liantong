package com.p319ss.android.downloadlib.addownload.p328ox;

import com.p319ss.android.download.api.config.InterfaceC9796h;
import com.p319ss.android.downloadlib.C9998mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* renamed from: com.ss.android.downloadlib.addownload.ox.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9935mb implements IDownloadDiskSpaceHandler {

    /* renamed from: mb */
    private int f19152mb;

    /* renamed from: mb */
    public void m7406mb(int i) {
        this.f19152mb = i;
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler
    public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback) {
        long j3;
        DownloadSetting obtain = DownloadSetting.obtain(this.f19152mb);
        if (m7404mb(obtain)) {
            long currentTimeMillis = System.currentTimeMillis();
            C9934hj.m7414mb().m7415b();
            long m7055mb = C10050jb.m7055mb(0L);
            m7407mb();
            long m7055mb2 = C10050jb.m7055mb(0L);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (m7055mb2 < j2) {
                long m7403ox = m7403ox(obtain);
                if (m7403ox > 0) {
                    j3 = m7403ox;
                    m7055mb2 = C10050jb.m7055mb(0L);
                } else {
                    j3 = m7403ox;
                }
            } else {
                j3 = 0;
            }
            C10070x.m6966ox("AppDownloadDiskSpaceHandler", "cleanUpDisk, byteRequired = " + j2 + ", byteAvailableAfter = " + m7055mb2 + ", cleaned = " + (m7055mb2 - m7055mb), null);
            m7405mb(m7055mb, m7055mb2, j2, currentTimeMillis2, j3);
            if (m7055mb2 < j2) {
                return false;
            }
            if (iDownloadDiskSpaceCallback != null) {
                iDownloadDiskSpaceCallback.onDiskCleaned();
                return true;
            }
            return true;
        }
        return false;
    }

    /* renamed from: mb */
    private boolean m7404mb(DownloadSetting downloadSetting) {
        if (downloadSetting.optInt("clear_space_use_disk_handler", 0) != 1) {
            return false;
        }
        return System.currentTimeMillis() - C9934hj.m7414mb().m7409ox() >= downloadSetting.optLong("clear_space_min_time_interval", 600000L);
    }

    /* renamed from: mb */
    private void m7407mb() {
        InterfaceC9796h m7365lc = C9940x.m7365lc();
        if (m7365lc != null) {
            m7365lc.m7949mb();
        }
        C9932b.m7420mb();
        C9932b.m7416ox();
    }

    /* renamed from: ox */
    private long m7403ox(DownloadSetting downloadSetting) {
        long optLong = downloadSetting.optLong("clear_space_sleep_time", 0L);
        if (optLong <= 0) {
            return 0L;
        }
        long j = optLong <= 5000 ? optLong : 5000L;
        C10070x.m6966ox("AppDownloadDiskSpaceHandler", "waiting for space clear, sleepTime = " + j, null);
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        C10070x.m6966ox("AppDownloadDiskSpaceHandler", "waiting end!", null);
        return j;
    }

    /* renamed from: mb */
    private void m7405mb(long j, long j2, long j3, long j4, long j5) {
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(this.f19152mb);
        if (downloadInfo == null) {
            return;
        }
        try {
            C9998mb.m7215mb().m7206mb(downloadInfo, j, j2, j3, j4, j5, j2 > j3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
