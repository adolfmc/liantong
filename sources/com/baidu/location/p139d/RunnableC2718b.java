package com.baidu.location.p139d;

import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2718b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ WeakReference f5667a;

    /* renamed from: b */
    final /* synthetic */ ServiceC2716a f5668b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2718b(ServiceC2716a serviceC2716a, WeakReference weakReference) {
        this.f5668b = serviceC2716a;
        this.f5667a = weakReference;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        ServiceC2716a serviceC2716a = (ServiceC2716a) this.f5667a.get();
        if (serviceC2716a != null) {
            i = serviceC2716a.f5664h;
            if (i == 3) {
                Log.d("baidu_location_service", "baidu location service force stopped ...");
                serviceC2716a.f5665i = false;
                serviceC2716a.m19103b();
            }
        }
    }
}
