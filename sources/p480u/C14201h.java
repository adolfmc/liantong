package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPDzlResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.h */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14201h extends AbstractC14257d<WPDzlResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27708c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.h$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14202a extends TypeToken<WPResult<WPDzlResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14201h(C14219q c14219q, AbstractC12375a abstractC12375a, int i) {
        super(abstractC12375a, i);
        this.f27708c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPDzlResultBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27708c.m1795b()) {
            this.f27708c.m1798a().mo37k();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPDzlResultBean> wPResult) {
        if (this.f27708c.m1795b()) {
            this.f27708c.m1798a().mo50a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14202a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27708c.m1795b()) {
            this.f27708c.m1798a().mo37k();
        }
    }
}
