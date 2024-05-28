package p484y;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPFqPayResult;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: y.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14274a extends AbstractC14257d<WPFqPayResult> {

    /* renamed from: c */
    public final /* synthetic */ C14280d f27837c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: y.a$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14275a extends TypeToken<WPResult<WPFqPayResult>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14274a(C14280d c14280d, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27837c = c14280d;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPFqPayResult> wPResult) {
        super.mo10a(wPResult);
        if (this.f27837c.m1795b()) {
            this.f27837c.m1798a().mo14n();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFqPayResult> wPResult) {
        if (this.f27837c.m1795b()) {
            wPResult.getData();
            this.f27837c.m1798a().mo18N();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14275a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27837c.m1795b()) {
            this.f27837c.m1798a().mo14n();
        }
    }
}
