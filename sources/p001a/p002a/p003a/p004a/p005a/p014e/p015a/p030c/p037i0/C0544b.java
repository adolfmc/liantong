package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p037i0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0556m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0566e0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0568f0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0592r0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0670b;

/* renamed from: a.a.a.a.a.e.a.c.i0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0544b implements InterfaceC0637r {

    /* renamed from: d */
    public static final BigInteger f1766d = BigInteger.valueOf(0);

    /* renamed from: e */
    public static final BigInteger f1767e = BigInteger.valueOf(1);

    /* renamed from: a */
    public InterfaceC0556m f1768a;

    /* renamed from: b */
    public SecureRandom f1769b;

    /* renamed from: c */
    public C0592r0 f1770c;

    public C0544b(InterfaceC0556m interfaceC0556m, SecureRandom secureRandom) {
        this.f1768a = interfaceC0556m;
        this.f1769b = secureRandom;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r
    /* renamed from: a */
    public void mo22663a(InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0592r0) {
            this.f1770c = (C0592r0) interfaceC0542i;
            return;
        }
        throw new IllegalArgumentException("RSA key required");
    }

    /* renamed from: b */
    public InterfaceC0542i m22874b(byte[] bArr, int i) {
        return mo22662a(bArr, 0, i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r
    /* renamed from: a */
    public InterfaceC0542i mo22662a(byte[] bArr, int i, int i2) {
        if (!this.f1770c.m22845a()) {
            BigInteger m22766c = this.f1770c.m22766c();
            BigInteger m22767b = this.f1770c.m22767b();
            BigInteger m22463a = C0670b.m22463a(f1766d, m22766c.subtract(f1767e), this.f1769b);
            byte[] m22465a = C0670b.m22465a((m22766c.bitLength() + 7) / 8, m22463a);
            byte[] m22465a2 = C0670b.m22465a((m22766c.bitLength() + 7) / 8, m22463a.modPow(m22767b, m22766c));
            System.arraycopy(m22465a2, 0, bArr, i, m22465a2.length);
            this.f1768a.m22847a(new C0566e0(m22465a, null));
            byte[] bArr2 = new byte[i2];
            this.f1768a.m22846a(bArr2, 0, i2);
            return new C0568f0(bArr2);
        }
        throw new IllegalArgumentException("Public key required for encryption");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r
    /* renamed from: a */
    public InterfaceC0542i mo22661a(byte[] bArr, int i, int i2, int i3) {
        if (this.f1770c.m22845a()) {
            BigInteger m22766c = this.f1770c.m22766c();
            BigInteger m22767b = this.f1770c.m22767b();
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            this.f1768a.m22847a(new C0566e0(C0670b.m22465a((m22766c.bitLength() + 7) / 8, new BigInteger(1, bArr2).modPow(m22767b, m22766c)), null));
            byte[] bArr3 = new byte[i3];
            this.f1768a.m22846a(bArr3, 0, i3);
            return new C0568f0(bArr3);
        }
        throw new IllegalArgumentException("Private key required for decryption");
    }

    /* renamed from: a */
    public InterfaceC0542i m22875a(byte[] bArr, int i) {
        return mo22661a(bArr, 0, bArr.length, i);
    }
}
