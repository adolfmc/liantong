package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;

/* renamed from: a.a.a.a.a.e.a.b.x2.m */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0490m extends AbstractC0174m {

    /* renamed from: w3 */
    public static C0492o f1538w3 = new C0492o();

    /* renamed from: v3 */
    public AbstractC0651d f1539v3;

    public C0490m(AbstractC0651d abstractC0651d) {
        this.f1539v3 = abstractC0651d;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0168k1(f1538w3.m23010a(this.f1539v3.mo22584g(), f1538w3.m23011a(this.f1539v3)));
    }

    /* renamed from: i */
    public AbstractC0651d m23016i() {
        return this.f1539v3;
    }

    public C0490m(BigInteger bigInteger, AbstractC0182o abstractC0182o) {
        this(new AbstractC0651d.C0653b(bigInteger, new BigInteger(1, abstractC0182o.mo24088m())));
    }

    public C0490m(int i, int i2, int i3, int i4, AbstractC0182o abstractC0182o) {
        this(new AbstractC0651d.C0652a(i, i2, i3, i4, new BigInteger(1, abstractC0182o.mo24088m())));
    }
}
