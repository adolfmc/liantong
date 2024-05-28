package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1468b;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1517d extends AbstractC14257d<WPUnionPayResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C1506b f2549c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1518a extends TypeToken<WPResult<WPUnionPayResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1517d(C1506b c1506b, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f2549c = c1506b;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
        if (this.f2549c.m1795b()) {
            ((InterfaceC1468b) this.f2549c.m1798a()).mo6137c(wPResult);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
        if (this.f2549c.m1795b()) {
            ((InterfaceC1468b) this.f2549c.m1798a()).mo6137c(wPResult);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1518a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        m27a();
    }
}
