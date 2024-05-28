package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.k */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14207k extends AbstractC14257d<WPUnionPayResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27711c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.k$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14208a extends TypeToken<WPResult<WPUnionPayResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14207k(C14219q c14219q, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27711c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27711c.m1795b()) {
            if ("406114017".equals(wPResult.getCode())) {
                this.f27711c.m1798a().mo40f();
            } else if ("406114027".equals(wPResult.getCode()) || "406114026".equals(wPResult.getCode()) || "406114056".equals(wPResult.getCode())) {
                this.f27711c.m1798a().mo41e(null);
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
        if (this.f27711c.m1795b()) {
            this.f27711c.m1798a().mo41e(wPResult);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14208a().getType();
    }
}
