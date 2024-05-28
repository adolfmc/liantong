package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p046q0;

import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0156h1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.C0293v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.C0296y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.InterfaceC0291t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0428s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.InterfaceC0493p;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0581m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0583n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0592r0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0594s0;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.q0.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0634c {
    /* renamed from: a */
    public static C0293v m22668a(C0557a c0557a) {
        if (c0557a instanceof C0592r0) {
            C0594s0 c0594s0 = (C0594s0) c0557a;
            return new C0293v(new C0377b(InterfaceC0291t.f729o, (InterfaceC0136d) C0156h1.f189v3), new C0296y(c0594s0.m22766c(), c0594s0.m22761g(), c0594s0.m22767b(), c0594s0.m22762f(), c0594s0.m22760h(), c0594s0.m22764d(), c0594s0.m22763e(), c0594s0.m22759i()));
        } else if (c0557a instanceof C0583n) {
            C0583n c0583n = (C0583n) c0557a;
            C0581m m22802b = c0583n.m22802b();
            return new C0293v(new C0377b(InterfaceC0493p.f1572e3, (InterfaceC0136d) new C0428s(m22802b.m22791b(), m22802b.m22790c(), m22802b.m22792a())), new C0166k(c0583n.m22786c()));
        } else {
            throw new IOException("key parameters not recognised.");
        }
    }
}
