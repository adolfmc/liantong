package com.megvii.lv5;

import java.io.Serializable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.q4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5544q4 implements InterfaceC5499l4, Serializable, Cloneable {

    /* renamed from: a */
    public final C5491k4 f13199a;

    /* renamed from: b */
    public final int f13200b;

    /* renamed from: c */
    public final String f13201c;

    public C5544q4(C5491k4 c5491k4, int i, String str) {
        this.f13199a = (C5491k4) C5527o2.m13246a(c5491k4, "Version");
        this.f13200b = C5527o2.m13257a(i, "Status code");
        this.f13201c = str;
    }

    @Override // com.megvii.lv5.InterfaceC5499l4
    /* renamed from: a */
    public int mo13171a() {
        return this.f13200b;
    }

    /* renamed from: b */
    public C5491k4 m13170b() {
        return this.f13199a;
    }

    /* renamed from: c */
    public String m13169c() {
        return this.f13201c;
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        C5527o2.m13246a(this, "Status line");
        C5558s4 c5558s4 = new C5558s4(64);
        int length = m13170b().f12843a.length() + 4 + 1 + 3 + 1;
        String m13169c = m13169c();
        if (m13169c != null) {
            length += m13169c.length();
        }
        c5558s4.m13167a(length);
        C5491k4 m13170b = m13170b();
        C5527o2.m13246a(m13170b, "Protocol version");
        c5558s4.m13167a(m13170b.f12843a.length() + 4);
        c5558s4.m13166a(m13170b.f12843a);
        c5558s4.m13168a('/');
        c5558s4.m13166a(Integer.toString(m13170b.f12844b));
        c5558s4.m13168a('.');
        c5558s4.m13166a(Integer.toString(m13170b.f12845c));
        c5558s4.m13168a(' ');
        c5558s4.m13166a(Integer.toString(mo13171a()));
        c5558s4.m13168a(' ');
        if (m13169c != null) {
            c5558s4.m13166a(m13169c);
        }
        return c5558s4.toString();
    }
}
