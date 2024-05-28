package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPQueryPayInfoListBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.l */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1550l extends AbstractC14257d<WPQueryPayInfoListBean> {

    /* renamed from: c */
    public final /* synthetic */ int f2577c;

    /* renamed from: d */
    public final /* synthetic */ C1529j f2578d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.l$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1551a extends TypeToken<WPResult<WPQueryPayInfoListBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1550l(C1529j c1529j, AbstractC12375a abstractC12375a, int i) {
        super(abstractC12375a, 1);
        this.f2578d = c1529j;
        this.f2577c = i;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQueryPayInfoListBean> wPResult) {
        try {
            super.mo10a(wPResult);
            if (this.f2578d.m1795b()) {
                ((InterfaceC1470d) this.f2578d.m1798a()).mo6101b(this.f2577c);
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQueryPayInfoListBean> wPResult) {
        try {
            if (this.f2578d.m1795b()) {
                ((InterfaceC1470d) this.f2578d.m1798a()).mo6111a(wPResult.getData(), this.f2577c);
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1551a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        try {
            super.mo11d(str);
            if (this.f2578d.m1795b()) {
                ((InterfaceC1470d) this.f2578d.m1798a()).mo6101b(this.f2577c);
            }
        } catch (Exception unused) {
        }
    }
}
