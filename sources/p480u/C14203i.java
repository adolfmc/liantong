package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.i */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14203i extends AbstractC14257d<WPResult> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27709c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.i$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14204a extends TypeToken<WPResult> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14203i(C14219q c14219q, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f27709c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPResult> wPResult) {
        super.mo10a(wPResult);
        if (this.f27709c.m1795b()) {
            this.f27709c.m1798a().mo40f();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPResult> wPResult) {
        if (this.f27709c.m1795b()) {
            this.f27709c.m1798a().mo54J();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14204a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27709c.m1795b()) {
            this.f27709c.m1798a().mo40f();
        }
    }
}
