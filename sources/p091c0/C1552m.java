package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.m */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1552m extends AbstractC14257d<WPUnionPayResultBean> {

    /* renamed from: c */
    public final /* synthetic */ C1529j f2579c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.m$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1553a extends TypeToken<WPResult<WPUnionPayResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1552m(C1529j c1529j, AbstractC12375a abstractC12375a) {
        super(abstractC12375a);
        this.f2579c = c1529j;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPUnionPayResultBean> wPResult) {
        try {
            if (this.f2579c.m1795b()) {
                ((InterfaceC1470d) this.f2579c.m1798a()).mo6095d(wPResult);
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUnionPayResultBean> wPResult) {
        try {
            if (this.f2579c.m1795b()) {
                ((InterfaceC1470d) this.f2579c.m1798a()).mo6095d(wPResult);
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1553a().getType();
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
