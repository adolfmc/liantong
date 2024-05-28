package p480u;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: u.j */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14205j extends AbstractC14257d<WPResult> {

    /* renamed from: c */
    public final /* synthetic */ C14219q f27710c;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.j$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14206a extends TypeToken<WPResult> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14205j(C14219q c14219q, AbstractC12375a abstractC12375a, int i) {
        super(abstractC12375a, i);
        this.f27710c = c14219q;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPResult> wPResult) {
        super.mo10a(wPResult);
        if (this.f27710c.m1795b()) {
            this.f27710c.m1798a().mo37k();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPResult> wPResult) {
        if (this.f27710c.m1795b()) {
            this.f27710c.m1798a().mo34z();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14206a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27710c.m1795b()) {
            this.f27710c.m1798a().mo37k();
        }
    }
}
