package p407m0;

import com.google.gson.reflect.TypeToken;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.verify.bean.WPGmKeyBean;
import java.lang.reflect.Type;
import java.util.HashMap;
import p411o.AbstractC12375a;
import p482w.AbstractC14257d;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: m0.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12315e extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ C12319g f24931c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: m0.e$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12316a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C12315e(C12319g c12319g, AbstractC12375a abstractC12375a) {
        super(abstractC12375a, 1);
        this.f24931c = c12319g;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPGmKeyBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f24931c.m1795b()) {
            this.f24931c.m1798a().mo1847a();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        WPGmKeyBean data;
        if (!this.f24931c.m1795b() || (data = wPResult.getData()) == null) {
            return;
        }
        String mo1846a = this.f24931c.m1798a().mo1846a(data);
        String mo1848U = this.f24931c.m1798a().mo1848U();
        C12319g c12319g = this.f24931c;
        String keyboardToken = data.getKeyboardToken();
        c12319g.getClass();
        HashMap hashMap = new HashMap();
        hashMap.put("payLimit", mo1848U);
        hashMap.put("payPwd", mo1846a);
        hashMap.put("keyboardToken", keyboardToken);
        C14255c.C14256a.f27777a.m29a(C14262f.f27813c, hashMap, false, new C12317f(c12319g, c12319g, mo1846a));
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C12316a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f24931c.m1795b()) {
            this.f24931c.m1798a().mo1847a();
        }
    }
}
