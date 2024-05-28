package p394h0;

import com.unicom.pay.qpay.open.p360ui.WPOpenQPayActivity;
import java.util.HashMap;
import p391g0.C11950b;
import p391g0.C11954d;
import p470p0.C13654q;
import p481v.C14232a;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: h0.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12018a implements C14232a.InterfaceC14235c {

    /* renamed from: a */
    public final /* synthetic */ WPOpenQPayActivity f24358a;

    public C12018a(WPOpenQPayActivity wPOpenQPayActivity) {
        this.f24358a = wPOpenQPayActivity;
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: a */
    public final void mo59a() {
        C11954d c11954d = (C11954d) this.f24358a.f24311a;
        c11954d.m1798a().mo2031c();
        c11954d.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27818h, new HashMap(), false, new C11950b(c11954d, c11954d)));
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: b */
    public final void mo58b() {
        WPOpenQPayActivity wPOpenQPayActivity = this.f24358a;
        int i = WPOpenQPayActivity.f20386I;
        P p = wPOpenQPayActivity.f24311a;
        if (p == 0) {
            return;
        }
        C13654q.m172a(p, "", new C12019b(wPOpenQPayActivity));
    }
}
