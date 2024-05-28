package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p037i0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0556m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0566e0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0568f0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0593s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0670b;

/* renamed from: a.a.a.a.a.e.a.c.i0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0543a implements InterfaceC0637r {

    /* renamed from: g */
    public static final BigInteger f1759g = BigInteger.valueOf(1);

    /* renamed from: a */
    public InterfaceC0556m f1760a;

    /* renamed from: b */
    public SecureRandom f1761b;

    /* renamed from: c */
    public C0593s f1762c;

    /* renamed from: d */
    public boolean f1763d;

    /* renamed from: e */
    public boolean f1764e;

    /* renamed from: f */
    public boolean f1765f;

    public C0543a(InterfaceC0556m interfaceC0556m, SecureRandom secureRandom) {
        this.f1760a = interfaceC0556m;
        this.f1761b = secureRandom;
        this.f1763d = false;
        this.f1764e = false;
        this.f1765f = false;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r
    /* renamed from: a */
    public void mo22663a(InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0593s) {
            this.f1762c = (C0593s) interfaceC0542i;
            return;
        }
        throw new IllegalArgumentException("EC key required");
    }

    /* renamed from: b */
    public InterfaceC0542i m22876b(byte[] bArr, int i) {
        return mo22662a(bArr, 0, i);
    }

    public C0543a(InterfaceC0556m interfaceC0556m, SecureRandom secureRandom, boolean z, boolean z2, boolean z3) {
        this.f1760a = interfaceC0556m;
        this.f1761b = secureRandom;
        this.f1763d = z;
        this.f1764e = z2;
        this.f1765f = z3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r
    /* renamed from: a */
    public InterfaceC0542i mo22662a(byte[] bArr, int i, int i2) {
        C0593s c0593s = this.f1762c;
        if (c0593s instanceof C0596u) {
            BigInteger m22772d = c0593s.m22765b().m22772d();
            BigInteger m22773c = this.f1762c.m22765b().m22773c();
            BigInteger m22463a = C0670b.m22463a(f1759g, m22772d, this.f1761b);
            byte[] m22579c = this.f1762c.m22765b().m22774b().m22581a(m22463a).m22579c();
            System.arraycopy(m22579c, 0, bArr, i, m22579c.length);
            if (this.f1763d) {
                m22463a = m22463a.multiply(m22773c).mod(m22772d);
            }
            byte[] m22465a = C0670b.m22465a((this.f1762c.m22765b().m22775a().mo22604c() + 7) / 8, ((C0596u) this.f1762c).m22757c().m22581a(m22463a).m22578d().mo22584g());
            if (this.f1765f) {
                byte[] bArr2 = new byte[m22579c.length + m22465a.length];
                System.arraycopy(m22579c, 0, bArr2, 0, m22579c.length);
                System.arraycopy(m22465a, 0, bArr2, m22579c.length, m22465a.length);
                m22465a = bArr2;
            }
            this.f1760a.m22847a(new C0566e0(m22465a, null));
            byte[] bArr3 = new byte[i2];
            this.f1760a.m22846a(bArr3, 0, i2);
            return new C0568f0(bArr3);
        }
        throw new IllegalArgumentException("Public key required for encryption");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0637r
    /* renamed from: a */
    public InterfaceC0542i mo22661a(byte[] bArr, int i, int i2, int i3) {
        BigInteger m22758c;
        C0593s c0593s = this.f1762c;
        if (c0593s instanceof C0595t) {
            BigInteger m22772d = c0593s.m22765b().m22772d();
            BigInteger m22773c = this.f1762c.m22765b().m22773c();
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            AbstractC0655f m22621a = this.f1762c.m22765b().m22775a().m22621a(bArr2);
            if (this.f1763d || this.f1764e) {
                m22621a = m22621a.m22581a(m22773c);
            }
            if (this.f1763d) {
                m22758c = ((C0595t) this.f1762c).m22758c().multiply(m22773c.modInverse(m22772d)).mod(m22772d);
            } else {
                m22758c = ((C0595t) this.f1762c).m22758c();
            }
            byte[] m22465a = C0670b.m22465a((this.f1762c.m22765b().m22775a().mo22604c() + 7) / 8, m22621a.m22581a(m22758c).m22578d().mo22584g());
            if (this.f1765f) {
                byte[] bArr3 = new byte[m22465a.length + i2];
                System.arraycopy(bArr2, 0, bArr3, 0, i2);
                System.arraycopy(m22465a, 0, bArr3, i2, m22465a.length);
                m22465a = bArr3;
            }
            this.f1760a.m22847a(new C0566e0(m22465a, null));
            byte[] bArr4 = new byte[i3];
            this.f1760a.m22846a(bArr4, 0, i3);
            return new C0568f0(bArr4);
        }
        throw new IllegalArgumentException("Private key required for encryption");
    }

    /* renamed from: a */
    public InterfaceC0542i m22877a(byte[] bArr, int i) {
        return mo22661a(bArr, 0, bArr.length, i);
    }
}
