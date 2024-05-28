package p476s;

import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import com.unicom.pay.modules.verify.bean.WPUnionPayResultBean;
import java.util.HashMap;
import p470p0.C13654q;
import p473r.C13700a;
import p473r.C13708c;
import p481v.C14232a;
import p482w.C14255c;
import p482w.C14262f;

/* renamed from: s.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14113a implements C14232a.InterfaceC14235c {

    /* renamed from: a */
    public final /* synthetic */ WPPayResultActivity f27650a;

    public C14113a(WPPayResultActivity wPPayResultActivity) {
        this.f27650a = wPPayResultActivity;
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: a */
    public final void mo59a() {
        WPResult<WPUnionPayResultBean> wPResult = this.f27650a.f20161y;
        if (wPResult == null || wPResult.getData() == null) {
            return;
        }
        WPPayResultActivity wPPayResultActivity = this.f27650a;
        C13700a c13700a = (C13700a) wPPayResultActivity.f24311a;
        String merNo = wPPayResultActivity.f20161y.getData().getMerNo();
        c13700a.m1798a().mo145c();
        c13700a.m1797a(C14255c.C14256a.f27777a.m29a(C14262f.f27818h, new HashMap(), false, new C13708c(c13700a, c13700a, merNo)));
    }

    @Override // p481v.C14232a.InterfaceC14235c
    /* renamed from: b */
    public final void mo58b() {
        WPPayResultActivity wPPayResultActivity = this.f27650a;
        int i = WPPayResultActivity.f20142F;
        P p = wPPayResultActivity.f24311a;
        if (p == 0) {
            return;
        }
        C13654q.m172a(p, wPPayResultActivity.f20160x, new C14114b(wPPayResultActivity));
    }
}
