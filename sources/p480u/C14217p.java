package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPFqSignResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.p */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14217p extends AbstractC14257d<WPFqSignResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27716c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.p$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14218a extends TypeToken<WPResult<WPFqSignResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14217p(C14219q c14219q, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27716c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPFqSignResultBean> wPResult) {
        if (this.f27716c.m1795b()) {
            this.f27716c.m1798a().mo40f();
            super.mo10a(wPResult);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFqSignResultBean> wPResult) {
        if (this.f27716c.m1795b()) {
            wPResult.getData();
            this.f27716c.m1798a().mo55G();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14218a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        if (this.f27716c.m1795b()) {
            this.f27716c.m1798a().mo40f();
            super.mo11d(str);
        }
    }
}
