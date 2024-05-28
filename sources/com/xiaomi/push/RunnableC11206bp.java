package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11213bw;
import java.lang.ref.WeakReference;

/* renamed from: com.xiaomi.push.bp */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RunnableC11206bp implements Runnable {

    /* renamed from: a */
    private String f21632a;

    /* renamed from: a */
    private WeakReference<Context> f21633a;

    public RunnableC11206bp(String str, WeakReference<Context> weakReference) {
        this.f21632a = str;
        this.f21633a = weakReference;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        WeakReference<Context> weakReference = this.f21633a;
        if (weakReference == null || (context = weakReference.get()) == null) {
            return;
        }
        if (C11226ca.m4609a(this.f21632a) > C11205bo.f21630a) {
            C11209bs m4688a = C11209bs.m4688a(this.f21632a);
            C11208br m4689a = C11208br.m4689a(this.f21632a);
            m4688a.m4670a(m4689a);
            m4689a.m4670a(C11207bq.m4690a(context, this.f21632a, 1000));
            C11213bw.m4683a(context).m4682a((C11213bw.AbstractRunnableC11215a) m4688a);
            return;
        }
        AbstractC11049b.m5274b("=====> do not need clean db");
    }
}
