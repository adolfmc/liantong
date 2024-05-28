package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.result.bean.WPUserStatusBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.k */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1548k extends AbstractC14257d<WPUserStatusBean> {

    /* renamed from: c */
    public final /* synthetic */ C1529j f2576c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.k$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1549a extends TypeToken<WPResult<WPUserStatusBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1548k(C1529j c1529j, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f2576c = c1529j;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUserStatusBean> wPResult) {
        if (this.f2576c.m1795b()) {
            ((InterfaceC1470d) this.f2576c.m1798a()).mo6123a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1549a().getType();
    }
}
