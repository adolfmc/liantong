package p484y;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPFqSignResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: y.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14276b extends AbstractC14257d<WPFqSignResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14280d f27838c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: y.b$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14277a extends TypeToken<WPResult<WPFqSignResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14276b(C14280d c14280d, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27838c = c14280d;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPFqSignResultBean> wPResult) {
        if (this.f27838c.m1795b()) {
            if ("406114035".equals(wPResult.getCode())) {
                this.f27838c.m1798a().mo16a(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFqSignResultBean> wPResult) {
        if (this.f27838c.m1795b()) {
            this.f27838c.m1798a().mo17a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14277a().getType();
    }
}
