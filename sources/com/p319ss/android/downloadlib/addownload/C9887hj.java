package com.p319ss.android.downloadlib.addownload;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9878h;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.C9926ww;
import com.p319ss.android.downloadlib.addownload.p328ox.C9932b;
import com.p319ss.android.downloadlib.addownload.p328ox.C9934hj;
import com.p319ss.android.downloadlib.addownload.p328ox.InterfaceC9933h;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.network.IFetchHttpHeadInfoListener;
import com.p319ss.android.socialbase.downloader.network.connectionpool.DownloadPreconnecter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9887hj {

    /* renamed from: b */
    private AtomicBoolean f19013b = new AtomicBoolean(false);

    /* renamed from: hj */
    private AtomicBoolean f19014hj = new AtomicBoolean(false);

    /* renamed from: mb */
    private Handler f19015mb;

    /* renamed from: ox */
    private C9916h f19016ox;

    /* renamed from: b */
    static /* synthetic */ long m7595b() {
        return m7594hj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C9887hj(Handler handler) {
        this.f19015mb = handler;
    }

    /* renamed from: mb */
    public void m7583mb(C9916h c9916h) {
        this.f19016ox = c9916h;
    }

    /* renamed from: mb */
    public boolean m7593mb() {
        return this.f19014hj.get();
    }

    /* renamed from: mb */
    public void m7578mb(boolean z) {
        this.f19014hj.set(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7591mb(final int i, final long j, long j2, final C9878h.InterfaceC9885mb interfaceC9885mb) {
        C9837ox c9837ox;
        this.f19014hj.set(false);
        if (interfaceC9885mb == null) {
            return;
        }
        if (!C10049hj.m7064u(i) || !C10049hj.m7078h(i)) {
            interfaceC9885mb.mo7596mb();
            return;
        }
        long m7081b = C10049hj.m7081b(i);
        this.f19013b.set(false);
        final String downloadUrl = this.f19016ox.f19103ox.getDownloadUrl();
        C9837ox m7435ox = C9923u.m7451mb().m7435ox(downloadUrl);
        if (m7435ox == null) {
            C9837ox c9837ox2 = new C9837ox(this.f19016ox.f19103ox, this.f19016ox.f19100b, this.f19016ox.f19101hj, 0);
            C9923u.m7451mb().m7445mb(c9837ox2);
            c9837ox = c9837ox2;
        } else {
            c9837ox = m7435ox;
        }
        c9837ox.m7804h(false);
        if (C9940x.m7368je() != null) {
            C9940x.m7368je().m7937mb(c9837ox.mo7474ox());
        }
        C9934hj.m7414mb().m7411mb(c9837ox.mo7478mb());
        boolean m7076hj = C10049hj.m7076hj(i);
        if (j2 > 0) {
            m7589mb(i, downloadUrl, j2, c9837ox, j, interfaceC9885mb);
        } else if (m7076hj) {
            final C9837ox c9837ox3 = c9837ox;
            m7580mb(downloadUrl, c9837ox, new C9878h.InterfaceC9886ox() { // from class: com.ss.android.downloadlib.addownload.hj.1
                @Override // com.p319ss.android.downloadlib.addownload.C9878h.InterfaceC9886ox
                /* renamed from: mb */
                public void mo7575mb(long j3) {
                    C9887hj.this.m7589mb(i, downloadUrl, j3, c9837ox3, j, interfaceC9885mb);
                }
            });
        } else {
            m7081b = 0;
        }
        this.f19015mb.postDelayed(new Runnable() { // from class: com.ss.android.downloadlib.addownload.hj.2
            @Override // java.lang.Runnable
            public void run() {
                if (C9887hj.this.f19013b.get()) {
                    return;
                }
                C9887hj.this.f19013b.set(true);
                interfaceC9885mb.mo7596mb();
            }
        }, m7081b);
    }

    /* renamed from: mb */
    private void m7580mb(String str, C9837ox c9837ox, final C9878h.InterfaceC9886ox interfaceC9886ox) {
        if (interfaceC9886ox == null) {
            return;
        }
        DownloadPreconnecter.asyncFetchHttpHeadInfo(str, new IFetchHttpHeadInfoListener() { // from class: com.ss.android.downloadlib.addownload.hj.3
            @Override // com.p319ss.android.socialbase.downloader.network.IFetchHttpHeadInfoListener
            public void onFetchFinished(Map<String, String> map) {
                if (C9887hj.this.f19013b.get()) {
                    return;
                }
                C9887hj.this.f19013b.set(true);
                long m7579mb = C9887hj.this.m7579mb(map);
                if (m7579mb > 0) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.putOpt("apk_size", Long.valueOf(m7579mb));
                        jSONObject.putOpt("available_space", Long.valueOf(C9887hj.m7595b()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                interfaceC9886ox.mo7575mb(m7579mb);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public long m7579mb(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return 0L;
        }
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue();
                if ("content-length".equalsIgnoreCase(entry.getKey())) {
                    return Long.parseLong(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m7589mb(int i, String str, long j, final C9837ox c9837ox, long j2, final C9878h.InterfaceC9885mb interfaceC9885mb) {
        this.f19013b.set(true);
        boolean z = false;
        if (j > 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("apk_size", Long.valueOf(j));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            long longValue = (Double.valueOf((C10049hj.m7073mb(i) + 1.0d) * j).longValue() + C10049hj.m7068ox(i)) - j2;
            long m7594hj = m7594hj();
            if (m7594hj < longValue) {
                m7587mb(c9837ox, jSONObject, longValue, m7594hj);
                m7588mb(c9837ox);
                long m7594hj2 = m7594hj();
                if (m7594hj2 < longValue) {
                    c9837ox.m7799hj(true);
                    final String mo7478mb = c9837ox.mo7478mb();
                    C9934hj.m7414mb().m7410mb(mo7478mb, new InterfaceC9933h() { // from class: com.ss.android.downloadlib.addownload.hj.4
                    });
                    z = m7590mb(i, c9837ox, str, longValue);
                    if (z) {
                        c9837ox.m7804h(true);
                    }
                } else {
                    m7576ox(c9837ox, jSONObject, m7594hj, m7594hj2);
                }
            }
        }
        if (z) {
            return;
        }
        this.f19015mb.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.hj.5
            @Override // java.lang.Runnable
            public void run() {
                interfaceC9885mb.mo7596mb();
            }
        });
    }

    /* renamed from: mb */
    private boolean m7590mb(int i, @NonNull C9837ox c9837ox, String str, long j) {
        if (C10049hj.m7064u(i)) {
            if (C9940x.m7368je() != null) {
                return C9940x.m7368je().m7938mb(i, str, true, j);
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("show_dialog_result", 3);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdEventHandler.m7315mb().m7298mb("cleanspace_window_show", jSONObject, c9837ox);
            return false;
        }
        return false;
    }

    /* renamed from: mb */
    public static boolean m7582mb(final DownloadInfo downloadInfo, long j) {
        int id = downloadInfo.getId();
        boolean z = false;
        if (C10049hj.m7064u(id)) {
            if (C9940x.m7368je() != null && (z = C9940x.m7368je().m7938mb(id, downloadInfo.getUrl(), false, j))) {
                C9934hj.m7414mb().m7410mb(downloadInfo.getUrl(), new InterfaceC9933h() { // from class: com.ss.android.downloadlib.addownload.hj.6
                });
            }
            return z;
        }
        return false;
    }

    /* renamed from: mb */
    public static JSONObject m7581mb(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("clean_space_install_params", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: mb */
    public static void m7592mb(int i) {
        if (C10049hj.m7064u(i) && C9940x.m7368je() != null && C9940x.m7368je().m7936ox()) {
            C9940x.m7368je().m7942b();
        }
    }

    /* renamed from: ox */
    public static long m7577ox() {
        if (C9940x.m7368je() != null) {
            return C9940x.m7368je().m7939mb();
        }
        return 0L;
    }

    /* renamed from: mb */
    private static void m7588mb(C9837ox c9837ox) {
        long m7594hj = m7594hj();
        if (C9940x.m7368je() != null) {
            C9940x.m7368je().m7941h();
        }
        C9932b.m7420mb();
        C9932b.m7416ox();
        if (C10049hj.m7075ko(c9837ox.mo7479m())) {
            C9932b.m7419mb(C9940x.getContext());
        }
        long m7594hj2 = m7594hj();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("quite_clean_size", Long.valueOf(m7594hj2 - m7594hj));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7298mb("clean_quite_finish", jSONObject, c9837ox);
    }

    /* renamed from: hj */
    private static long m7594hj() {
        return C10050jb.m7055mb(0L);
    }

    /* renamed from: mb */
    private void m7587mb(C9837ox c9837ox, JSONObject jSONObject, long j, long j2) {
        try {
            jSONObject.putOpt("available_space", Long.valueOf(j2));
            jSONObject.putOpt("apk_download_need_size", Long.valueOf(j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7298mb("clean_space_no_enough_for_download", jSONObject, c9837ox);
    }

    /* renamed from: ox */
    private void m7576ox(C9837ox c9837ox, JSONObject jSONObject, long j, long j2) {
        c9837ox.m7792je("1");
        C9926ww.m7430mb().m7429mb(c9837ox);
        try {
            jSONObject.putOpt("quite_clean_size", Long.valueOf(j2 - j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7298mb("cleanspace_download_after_quite_clean", jSONObject, c9837ox);
    }
}
