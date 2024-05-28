package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10531R;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQPayResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.n */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14213n extends AbstractC14257d<WPQPayResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27714c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.n$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14214a extends TypeToken<WPResult<WPQPayResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14213n(C14219q c14219q, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27714c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQPayResultBean> wPResult) {
        super.mo10a(wPResult);
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQPayResultBean> wPResult) {
        WPQPayResultBean data;
        if (!this.f27714c.m1795b() || (data = wPResult.getData()) == null) {
            return;
        }
        if ("002".equals(data.getResultCode())) {
            this.f27714c.m1798a().mo42e();
        } else {
            this.f27714c.m1798a().mo1790i(data.getResultMsg());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14214a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        if (this.f27714c.m1795b()) {
            m27a();
            this.f27714c.m1798a().mo1791a(C10531R.string.wp_detail_qpay_pay_fail);
        }
    }
}
