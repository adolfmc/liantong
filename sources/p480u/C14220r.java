package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQuickSmsBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.r */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14220r extends AbstractC14257d<WPQuickSmsBean> {

    /* renamed from: c */
    public final /* synthetic */ C14226u f27717c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.r$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14221a extends TypeToken<WPResult<WPQuickSmsBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14220r(C14226u c14226u, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27717c = c14226u;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQuickSmsBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27717c.m1795b()) {
            this.f27717c.m1798a().mo96k();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQuickSmsBean> wPResult) {
        if (this.f27717c.m1795b()) {
            this.f27717c.m1798a().mo102a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14221a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27717c.m1795b()) {
            this.f27717c.m1798a().mo96k();
        }
    }
}
