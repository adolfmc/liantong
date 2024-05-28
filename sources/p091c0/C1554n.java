package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import com.unicom.pay.normal.order.bean.WPPayBeforeBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1472f;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.n */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1554n extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ String f2580c;

    /* renamed from: d */
    public final /* synthetic */ WPPayBeforeBean f2581d;

    /* renamed from: e */
    public final /* synthetic */ String f2582e;

    /* renamed from: f */
    public final /* synthetic */ String f2583f;

    /* renamed from: g */
    public final /* synthetic */ AbstractC1556o f2584g;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.n$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1555a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1554n(AbstractC1556o abstractC1556o, AbstractC12375a abstractC12375a, String str, WPPayBeforeBean wPPayBeforeBean, String str2, String str3) {
        super(abstractC12375a, 1);
        this.f2584g = abstractC1556o;
        this.f2580c = str;
        this.f2581d = wPPayBeforeBean;
        this.f2582e = str2;
        this.f2583f = str3;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPGmKeyBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f2584g.m1795b()) {
            ((InterfaceC1472f) this.f2584g.m1798a()).mo2106a();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        if (this.f2584g.m1795b()) {
            this.f2584g.m22168a(this.f2580c, this.f2581d, this.f2582e, "", ((InterfaceC1472f) this.f2584g.m1798a()).mo2103a(wPResult.getData()), this.f2583f);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1555a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f2584g.m1795b()) {
            ((InterfaceC1472f) this.f2584g.m1798a()).mo2106a();
        }
    }
}
