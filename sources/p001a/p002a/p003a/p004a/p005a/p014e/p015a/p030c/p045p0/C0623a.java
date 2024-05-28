package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import java.io.IOException;
import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;

/* renamed from: a.a.a.a.a.e.a.c.p0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0623a implements InterfaceC0643x {

    /* renamed from: a */
    public final InterfaceC0605o f1938a;

    /* renamed from: b */
    public final InterfaceC0551l f1939b;

    /* renamed from: c */
    public boolean f1940c;

    public C0623a(InterfaceC0551l interfaceC0551l, InterfaceC0605o interfaceC0605o) {
        this.f1938a = interfaceC0605o;
        this.f1939b = interfaceC0551l;
    }

    /* renamed from: c */
    private BigInteger[] m22709c(byte[] bArr) {
        AbstractC0263s abstractC0263s = (AbstractC0263s) AbstractC0258r.m23755a(bArr);
        return new BigInteger[]{((C0151g1) abstractC0263s.mo23751a(0)).m24145n(), ((C0151g1) abstractC0263s.mo23751a(1)).m24145n()};
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22637a(boolean z, InterfaceC0542i interfaceC0542i) {
        C0557a c0557a;
        this.f1940c = z;
        if (interfaceC0542i instanceof C0578k0) {
            c0557a = (C0557a) ((C0578k0) interfaceC0542i).m22801a();
        } else {
            c0557a = (C0557a) interfaceC0542i;
        }
        if (z && !c0557a.m22845a()) {
            throw new IllegalArgumentException("Signing Requires Private Key.");
        }
        if (!z && c0557a.m22845a()) {
            throw new IllegalArgumentException("Verification Requires Public Key.");
        }
        mo22635b();
        this.f1939b.mo22702a(z, interfaceC0542i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: b */
    public boolean mo22634b(byte[] bArr) {
        if (!this.f1940c) {
            byte[] bArr2 = new byte[this.f1938a.mo22743c()];
            this.f1938a.mo22746a(bArr2, 0);
            try {
                BigInteger[] m22709c = m22709c(bArr);
                return this.f1939b.mo22700a(bArr2, m22709c[0], m22709c[1]);
            } catch (IOException unused) {
                return false;
            }
        }
        throw new IllegalStateException("DSADigestSigner not initialised for verification");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: b */
    public void mo22635b() {
        this.f1938a.mo22744b();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22638a(byte b) {
        this.f1938a.mo22747a(b);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22636a(byte[] bArr, int i, int i2) {
        this.f1938a.mo22745a(bArr, i, i2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public byte[] mo22639a() {
        if (this.f1940c) {
            byte[] bArr = new byte[this.f1938a.mo22743c()];
            this.f1938a.mo22746a(bArr, 0);
            BigInteger[] mo22701a = this.f1939b.mo22701a(bArr);
            try {
                return m22710a(mo22701a[0], mo22701a[1]);
            } catch (IOException unused) {
                throw new IllegalStateException("unable to encode signature");
            }
        }
        throw new IllegalStateException("DSADigestSigner not initialised for signature generation.");
    }

    /* renamed from: a */
    private byte[] m22710a(BigInteger bigInteger, BigInteger bigInteger2) {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0151g1(bigInteger));
        c0140e.m24170a(new C0151g1(bigInteger2));
        return new C0184o1(c0140e).m24102a("DER");
    }
}
