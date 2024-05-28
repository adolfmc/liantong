package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10531R;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQPayResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.t */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14224t extends AbstractC14257d<WPQPayResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14226u f27719c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.t$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14225a extends TypeToken<WPResult<WPQPayResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14224t(C14226u c14226u, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f27719c = c14226u;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQPayResultBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27719c.m1795b()) {
            if ("4066051003".equals(wPResult.getCode())) {
                this.f27719c.m1798a().mo98i();
            } else if ("4066051000".equals(wPResult.getCode())) {
                this.f27719c.m1798a().mo95s();
            } else {
                this.f27719c.m1798a().mo97j();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQPayResultBean> wPResult) {
        if (this.f27719c.m1795b()) {
            WPQPayResultBean data = wPResult.getData();
            if (data == null) {
                this.f27719c.m1798a().mo97j();
            } else if ("002".equals(data.getResultCode())) {
                this.f27719c.m1798a().mo100e();
            } else {
                this.f27719c.m1798a().mo1790i(data.getResultMsg());
                this.f27719c.m1798a().mo97j();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14225a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        if (this.f27719c.m1795b()) {
            m27a();
            this.f27719c.m1798a().mo1791a(C10531R.string.wp_mer_qpay_pay_fail);
            this.f27719c.m1798a().mo97j();
        }
    }
}
