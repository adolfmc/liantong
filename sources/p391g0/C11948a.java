package p391g0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.qpay.open.bean.WPAgreementBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: g0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11948a extends AbstractC14257d<WPAgreementBean> {

    /* renamed from: c */
    public final /* synthetic */ C11954d f24300c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: g0.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11949a extends TypeToken<WPResult<WPAgreementBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C11948a(C11954d c11954d, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f24300c = c11954d;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPAgreementBean> wPResult) {
        if (!this.f24300c.m1795b() || wPResult.getData() == null) {
            return;
        }
        this.f24300c.m1798a().mo2035a(wPResult.getData());
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C11949a().getType();
    }
}
