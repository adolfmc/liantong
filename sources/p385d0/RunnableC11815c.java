package p385d0;

import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import p091c0.AbstractC1556o;
import p385d0.AbstractActivityC11796a;

/* renamed from: d0.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC11815c implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ FidoReInfo f24065a;

    /* renamed from: b */
    public final /* synthetic */ AbstractActivityC11796a.C11807j f24066b;

    public RunnableC11815c(AbstractActivityC11796a.C11807j c11807j, FidoReInfo fidoReInfo) {
        this.f24066b = c11807j;
        this.f24065a = fidoReInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AbstractActivityC11796a abstractActivityC11796a;
        WPQPayUserInfoBean wPQPayUserInfoBean;
        WPPayBeforeBean wPPayBeforeBean;
        String str;
        boolean z;
        AbstractActivityC11796a.this.mo1793I();
        if (this.f24065a.getStatus() == FidoStatus.SUCCESS) {
            String mo6076y = AbstractActivityC11796a.this.mo6076y();
            AbstractActivityC11796a.C11807j c11807j = this.f24066b;
            AbstractActivityC11796a abstractActivityC11796a2 = AbstractActivityC11796a.this;
            ((AbstractC1556o) abstractActivityC11796a2.f24311a).m22168a(mo6076y, c11807j.f24046a, abstractActivityC11796a2.mo6127V(), this.f24065a.getMfacResponse(), "", AbstractActivityC11796a.this.mo6077x());
            return;
        }
        if (this.f24065a.getStatus() == FidoStatus.FAILED || this.f24065a.getStatus() == FidoStatus.NO_MATCH || this.f24065a.getStatus() == FidoStatus.CANCELED) {
            AbstractActivityC11796a.C11807j c11807j2 = this.f24066b;
            abstractActivityC11796a = AbstractActivityC11796a.this;
            wPQPayUserInfoBean = c11807j2.f24047b;
            wPPayBeforeBean = c11807j2.f24046a;
            str = c11807j2.f24048c;
            z = false;
        } else {
            AbstractActivityC11796a.C11807j c11807j3 = this.f24066b;
            abstractActivityC11796a = AbstractActivityC11796a.this;
            wPQPayUserInfoBean = c11807j3.f24047b;
            wPPayBeforeBean = c11807j3.f24046a;
            str = c11807j3.f24048c;
            z = true;
        }
        abstractActivityC11796a.m2099a(wPQPayUserInfoBean, wPPayBeforeBean, str, z);
    }
}
