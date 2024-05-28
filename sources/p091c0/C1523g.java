package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPNoticeListInfoBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1523g extends AbstractC14257d<WPNoticeListInfoBean> {

    /* renamed from: c */
    public final /* synthetic */ C1529j f2554c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.g$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1524a extends TypeToken<WPResult<WPNoticeListInfoBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1523g(C1529j c1529j, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f2554c = c1529j;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPNoticeListInfoBean> wPResult) {
        try {
            super.mo10a(wPResult);
            if (this.f2554c.m1795b()) {
                ((InterfaceC1470d) this.f2554c.m1798a()).mo6129K();
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPNoticeListInfoBean> wPResult) {
        try {
            if (this.f2554c.m1795b()) {
                ((InterfaceC1470d) this.f2554c.m1798a()).mo6117a(wPResult.getData());
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1524a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        try {
            super.mo11d(str);
            if (this.f2554c.m1795b()) {
                ((InterfaceC1470d) this.f2554c.m1798a()).mo6129K();
            }
        } catch (Exception unused) {
        }
    }
}
