package p473r;

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

/* renamed from: r.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13708c extends AbstractC14257d<WPGmKeyBean> {

    /* renamed from: c */
    public final /* synthetic */ String f27568c;

    /* renamed from: d */
    public final /* synthetic */ C13700a f27569d;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: r.c$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C13709a extends TypeToken<WPResult<WPGmKeyBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C13708c(C13700a c13700a, AbstractC12375a abstractC12375a, String str) {
        super(abstractC12375a, 1);
        this.f27569d = c13700a;
        this.f27568c = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: a */
    public final void mo10a(WPResult<WPGmKeyBean> wPResult) {
        super.mo10a(wPResult);
        if (this.f27569d.m1795b()) {
            this.f27569d.m1798a().mo155a();
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPGmKeyBean> wPResult) {
        if (this.f27569d.m1795b()) {
            WPGmKeyBean data = wPResult.getData();
            String mo151a = this.f27569d.m1798a().mo151a(data);
            C13700a c13700a = this.f27569d;
            String str = this.f27568c;
            String mo142g = c13700a.m1798a().mo142g();
            String keyboardToken = data.getKeyboardToken();
            HashMap m126a = C13707b.m126a("merNo", str, "cdkey", "");
            m126a.put("signScene", mo142g);
            m126a.put("payPwd", mo151a);
            m126a.put("signTokenId", "");
            m126a.put("verifyCode", "");
            m126a.put("keyboardToken", keyboardToken);
            m126a.put("userClientUid", C10546a.C10576i.f20125a.f20059i);
            c13700a.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27816f, m126a, false, new C13710d(c13700a, c13700a, mo151a)));
        }
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C13709a().getType();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: d */
    public final void mo11d(String str) {
        super.mo11d(str);
        if (this.f27569d.m1795b()) {
            this.f27569d.m1798a().mo155a();
        }
    }
}
