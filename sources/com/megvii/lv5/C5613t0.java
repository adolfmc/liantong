package com.megvii.lv5;

import com.megvii.lv5.C5668z3;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.t0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5613t0 implements C5668z3.InterfaceC5669a {

    /* renamed from: a */
    public final /* synthetic */ InterfaceC5546r0 f13731a;

    public C5613t0(C5658y0 c5658y0, InterfaceC5546r0 interfaceC5546r0) {
        this.f13731a = interfaceC5546r0;
    }

    @Override // com.megvii.lv5.C5668z3.InterfaceC5669a
    /* renamed from: a */
    public void mo12874a(C5416d4 c5416d4) {
        InterfaceC5546r0 interfaceC5546r0;
        String str;
        if (c5416d4 == null) {
            C5628v2.m12958b("volleyError", "in null");
            interfaceC5546r0 = this.f13731a;
            if (interfaceC5546r0 == null) {
                return;
            }
            str = "error is null";
        } else if (c5416d4.f12535a != null) {
            C5628v2.m12958b("volleyError", "code: " + c5416d4.f12535a.f13745a + " data: " + new String(c5416d4.f12535a.f13746b));
            InterfaceC5546r0 interfaceC5546r02 = this.f13731a;
            if (interfaceC5546r02 != null) {
                C5622u3 c5622u3 = c5416d4.f12535a;
                interfaceC5546r02.mo12907a(c5622u3.f13745a, c5622u3.f13746b);
                return;
            }
            return;
        } else if (c5416d4 instanceof C5400c4) {
            C5628v2.m12958b("volleyError", "TimeoutError");
            interfaceC5546r0 = this.f13731a;
            if (interfaceC5546r0 == null) {
                return;
            }
            str = "TimeoutError";
        } else if (c5416d4 instanceof C5389b4) {
            C5628v2.m12958b("volleyError", "ServerError");
            interfaceC5546r0 = this.f13731a;
            if (interfaceC5546r0 == null) {
                return;
            }
            str = "ServerError";
        } else if (c5416d4 instanceof C5616t3) {
            C5628v2.m12958b("volleyError", "NetworkError");
            interfaceC5546r0 = this.f13731a;
            if (interfaceC5546r0 == null) {
                return;
            }
            str = "NetworkError";
        } else if (c5416d4 instanceof C5639w3) {
            C5628v2.m12958b("volleyError", "ParseError");
            interfaceC5546r0 = this.f13731a;
            if (interfaceC5546r0 == null) {
                return;
            }
            str = "ParseError";
        } else {
            C5628v2.m12958b("volleyError", "otherError");
            interfaceC5546r0 = this.f13731a;
            if (interfaceC5546r0 == null) {
                return;
            }
            str = "otherError";
        }
        interfaceC5546r0.mo12907a(-1, str.getBytes());
    }
}
