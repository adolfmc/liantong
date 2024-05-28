package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0135c2;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;

/* renamed from: a.a.a.a.a.e.a.b.s2.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0269b extends AbstractC0174m {

    /* renamed from: v3 */
    public C0278g[] f565v3;

    /* renamed from: w3 */
    public boolean f566w3;

    public C0269b(AbstractC0263s abstractC0263s) {
        this.f566w3 = true;
        this.f565v3 = new C0278g[abstractC0263s.mo23745o()];
        int i = 0;
        while (true) {
            C0278g[] c0278gArr = this.f565v3;
            if (i != c0278gArr.length) {
                c0278gArr[i] = C0278g.m23694a(abstractC0263s.mo23751a(i));
                i++;
            } else {
                this.f566w3 = abstractC0263s instanceof C0167k0;
                return;
            }
        }
    }

    /* renamed from: a */
    public static C0269b m23735a(Object obj) {
        if (obj instanceof C0269b) {
            return (C0269b) obj;
        }
        if (obj != null) {
            return new C0269b(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        int i = 0;
        while (true) {
            C0278g[] c0278gArr = this.f565v3;
            if (i == c0278gArr.length) {
                break;
            }
            c0140e.m24170a(c0278gArr[i]);
            i++;
        }
        if (this.f566w3) {
            return new C0167k0(c0140e);
        }
        return new C0135c2(c0140e);
    }

    /* renamed from: i */
    public C0278g[] m23734i() {
        return this.f565v3;
    }

    public C0269b(C0278g[] c0278gArr) {
        this.f566w3 = true;
        this.f565v3 = c0278gArr;
    }
}
