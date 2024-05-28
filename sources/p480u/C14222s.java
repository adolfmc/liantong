package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.s */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14222s extends AbstractC14257d<WPQOpenResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C14226u f27718c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.s$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14223a extends TypeToken<WPResult<WPQOpenResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14222s(C14226u c14226u, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27718c = c14226u;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQOpenResultBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27718c.m1795b() && "4066041000".equals(wPResult.getCode())) {
            this.f27718c.m1798a().mo99f();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQOpenResultBean> wPResult) {
        WPQOpenResultBean data;
        if (!this.f27718c.m1795b() || (data = wPResult.getData()) == null) {
            return;
        }
        this.f27718c.m1798a().mo101b(data);
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14223a().getType();
    }
}
