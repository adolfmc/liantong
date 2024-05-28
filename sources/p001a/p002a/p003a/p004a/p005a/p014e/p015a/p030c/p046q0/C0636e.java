package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p046q0;

import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0156h1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.C0268a0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2.InterfaceC0291t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0445y0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.C0485h;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.C0487j;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.C0489l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.InterfaceC0493p;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0585o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0589q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0592r0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.q0.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0636e {
    /* renamed from: a */
    public static C0445y0 m22664a(C0557a c0557a) {
        C0485h c0485h;
        if (c0557a instanceof C0592r0) {
            C0592r0 c0592r0 = (C0592r0) c0557a;
            return new C0445y0(new C0377b(InterfaceC0291t.f729o, (InterfaceC0136d) C0156h1.f189v3), new C0268a0(c0592r0.m22766c(), c0592r0.m22767b()));
        } else if (c0557a instanceof C0585o) {
            return new C0445y0(new C0377b(InterfaceC0493p.f1572e3), new C0166k(((C0585o) c0557a).m22783c()));
        } else {
            if (c0557a instanceof C0596u) {
                C0596u c0596u = (C0596u) c0557a;
                C0589q m22765b = c0596u.m22765b();
                if (m22765b == null) {
                    c0485h = new C0485h(C0156h1.f189v3);
                } else {
                    c0485h = new C0485h(new C0487j(m22765b.m22775a(), m22765b.m22774b(), m22765b.m22772d(), m22765b.m22773c(), m22765b.m22771e()));
                }
                return new C0445y0(new C0377b(InterfaceC0493p.f1598u2, (InterfaceC0136d) c0485h), ((AbstractC0182o) new C0489l(c0596u.m22757c()).mo23015d()).mo24088m());
            }
            throw new IOException("key parameters not recognised.");
        }
    }
}
