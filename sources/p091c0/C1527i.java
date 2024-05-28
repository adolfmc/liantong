package p091c0;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10531R;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPQOpenResultBean;
import java.lang.reflect.Type;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;

/* renamed from: c0.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1527i extends AbstractC14257d<WPQOpenResultBean> {

    /* renamed from: c */
    public final /* synthetic */ String f2557c;

    /* renamed from: d */
    public final /* synthetic */ C1529j f2558d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.i$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1528a extends TypeToken<WPResult<WPQOpenResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1527i(C1529j c1529j, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f2558d = c1529j;
        this.f2557c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPQOpenResultBean> wPResult) {
        if (this.f2558d.m1795b()) {
            if ("4066041007".equals(wPResult.getCode())) {
                if (TextUtils.isEmpty(this.f2557c)) {
                    return;
                }
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2090c(wPResult.getMsg());
            } else if ("4066041002".equals(wPResult.getCode())) {
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2095b();
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2098a(wPResult.getMsg());
            } else if ("4066049811".equals(wPResult.getCode())) {
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2094b(wPResult.getMsg());
            } else {
                super.mo10a(wPResult);
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2106a();
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPQOpenResultBean> wPResult) {
        InterfaceC1470d interfaceC1470d;
        boolean z;
        if (this.f2558d.m1795b()) {
            WPQOpenResultBean data = wPResult.getData();
            if (data == null) {
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2106a();
                return;
            }
            if (data.isSignSuccess()) {
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2088d();
                interfaceC1470d = (InterfaceC1470d) this.f2558d.m1798a();
                z = true;
            } else if ("001".equals(data.getSignResultCode())) {
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2088d();
                ((InterfaceC1470d) this.f2558d.m1798a()).mo6122a(wPResult.getData());
                return;
            } else {
                ((InterfaceC1470d) this.f2558d.m1798a()).mo2106a();
                interfaceC1470d = (InterfaceC1470d) this.f2558d.m1798a();
                z = false;
            }
            interfaceC1470d.mo6121a(data, z);
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1528a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        if (this.f2558d.m1795b()) {
            m27a();
            ((InterfaceC1470d) this.f2558d.m1798a()).mo1791a(C10531R.string.wp_detail_qpay_open_fail);
            ((InterfaceC1470d) this.f2558d.m1798a()).mo2106a();
        }
    }
}
