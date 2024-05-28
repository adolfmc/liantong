package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQuickSmsBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.l */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14209l extends AbstractC14257d<WPQuickSmsBean> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27712c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.l$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14210a extends TypeToken<WPResult<WPQuickSmsBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14209l(C14219q c14219q, AbstractC12375a abstractC12375a, int i) {
        super(abstractC12375a, i);
        this.f27712c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQuickSmsBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27712c.m1795b()) {
            this.f27712c.m1798a().mo37k();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQuickSmsBean> wPResult) {
        if (this.f27712c.m1795b()) {
            this.f27712c.m1798a().mo49a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14210a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27712c.m1795b()) {
            this.f27712c.m1798a().mo37k();
        }
    }
}
