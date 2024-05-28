package com.p319ss.android.downloadlib.addownload.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.download.api.download.C9813b;
import com.p319ss.android.download.api.download.C9818ox;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.model.u */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9923u {

    /* renamed from: b */
    private final ConcurrentHashMap<Long, DownloadEventConfig> f19131b;

    /* renamed from: h */
    private final ConcurrentHashMap<Long, C9837ox> f19132h;

    /* renamed from: hj */
    private final ConcurrentHashMap<Long, DownloadController> f19133hj;

    /* renamed from: mb */
    private volatile boolean f19134mb;

    /* renamed from: ox */
    private final ConcurrentHashMap<Long, DownloadModel> f19135ox;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.model.u$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9925mb {

        /* renamed from: mb */
        private static C9923u f19137mb = new C9923u();
    }

    /* renamed from: mb */
    public static C9923u m7451mb() {
        return C9925mb.f19137mb;
    }

    private C9923u() {
        this.f19134mb = false;
        this.f19135ox = new ConcurrentHashMap<>();
        this.f19131b = new ConcurrentHashMap<>();
        this.f19133hj = new ConcurrentHashMap<>();
        this.f19132h = new ConcurrentHashMap<>();
    }

    /* renamed from: ox */
    public void m7438ox() {
        C9982hj.m7254mb().m7251mb(new Runnable() { // from class: com.ss.android.downloadlib.addownload.model.u.1
            @Override // java.lang.Runnable
            public void run() {
                if (C9923u.this.f19134mb) {
                    return;
                }
                synchronized (C9923u.class) {
                    if (!C9923u.this.f19134mb) {
                        C9923u.this.f19132h.putAll(C9926ww.m7430mb().m7425ox());
                        C9923u.this.f19134mb = true;
                    }
                }
            }
        }, true);
    }

    /* renamed from: mb */
    public void m7446mb(DownloadModel downloadModel) {
        if (downloadModel != null) {
            this.f19135ox.put(Long.valueOf(downloadModel.getId()), downloadModel);
            if (downloadModel.getDeepLink() != null) {
                downloadModel.getDeepLink().setId(downloadModel.getId());
                downloadModel.getDeepLink().setPackageName(downloadModel.getPackageName());
            }
        }
    }

    /* renamed from: mb */
    public void m7447mb(long j, DownloadEventConfig downloadEventConfig) {
        if (downloadEventConfig != null) {
            this.f19131b.put(Long.valueOf(j), downloadEventConfig);
        }
    }

    /* renamed from: mb */
    public void m7448mb(long j, DownloadController downloadController) {
        if (downloadController != null) {
            this.f19133hj.put(Long.valueOf(j), downloadController);
        }
    }

    /* renamed from: mb */
    public synchronized void m7445mb(C9837ox c9837ox) {
        if (c9837ox == null) {
            return;
        }
        this.f19132h.put(Long.valueOf(c9837ox.mo7474ox()), c9837ox);
        C9926ww.m7430mb().m7429mb(c9837ox);
    }

    /* renamed from: mb */
    public DownloadModel m7449mb(long j) {
        return this.f19135ox.get(Long.valueOf(j));
    }

    /* renamed from: ox */
    public DownloadEventConfig m7437ox(long j) {
        return this.f19131b.get(Long.valueOf(j));
    }

    /* renamed from: b */
    public DownloadController m7454b(long j) {
        return this.f19133hj.get(Long.valueOf(j));
    }

    /* renamed from: hj */
    public C9837ox m7452hj(long j) {
        return this.f19132h.get(Long.valueOf(j));
    }

    /* renamed from: b */
    public ConcurrentHashMap<Long, C9837ox> m7455b() {
        return this.f19132h;
    }

    /* renamed from: mb */
    public C9837ox m7441mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (C9837ox c9837ox : this.f19132h.values()) {
            if (c9837ox != null && str.equals(c9837ox.mo7489h())) {
                return c9837ox;
            }
        }
        return null;
    }

    /* renamed from: ox */
    public C9837ox m7435ox(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (C9837ox c9837ox : this.f19132h.values()) {
            if (c9837ox != null && str.equals(c9837ox.mo7478mb())) {
                return c9837ox;
            }
        }
        return null;
    }

    /* renamed from: mb */
    public C9837ox m7442mb(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return null;
        }
        for (C9837ox c9837ox : this.f19132h.values()) {
            if (c9837ox != null && c9837ox.mo7479m() == downloadInfo.getId()) {
                return c9837ox;
            }
        }
        if (!TextUtils.isEmpty(downloadInfo.getExtra())) {
            try {
                long m7041mb = C10050jb.m7041mb(new JSONObject(downloadInfo.getExtra()), "extra");
                if (m7041mb != 0) {
                    for (C9837ox c9837ox2 : this.f19132h.values()) {
                        if (c9837ox2 != null && c9837ox2.mo7474ox() == m7041mb) {
                            return c9837ox2;
                        }
                    }
                    C9971b.m7285mb().m7284mb("getNativeModelByInfo");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (C9837ox c9837ox3 : this.f19132h.values()) {
            if (c9837ox3 != null && TextUtils.equals(c9837ox3.mo7478mb(), downloadInfo.getUrl())) {
                return c9837ox3;
            }
        }
        return null;
    }

    /* renamed from: mb */
    public C9837ox m7450mb(int i) {
        for (C9837ox c9837ox : this.f19132h.values()) {
            if (c9837ox != null && c9837ox.mo7479m() == i) {
                return c9837ox;
            }
        }
        return null;
    }

    @NonNull
    /* renamed from: h */
    public C9916h m7453h(long j) {
        C9916h c9916h = new C9916h();
        c9916h.f19102mb = j;
        c9916h.f19103ox = m7449mb(j);
        c9916h.f19100b = m7437ox(j);
        if (c9916h.f19100b == null) {
            c9916h.f19100b = new C9813b();
        }
        c9916h.f19101hj = m7454b(j);
        if (c9916h.f19101hj == null) {
            c9916h.f19101hj = new C9818ox();
        }
        return c9916h;
    }

    /* renamed from: u */
    public void m7433u(long j) {
        this.f19135ox.remove(Long.valueOf(j));
        this.f19131b.remove(Long.valueOf(j));
        this.f19133hj.remove(Long.valueOf(j));
    }

    @NonNull
    /* renamed from: mb */
    public Map<Long, C9837ox> m7440mb(String str, String str2) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return hashMap;
        }
        for (C9837ox c9837ox : this.f19132h.values()) {
            if (c9837ox != null && TextUtils.equals(c9837ox.mo7478mb(), str)) {
                c9837ox.m7758ox(str2);
                hashMap.put(Long.valueOf(c9837ox.mo7474ox()), c9837ox);
            }
        }
        return hashMap;
    }

    /* renamed from: ox */
    public void m7434ox(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        for (DownloadModel downloadModel : this.f19135ox.values()) {
            if ((downloadModel instanceof AdDownloadModel) && TextUtils.equals(downloadModel.getDownloadUrl(), str)) {
                ((AdDownloadModel) downloadModel).setPackageName(str2);
            }
        }
    }

    /* renamed from: mb */
    public synchronized void m7439mb(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        for (Long l : list) {
            long longValue = l.longValue();
            arrayList.add(String.valueOf(longValue));
            this.f19132h.remove(Long.valueOf(longValue));
        }
        C9926ww.m7430mb().m7426mb((List<String>) arrayList);
    }
}
