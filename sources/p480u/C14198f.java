package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.f */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14198f extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ C14200g f27707c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.f$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14199a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14198f(C14200g c14200g, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27707c = c14200g;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        if (this.f27707c.m1795b()) {
            this.f27707c.m1798a().mo107b(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14199a().getType();
    }
}
