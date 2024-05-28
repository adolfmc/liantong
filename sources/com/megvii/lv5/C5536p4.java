package com.megvii.lv5;

import java.util.List;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.p4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5536p4 {

    /* renamed from: a */
    public C5550r4 f13174a = new C5550r4();

    /* renamed from: b */
    public InterfaceC5499l4 f13175b;

    /* renamed from: c */
    public C5491k4 f13176c;

    /* renamed from: d */
    public int f13177d;

    /* renamed from: e */
    public String f13178e;

    /* renamed from: f */
    public InterfaceC5478i4 f13179f;

    public C5536p4(InterfaceC5499l4 interfaceC5499l4) {
        this.f13175b = (InterfaceC5499l4) C5527o2.m13246a(interfaceC5499l4, "Status line");
        C5544q4 c5544q4 = (C5544q4) interfaceC5499l4;
        this.f13176c = c5544q4.m13170b();
        this.f13177d = c5544q4.mo13171a();
        this.f13178e = c5544q4.m13169c();
    }

    /* renamed from: a */
    public InterfaceC5466h4[] m13210a() {
        List<InterfaceC5466h4> list = this.f13174a.f13247a;
        return (InterfaceC5466h4[]) list.toArray(new InterfaceC5466h4[list.size()]);
    }

    /* renamed from: b */
    public InterfaceC5499l4 m13209b() {
        if (this.f13175b == null) {
            C5491k4 c5491k4 = this.f13176c;
            if (c5491k4 == null) {
                c5491k4 = C5485j4.f12830d;
            }
            int i = this.f13177d;
            String str = this.f13178e;
            if (str == null) {
                str = null;
            }
            this.f13175b = new C5544q4(c5491k4, i, str);
        }
        return this.f13175b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(m13209b());
        sb.append(' ');
        sb.append(this.f13174a);
        if (this.f13179f != null) {
            sb.append(' ');
            sb.append(this.f13179f);
        }
        return sb.toString();
    }
}
