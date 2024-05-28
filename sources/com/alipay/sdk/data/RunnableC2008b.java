package com.alipay.sdk.data;

import android.content.Context;
import com.alipay.sdk.packet.C2021b;
import com.alipay.sdk.packet.impl.C2026b;
import com.alipay.sdk.util.C2040c;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.data.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2008b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f3742a;

    /* renamed from: b */
    final /* synthetic */ C2006a f3743b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2008b(C2006a c2006a, Context context) {
        this.f3743b = c2006a;
        this.f3742a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            C2021b a = new C2026b().m20802a(this.f3742a);
            if (a != null) {
                this.f3743b.m20873b(a.m20814b());
                this.f3743b.m20866i();
            }
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }
}
