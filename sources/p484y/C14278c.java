package p484y;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: y.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14278c extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ C14280d f27839c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: y.c$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14279a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14278c(C14280d c14280d, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f27839c = c14280d;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        if (this.f27839c.m1795b()) {
            this.f27839c.m1798a().mo15b(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14279a().getType();
    }
}
