package p407m0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: m0.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12311c extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ C12308b f24928c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: m0.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12312a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C12311c(C12308b c12308b, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f24928c = c12308b;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPGmKeyBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f24928c.m1795b()) {
            this.f24928c.m1798a().mo1859a();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        if (this.f24928c.m1795b()) {
            WPGmKeyBean data = wPResult.getData();
            String mo1858a = this.f24928c.m1798a().mo1858a(data);
            if (data != null) {
                C12308b c12308b = this.f24928c;
                String keyboardToken = data.getKeyboardToken();
                c12308b.getClass();
                HashMap hashMap = new HashMap();
                hashMap.put("payPwd", mo1858a);
                hashMap.put("keyboardToken", keyboardToken);
                hashMap.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
                c12308b.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27812b, hashMap, false, new C12313d(c12308b, c12308b, mo1858a)));
            }
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C12312a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f24928c.m1795b()) {
            this.f24928c.m1798a().mo1859a();
        }
    }
}
