package com.p319ss.android.downloadlib.p329b;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9998mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.monitor.InnerEventListener;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.b.h */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9951h implements InnerEventListener {
    @Override // com.p319ss.android.socialbase.downloader.monitor.InnerEventListener
    public void onEvent(int i, String str, JSONObject jSONObject) {
        C9837ox m7442mb;
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(i);
        if (downloadInfo == null || (m7442mb = C9923u.m7451mb().m7442mb(downloadInfo)) == null) {
            return;
        }
        if ("install_view_result".equals(str)) {
            jSONObject = C10050jb.m7042mb(jSONObject);
            C9998mb.m7199mb(jSONObject, downloadInfo);
            C10050jb.m7040mb(jSONObject, "model_id", Long.valueOf(m7442mb.mo7474ox()));
        }
        AdEventHandler.m7315mb().m7293ox(str, jSONObject, m7442mb);
    }

    @Override // com.p319ss.android.socialbase.downloader.monitor.InnerEventListener
    public void onUnityEvent(int i, String str, JSONObject jSONObject) {
        C9837ox m7442mb;
        DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(i);
        if (downloadInfo == null || (m7442mb = C9923u.m7451mb().m7442mb(downloadInfo)) == null) {
            return;
        }
        AdEventHandler.m7315mb().m7298mb(str, jSONObject, m7442mb);
    }
}
