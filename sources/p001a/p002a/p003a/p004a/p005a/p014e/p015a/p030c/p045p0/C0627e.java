package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0593s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.C0646a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b;

/* renamed from: a.a.a.a.a.e.a.c.p0.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0627e implements InterfaceC0551l {

    /* renamed from: f */
    public C0593s f1948f;

    /* renamed from: g */
    public SecureRandom f1949g;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public void mo22702a(boolean z, InterfaceC0542i interfaceC0542i) {
        if (z) {
            if (interfaceC0542i instanceof C0578k0) {
                C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
                this.f1949g = c0578k0.m22800b();
                this.f1948f = (C0595t) c0578k0.m22801a();
                return;
            }
            this.f1949g = new SecureRandom();
            this.f1948f = (C0595t) interfaceC0542i;
            return;
        }
        this.f1948f = (C0596u) interfaceC0542i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public BigInteger[] mo22701a(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(length - 1) - i];
        }
        BigInteger bigInteger = new BigInteger(1, bArr2);
        BigInteger m22772d = this.f1948f.m22765b().m22772d();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(m22772d.bitLength(), this.f1949g);
            if (!bigInteger2.equals(InterfaceC0647b.f1976a)) {
                BigInteger mod = this.f1948f.m22765b().m22774b().m22581a(bigInteger2).m22578d().mo22584g().mod(m22772d);
                if (mod.equals(InterfaceC0647b.f1976a)) {
                    continue;
                } else {
                    BigInteger mod2 = bigInteger2.multiply(bigInteger).add(((C0595t) this.f1948f).m22758c().multiply(mod)).mod(m22772d);
                    if (!mod2.equals(InterfaceC0647b.f1976a)) {
                        return new BigInteger[]{mod, mod2};
                    }
                }
            }
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public boolean mo22700a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr2[i] = bArr[(length - 1) - i];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        BigInteger m22772d = this.f1948f.m22765b().m22772d();
        if (bigInteger.compareTo(InterfaceC0647b.f1977b) < 0 || bigInteger.compareTo(m22772d) >= 0 || bigInteger2.compareTo(InterfaceC0647b.f1977b) < 0 || bigInteger2.compareTo(m22772d) >= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger3.modInverse(m22772d);
        AbstractC0655f m22623c = C0646a.m22623c(this.f1948f.m22765b().m22774b(), bigInteger2.multiply(modInverse).mod(m22772d), ((C0596u) this.f1948f).m22757c(), m22772d.subtract(bigInteger).multiply(modInverse).mod(m22772d));
        if (m22623c.m22575g()) {
            return false;
        }
        return m22623c.m22578d().mo22584g().mod(m22772d).equals(bigInteger);
    }
}
