package com.p319ss.android.downloadlib.addownload.model;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.addownload.C9940x;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.model.ww */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9926ww {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.model.ww$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9929mb {

        /* renamed from: mb */
        private static C9926ww f19142mb = new C9926ww();
    }

    /* renamed from: mb */
    public static C9926ww m7430mb() {
        return C9929mb.f19142mb;
    }

    private C9926ww() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public SharedPreferences m7431b() {
        return C9940x.getContext().getSharedPreferences("sp_ad_download_event", 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: ox */
    public ConcurrentHashMap<Long, C9837ox> m7425ox() {
        ConcurrentHashMap<Long, C9837ox> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String, ?> all = m7431b().getAll();
        if (all == null) {
            return concurrentHashMap;
        }
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            if (entry.getValue() != null) {
                try {
                    long longValue = Long.valueOf(entry.getKey()).longValue();
                    C9837ox m7757ox = C9837ox.m7757ox(new JSONObject(String.valueOf(entry.getValue())));
                    if (longValue > 0 && m7757ox != null) {
                        concurrentHashMap.put(Long.valueOf(longValue), m7757ox);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return concurrentHashMap;
    }

    /* renamed from: mb */
    public void m7429mb(C9837ox c9837ox) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(c9837ox);
        m7427mb((Collection<C9837ox>) arrayList);
    }

    /* renamed from: mb */
    public synchronized void m7427mb(final Collection<C9837ox> collection) {
        if (collection != null) {
            if (!collection.isEmpty()) {
                C9982hj.m7254mb().m7251mb(new Runnable() { // from class: com.ss.android.downloadlib.addownload.model.ww.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SharedPreferences.Editor edit = C9926ww.this.m7431b().edit();
                        for (C9837ox c9837ox : collection) {
                            if (c9837ox != null && c9837ox.mo7474ox() != 0) {
                                String valueOf = String.valueOf(c9837ox.mo7474ox());
                                JSONObject m7786km = c9837ox.m7786km();
                                edit.putString(valueOf, !(m7786km instanceof JSONObject) ? m7786km.toString() : NBSJSONObjectInstrumentation.toString(m7786km));
                            }
                        }
                        edit.apply();
                    }
                }, true);
            }
        }
    }

    /* renamed from: mb */
    public void m7426mb(final List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C9982hj.m7254mb().m7251mb(new Runnable() { // from class: com.ss.android.downloadlib.addownload.model.ww.2
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences.Editor edit = C9926ww.this.m7431b().edit();
                for (String str : list) {
                    edit.remove(str);
                }
                edit.apply();
            }
        }, true);
    }
}
