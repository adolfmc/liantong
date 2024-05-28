package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPInstalmentBankListBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1468b;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1515c extends AbstractC14257d<WPInstalmentBankListBean> {

    /* renamed from: c */
    public final /* synthetic */ C1506b f2548c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1516a extends TypeToken<WPResult<WPInstalmentBankListBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1515c(C1506b c1506b, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f2548c = c1506b;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPInstalmentBankListBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f2548c.m1795b()) {
            ((InterfaceC1468b) this.f2548c.m1798a()).mo6148Q();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPInstalmentBankListBean> wPResult) {
        if (this.f2548c.m1795b()) {
            ((InterfaceC1468b) this.f2548c.m1798a()).mo6142a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1516a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f2548c.m1795b()) {
            ((InterfaceC1468b) this.f2548c.m1798a()).mo6148Q();
        }
    }
}
