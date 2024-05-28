package p473r;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.result.bean.WPUserStatusBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: r.e */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13712e extends AbstractC14257d<WPUserStatusBean> {

    /* renamed from: c */
    public final /* synthetic */ C13700a f27572c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: r.e$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C13713a extends TypeToken<WPResult<WPUserStatusBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C13712e(C13700a c13700a, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27572c = c13700a;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUserStatusBean> wPResult) {
        if (this.f27572c.m1795b()) {
            this.f27572c.m1798a().mo152a(wPResult.getData());
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C13713a().getType();
    }
}
