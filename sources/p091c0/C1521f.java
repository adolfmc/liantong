package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1521f extends AbstractC14257d<WPUnionPayResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C1529j f2553c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1522a extends TypeToken<WPResult<WPUnionPayResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1521f(C1529j c1529j, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f2553c = c1529j;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
        try {
            if (this.f2553c.m1795b()) {
                ((InterfaceC1470d) this.f2553c.m1798a()).mo6097c(wPResult);
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
        try {
            if (this.f2553c.m1795b()) {
                ((InterfaceC1470d) this.f2553c.m1798a()).mo6097c(wPResult);
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1522a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        try {
            m27a();
        } catch (Exception unused) {
        }
    }
}
