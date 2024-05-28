package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.w */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0294w extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f770v3;

    /* renamed from: w3 */
    public AbstractC0182o f771w3;

    public C0294w(byte[] bArr) {
        this.f770v3 = null;
        this.f771w3 = new C0168k1(bArr);
    }

    /* renamed from: a */
    public static C0294w m23641a(Object obj) {
        if (obj instanceof C0294w) {
            return (C0294w) obj;
        }
        if (obj != null) {
            return new C0294w(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0166k c0166k = this.f770v3;
        if (c0166k != null) {
            c0140e.m24170a(c0166k);
        }
        c0140e.m24170a(this.f771w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public byte[] m23640i() {
        return this.f771w3.mo24088m();
    }

    /* renamed from: j */
    public BigInteger m23639j() {
        C0166k c0166k = this.f770v3;
        if (c0166k == null) {
            return null;
        }
        return c0166k.m24145n();
    }

    public C0294w(int i, byte[] bArr) {
        this.f770v3 = new C0166k(i);
        this.f771w3 = new C0168k1(bArr);
    }

    public C0294w(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 1) {
            this.f770v3 = null;
            this.f771w3 = (AbstractC0182o) abstractC0263s.mo23751a(0);
            return;
        }
        this.f770v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f771w3 = (AbstractC0182o) abstractC0263s.mo23751a(1);
    }
}
