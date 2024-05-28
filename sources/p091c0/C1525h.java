package p091c0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p089b0.InterfaceC1470d;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: c0.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1525h extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ String f2555c;

    /* renamed from: d */
    public final /* synthetic */ C1529j f2556d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: c0.h$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C1526a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1525h(C1529j c1529j, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f2556d = c1529j;
        this.f2555c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPGmKeyBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f2556d.m1795b()) {
            ((InterfaceC1470d) this.f2556d.m1798a()).mo2106a();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        if (this.f2556d.m1795b()) {
            WPGmKeyBean data = wPResult.getData();
            String mo2103a = ((InterfaceC1470d) this.f2556d.m1798a()).mo2103a(data);
            C1529j c1529j = this.f2556d;
            String str = this.f2555c;
            String keyboardToken = data.getKeyboardToken();
            c1529j.getClass();
            HashMap hashMap = new HashMap();
            hashMap.put("merNo", str);
            hashMap.put("cdkey", "");
            hashMap.put("signScene", "PAY_DETAIL");
            hashMap.put("payPwd", mo2103a);
            hashMap.put("signTokenId", "");
            hashMap.put("verifyCode", "");
            hashMap.put("keyboardToken", keyboardToken);
            hashMap.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            c1529j.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27816f, hashMap, false, new C1527i(c1529j, c1529j, mo2103a)));
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C1526a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f2556d.m1795b()) {
            ((InterfaceC1470d) this.f2556d.m1798a()).mo2106a();
        }
    }
}
