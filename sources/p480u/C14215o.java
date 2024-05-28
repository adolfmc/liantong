package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPFqPayResult;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.o */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14215o extends AbstractC14257d<WPFqPayResult> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27715c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.o$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14216a extends TypeToken<WPResult<WPFqPayResult>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14215o(C14219q c14219q, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27715c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPFqPayResult> wPResult) {
        super.mo10a(wPResult);
        if (this.f27715c.m1795b()) {
            this.f27715c.m1798a().mo37k();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFqPayResult> wPResult) {
        if (this.f27715c.m1795b()) {
            wPResult.getData();
            this.f27715c.m1798a().mo35v();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14216a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27715c.m1795b()) {
            this.f27715c.m1798a().mo37k();
        }
    }
}
