package p409n0;

import com.unicom.pay.qpay.setting.p361ui.WPSettingLimitActivity;
import java.util.HashMap;
import p407m0.C12315e;
import p407m0.C12319g;
import p470p0.C13654q;
import p481v.C14232a;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: n0.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12364d implements C14232a.InterfaceC14235c {

    /* renamed from: a */
    public final /* synthetic */ WPSettingLimitActivity f25023a;

    public C12364d(WPSettingLimitActivity wPSettingLimitActivity) {
        this.f25023a = wPSettingLimitActivity;
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: a */
    public final void mo59a() {
        C12319g c12319g = (C12319g) this.f25023a.f24311a;
        c12319g.m1798a().mo1842c();
        c12319g.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27818h, new HashMap(), false, new C12315e(c12319g, c12319g)));
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: b */
    public final void mo58b() {
        WPSettingLimitActivity wPSettingLimitActivity = this.f25023a;
        int i = WPSettingLimitActivity.f20437q;
        P p = wPSettingLimitActivity.f24311a;
        if (p == 0) {
            return;
        }
        C13654q.m172a(p, "", new C12365e(wPSettingLimitActivity));
    }
}
