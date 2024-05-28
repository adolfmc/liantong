package p481v;

import com.unicom.pay.modules.verify.p357ui.WPValidatePayPassActivity;
import com.unicom.pay.widget.WPPassEditText;
import java.util.HashMap;
import p480u.C14188a;
import p480u.C14198f;
import p480u.C14200g;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: v.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14236b implements WPPassEditText.InterfaceC10713b {

    /* renamed from: a */
    public final /* synthetic */ WPValidatePayPassActivity f27733a;

    public C14236b(WPValidatePayPassActivity wPValidatePayPassActivity) {
        this.f27733a = wPValidatePayPassActivity;
    }

    @Override // com.unicom.pay.widget.WPPassEditText.InterfaceC10713b
    /* renamed from: a */
    public final void mo57a(String str) {
        if (str.length() == 6) {
            if (WPValidatePayPassActivity.f20173u == 0) {
                WPValidatePayPassActivity.f20173u = 1;
                if ("FIDO_OPEN".equals(this.f27733a.f20179q)) {
                    C14200g c14200g = (C14200g) this.f27733a.f24311a;
                    c14200g.getClass();
                    c14200g.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27800M, new HashMap(), false, new C14198f(c14200g, c14200g)));
                } else {
                    C14200g c14200g2 = (C14200g) this.f27733a.f24311a;
                    c14200g2.getClass();
                    c14200g2.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27818h, new HashMap(), false, new C14188a(c14200g2, c14200g2)));
                }
                this.f27733a.m1991k("密码页-输入完6位支付密码");
                return;
            }
            return;
        }
        WPValidatePayPassActivity.f20173u = 0;
    }
}
