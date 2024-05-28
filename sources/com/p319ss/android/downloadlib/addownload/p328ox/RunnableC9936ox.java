package com.p319ss.android.downloadlib.addownload.p328ox;

import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.ox.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RunnableC9936ox implements Runnable {

    /* renamed from: mb */
    private DownloadInfo f19153mb;

    public RunnableC9936ox(DownloadInfo downloadInfo) {
        this.f19153mb = downloadInfo;
    }

    @Override // java.lang.Runnable
    public void run() {
        final C9837ox m7442mb;
        if (this.f19153mb == null || (m7442mb = C9923u.m7451mb().m7442mb(this.f19153mb)) == null) {
            return;
        }
        AdEventHandler.m7315mb().m7303mb("cleanspace_task", m7442mb);
        long longValue = Double.valueOf((C10049hj.m7073mb(this.f19153mb.getId()) + 1.0d) * this.f19153mb.getTotalBytes()).longValue() - this.f19153mb.getCurBytes();
        long m7055mb = C10050jb.m7055mb(0L);
        if (C9940x.m7368je() != null) {
            C9940x.m7368je().m7941h();
        }
        C9932b.m7420mb();
        C9932b.m7416ox();
        if (C10049hj.m7075ko(m7442mb.mo7479m())) {
            C9932b.m7419mb(C9940x.getContext());
        }
        long m7055mb2 = C10050jb.m7055mb(0L);
        if (m7055mb2 >= longValue) {
            m7442mb.m7792je("1");
            C9926ww.m7430mb().m7429mb(m7442mb);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("quite_clean_size", Long.valueOf(m7055mb2 - m7055mb));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdEventHandler.m7315mb().m7298mb("cleanspace_download_after_quite_clean", jSONObject, m7442mb);
            Downloader.getInstance(C9940x.getContext()).restart(this.f19153mb.getId());
        } else if (C9940x.m7368je() != null) {
            m7442mb.m7799hj(false);
            C9934hj.m7414mb().m7410mb(m7442mb.mo7478mb(), new InterfaceC9933h() { // from class: com.ss.android.downloadlib.addownload.ox.ox.1
            });
            if (C9940x.m7368je().m7938mb(this.f19153mb.getId(), this.f19153mb.getUrl(), true, longValue)) {
                m7442mb.m7804h(true);
            }
        } else {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.putOpt("show_dialog_result", 3);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            AdEventHandler.m7315mb().m7298mb("cleanspace_window_show", jSONObject2, m7442mb);
        }
    }
}
