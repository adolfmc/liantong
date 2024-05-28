package p385d0;

import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;

/* renamed from: d0.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC11820g implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ WPResult f24079a;

    /* renamed from: b */
    public final /* synthetic */ WPOrderActivity f24080b;

    public RunnableC11820g(WPOrderActivity wPOrderActivity, WPResult wPResult) {
        this.f24080b = wPOrderActivity;
        this.f24079a = wPResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WPOrderActivity wPOrderActivity = this.f24080b;
        WPResult<WPUnionPayResultBean> wPResult = this.f24079a;
        int i = WPOrderActivity.f20256N0;
        wPOrderActivity.m2010Z();
        wPOrderActivity.m2086f(wPResult);
    }
}
