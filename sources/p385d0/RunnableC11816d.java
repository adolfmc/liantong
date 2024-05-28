package p385d0;

import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.p359ui.WPInstalmentActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: d0.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class RunnableC11816d implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ WPResult f24067a;

    /* renamed from: b */
    public final /* synthetic */ WPInstalmentActivity f24068b;

    public RunnableC11816d(WPInstalmentActivity wPInstalmentActivity, WPResult wPResult) {
        this.f24068b = wPInstalmentActivity;
        this.f24067a = wPResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f24068b.m2086f(this.f24067a);
    }
}
