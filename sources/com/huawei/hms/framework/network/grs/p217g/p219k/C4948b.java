package com.huawei.hms.framework.network.grs.p217g.p219k;

import android.os.SystemClock;
import com.huawei.hms.framework.network.grs.p217g.C4937d;
import java.util.concurrent.Future;

/* renamed from: com.huawei.hms.framework.network.grs.g.k.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4948b {

    /* renamed from: a */
    private final Future<C4937d> f11314a;

    /* renamed from: b */
    private final long f11315b = SystemClock.elapsedRealtime();

    public C4948b(Future<C4937d> future) {
        this.f11314a = future;
    }

    /* renamed from: a */
    public Future<C4937d> m14882a() {
        return this.f11314a;
    }

    /* renamed from: b */
    public boolean m14881b() {
        return SystemClock.elapsedRealtime() - this.f11315b <= 300000;
    }
}
