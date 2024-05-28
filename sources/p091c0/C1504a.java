package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPUnionFqInfoBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1468b;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1504a extends AbstractC14257d<WPUnionFqInfoBean> {

    /* renamed from: c */
    public final /* synthetic */ C1506b f2537c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1505a extends TypeToken<WPResult<WPUnionFqInfoBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1504a(C1506b c1506b, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f2537c = c1506b;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPUnionFqInfoBean> wPResult) {
        ((InterfaceC1468b) this.f2537c.m1798a()).mo6138a(wPResult.getMsg(), wPResult.getMsgDetail());
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUnionFqInfoBean> wPResult) {
        if (this.f2537c.m1795b()) {
            ((InterfaceC1468b) this.f2537c.m1798a()).mo6140a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1505a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
    }
}
