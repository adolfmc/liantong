package p391g0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p411o.AbstractC12375a;
import p473r.C13707b;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: g0.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11950b extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ C11954d f24301c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: g0.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11951a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C11950b(C11954d c11954d, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f24301c = c11954d;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPGmKeyBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f24301c.m1795b()) {
            this.f24301c.m1798a().mo2039a();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        WPGmKeyBean data;
        if (!this.f24301c.m1795b() || (data = wPResult.getData()) == null) {
            return;
        }
        String mo2038a = this.f24301c.m1798a().mo2038a(data);
        C11954d c11954d = this.f24301c;
        String mo2028g = c11954d.m1798a().mo2028g();
        String keyboardToken = data.getKeyboardToken();
        HashMap m126a = C13707b.m126a("cdkey", "", "signScene", mo2028g);
        m126a.put("payPwd", mo2038a);
        m126a.put("signTokenId", "");
        m126a.put("verifyCode", "");
        m126a.put("keyboardToken", keyboardToken);
        m126a.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
        c11954d.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27816f, m126a, false, new C11952c(c11954d, c11954d, mo2038a)));
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C11951a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f24301c.m1795b()) {
            this.f24301c.m1798a().mo2039a();
        }
    }
}
