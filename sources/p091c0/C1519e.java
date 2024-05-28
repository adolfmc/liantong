package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPCommonRespBean;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.normal.order.bean.WPUnionOrderInfoBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1519e extends AbstractC14257d<WPUnionOrderInfoBean> {

    /* renamed from: c */
    public final /* synthetic */ boolean f2550c;

    /* renamed from: d */
    public final /* synthetic */ String f2551d;

    /* renamed from: e */
    public final /* synthetic */ C1529j f2552e;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1520a extends TypeToken<WPResult<WPUnionOrderInfoBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1519e(C1529j c1529j, AbstractC12375a abstractC12375a, boolean z, String str) {
        super(abstractC12375a);
        this.f2552e = c1529j;
        this.f2550c = z;
        this.f2551d = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPUnionOrderInfoBean> wPResult) {
        InterfaceC1470d interfaceC1470d;
        String code;
        WPCommonRespBean commonResp;
        try {
            if ("GATEWAY9001".equals(wPResult.getCode())) {
                if (!this.f2552e.m1795b()) {
                    return;
                }
                interfaceC1470d = (InterfaceC1470d) this.f2552e.m1798a();
                code = wPResult.getCode();
                wPResult.getCommonResp().getTgtid();
                commonResp = wPResult.getCommonResp();
            } else if (!"GATEWAY9002".equals(wPResult.getCode())) {
                if (this.f2552e.m1795b()) {
                    ((InterfaceC1470d) this.f2552e.m1798a()).mo6104a(wPResult.getMsg(), wPResult.getMsgDetail());
                    return;
                }
                return;
            } else if (!this.f2552e.m1795b()) {
                return;
            } else {
                interfaceC1470d = (InterfaceC1470d) this.f2552e.m1798a();
                code = wPResult.getCode();
                wPResult.getCommonResp().getTgtid();
                commonResp = wPResult.getCommonResp();
            }
            interfaceC1470d.mo6102a(code, commonResp.getAuthId(), wPResult.getData());
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPUnionOrderInfoBean> wPResult) {
        try {
            if (this.f2552e.m1795b()) {
                String code = wPResult.getCode();
                wPResult.getCommonResp().getTgtid();
                ((InterfaceC1470d) this.f2552e.m1798a()).mo6102a(code, wPResult.getCommonResp().getAuthId(), wPResult.getData());
                this.f2552e.m22170d();
            }
        } catch (Exception unused) {
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1520a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        try {
            if (this.f2550c) {
                super.mo11d(str);
            } else {
                m27a();
            }
            if (this.f2552e.m1795b()) {
                if ("default".equals(this.f2551d) || "login".equals(this.f2551d)) {
                    ((InterfaceC1470d) this.f2552e.m1798a()).mo6128S();
                }
            }
        } catch (Exception unused) {
        }
    }
}
