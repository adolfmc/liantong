package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0593s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0646a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.p0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0625c implements InterfaceC0551l {

    /* renamed from: h */
    public static final BigInteger f1943h = BigInteger.valueOf(1);

    /* renamed from: f */
    public C0593s f1944f;

    /* renamed from: g */
    public SecureRandom f1945g;

    /* renamed from: b */
    public static void m22704b(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public void mo22702a(boolean z, InterfaceC0542i interfaceC0542i) {
        if (z) {
            if (interfaceC0542i instanceof C0578k0) {
                C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
                this.f1945g = c0578k0.m22800b();
                interfaceC0542i = c0578k0.m22801a();
            } else {
                this.f1945g = new SecureRandom();
            }
            this.f1944f = (C0595t) interfaceC0542i;
            return;
        }
        this.f1944f = (C0596u) interfaceC0542i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public BigInteger[] mo22701a(byte[] bArr) {
        AbstractC0651d m22707a = m22707a(this.f1944f.m22765b().m22775a(), bArr);
        if (m22707a.mo22584g().signum() == 0) {
            m22707a = this.f1944f.m22765b().m22775a().mo22606a(f1943h);
        }
        while (true) {
            BigInteger m22705a = m22705a(this.f1944f.m22765b().m22772d(), this.f1945g);
            AbstractC0651d m22578d = this.f1944f.m22765b().m22774b().m22581a(m22705a).m22578d();
            if (m22578d.mo22584g().signum() != 0) {
                BigInteger m22706a = m22706a(this.f1944f.m22765b().m22772d(), m22707a.mo22589c(m22578d));
                if (m22706a.signum() != 0) {
                    BigInteger mod = m22706a.multiply(((C0595t) this.f1944f).m22758c()).add(m22705a).mod(this.f1944f.m22765b().m22772d());
                    if (mod.signum() != 0) {
                        return new BigInteger[]{m22706a, mod};
                    }
                } else {
                    continue;
                }
            }
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public boolean mo22700a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.signum() == 0 || bigInteger2.signum() == 0 || bigInteger.compareTo(this.f1944f.m22765b().m22772d()) >= 0 || bigInteger2.compareTo(this.f1944f.m22765b().m22772d()) >= 0) {
            return false;
        }
        AbstractC0651d m22707a = m22707a(this.f1944f.m22765b().m22775a(), bArr);
        if (m22707a.mo22584g().signum() == 0) {
            m22707a = this.f1944f.m22765b().m22775a().mo22606a(f1943h);
        }
        AbstractC0655f m22623c = C0646a.m22623c(this.f1944f.m22765b().m22774b(), bigInteger2, ((C0596u) this.f1944f).m22757c(), bigInteger);
        if (m22623c.m22575g()) {
            return false;
        }
        return m22706a(this.f1944f.m22765b().m22772d(), m22707a.mo22589c(m22623c.m22578d())).compareTo(bigInteger) == 0;
    }

    /* renamed from: a */
    public static BigInteger m22705a(BigInteger bigInteger, SecureRandom secureRandom) {
        return new BigInteger(bigInteger.bitLength() - 1, secureRandom);
    }

    /* renamed from: a */
    public static AbstractC0651d m22707a(AbstractC0648c abstractC0648c, byte[] bArr) {
        byte[] m22503a = C0669a.m22503a(bArr);
        m22704b(m22503a);
        BigInteger bigInteger = new BigInteger(1, m22503a);
        while (bigInteger.bitLength() >= abstractC0648c.mo22604c()) {
            bigInteger = bigInteger.clearBit(bigInteger.bitLength() - 1);
        }
        return abstractC0648c.mo22606a(bigInteger);
    }

    /* renamed from: a */
    public static BigInteger m22706a(BigInteger bigInteger, AbstractC0651d abstractC0651d) {
        BigInteger mo22584g = abstractC0651d.mo22584g();
        while (mo22584g.bitLength() >= bigInteger.bitLength()) {
            mo22584g = mo22584g.clearBit(mo22584g.bitLength() - 1);
        }
        return mo22584g;
    }
}
