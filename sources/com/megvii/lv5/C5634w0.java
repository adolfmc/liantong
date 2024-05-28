package com.megvii.lv5;

import com.megvii.lv5.C5668z3;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.w0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5634w0 implements C5668z3.InterfaceC5669a {

    /* renamed from: a */
    public final /* synthetic */ InterfaceC5546r0 f13793a;

    public C5634w0(C5658y0 c5658y0, InterfaceC5546r0 interfaceC5546r0) {
        this.f13793a = interfaceC5546r0;
    }

    @Override // com.megvii.lv5.C5668z3.InterfaceC5669a
    /* renamed from: a */
    public void mo12874a(C5416d4 c5416d4) {
        InterfaceC5546r0 interfaceC5546r0;
        byte[] bytes;
        int i;
        if (c5416d4 == null) {
            C5628v2.m12958b("volleyError", "in null");
            interfaceC5546r0 = this.f13793a;
            if (interfaceC5546r0 == null) {
                return;
            }
            bytes = "error is null".getBytes();
            i = -1000;
        } else if (c5416d4.f12535a != null) {
            C5628v2.m12958b("volleyError", "code: " + c5416d4.f12535a.f13745a + " data: " + new String(c5416d4.f12535a.f13746b));
            InterfaceC5546r0 interfaceC5546r02 = this.f13793a;
            if (interfaceC5546r02 != null) {
                C5622u3 c5622u3 = c5416d4.f12535a;
                interfaceC5546r02.mo12907a(c5622u3.f13745a, c5622u3.f13746b);
                return;
            }
            return;
        } else if (c5416d4 instanceof C5400c4) {
            C5628v2.m12958b("volleyError", "TimeoutError");
            interfaceC5546r0 = this.f13793a;
            if (interfaceC5546r0 == null) {
                return;
            }
            bytes = "TimeoutError".getBytes();
            i = -1001;
        } else if (c5416d4 instanceof C5389b4) {
            C5628v2.m12958b("volleyError", "ServerError");
            interfaceC5546r0 = this.f13793a;
            if (interfaceC5546r0 == null) {
                return;
            }
            bytes = "ServerError".getBytes();
            i = -1002;
        } else if (c5416d4 instanceof C5616t3) {
            C5628v2.m12958b("volleyError", "NetworkError");
            interfaceC5546r0 = this.f13793a;
            if (interfaceC5546r0 == null) {
                return;
            }
            bytes = "NetworkError".getBytes();
            i = -1003;
        } else if (c5416d4 instanceof C5639w3) {
            C5628v2.m12958b("volleyError", "ParseError");
            interfaceC5546r0 = this.f13793a;
            if (interfaceC5546r0 == null) {
                return;
            }
            bytes = "ParseError".getBytes();
            i = -1004;
        } else {
            C5628v2.m12958b("volleyError", "otherError");
            interfaceC5546r0 = this.f13793a;
            if (interfaceC5546r0 == null) {
                return;
            }
            bytes = "otherError".getBytes();
            i = -1005;
        }
        interfaceC5546r0.mo12907a(i, bytes);
    }
}
