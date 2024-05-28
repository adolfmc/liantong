package com.p319ss.android.downloadlib.addownload.compliance;

import com.p319ss.android.downloadlib.addownload.model.C9922ox;
import com.p319ss.android.socialbase.downloader.utils.LruCache;

/* renamed from: com.ss.android.downloadlib.addownload.compliance.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9857b extends LruCache<Long, C9922ox> {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.compliance.b$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C9859mb {

        /* renamed from: mb */
        private static C9857b f18942mb = new C9857b();
    }

    /* renamed from: mb */
    public static C9857b m7677mb() {
        return C9859mb.f18942mb;
    }

    private C9857b() {
        super(16, 16);
    }

    /* renamed from: mb */
    public void m7674mb(C9922ox c9922ox) {
        if (c9922ox == null) {
            return;
        }
        put(Long.valueOf(c9922ox.m7457mb()), c9922ox);
    }

    /* renamed from: mb */
    public C9922ox m7675mb(long j, long j2) {
        return (C9922ox) get(get(Long.valueOf(j)) != null ? Long.valueOf(j) : Long.valueOf(j2));
    }

    /* renamed from: mb */
    public C9922ox m7676mb(long j) {
        return (C9922ox) get(Long.valueOf(j));
    }
}
