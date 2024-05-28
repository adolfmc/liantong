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

/* renamed from: a.a.a.a.a.e.a.c.p0.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0626d implements InterfaceC0551l, InterfaceC0647b {

    /* renamed from: f */
    public C0593s f1946f;

    /* renamed from: g */
    public SecureRandom f1947g;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public void mo22702a(boolean z, InterfaceC0542i interfaceC0542i) {
        if (z) {
            if (interfaceC0542i instanceof C0578k0) {
                C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
                this.f1947g = c0578k0.m22800b();
                this.f1946f = (C0595t) c0578k0.m22801a();
                return;
            }
            this.f1947g = new SecureRandom();
            this.f1946f = (C0595t) interfaceC0542i;
            return;
        }
        this.f1946f = (C0596u) interfaceC0542i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public BigInteger[] mo22701a(byte[] bArr) {
        BigInteger mod;
        BigInteger mod2;
        BigInteger m22772d = this.f1946f.m22765b().m22772d();
        BigInteger m22703a = m22703a(m22772d, bArr);
        do {
            int bitLength = m22772d.bitLength();
            while (true) {
                BigInteger bigInteger = new BigInteger(bitLength, this.f1947g);
                if (!bigInteger.equals(InterfaceC0647b.f1976a) && bigInteger.compareTo(m22772d) < 0) {
                    mod = this.f1946f.m22765b().m22774b().m22581a(bigInteger).m22578d().mo22584g().mod(m22772d);
                    if (!mod.equals(InterfaceC0647b.f1976a)) {
                        mod2 = bigInteger.modInverse(m22772d).multiply(m22703a.add(((C0595t) this.f1946f).m22758c().multiply(mod))).mod(m22772d);
                    }
                }
            }
        } while (mod2.equals(InterfaceC0647b.f1976a));
        return new BigInteger[]{mod, mod2};
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0551l
    /* renamed from: a */
    public boolean mo22700a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger m22772d = this.f1946f.m22765b().m22772d();
        BigInteger m22703a = m22703a(m22772d, bArr);
        if (bigInteger.compareTo(InterfaceC0647b.f1977b) < 0 || bigInteger.compareTo(m22772d) >= 0 || bigInteger2.compareTo(InterfaceC0647b.f1977b) < 0 || bigInteger2.compareTo(m22772d) >= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(m22772d);
        AbstractC0655f m22623c = C0646a.m22623c(this.f1946f.m22765b().m22774b(), m22703a.multiply(modInverse).mod(m22772d), ((C0596u) this.f1946f).m22757c(), bigInteger.multiply(modInverse).mod(m22772d));
        if (m22623c.m22575g()) {
            return false;
        }
        return m22623c.m22578d().mo22584g().mod(m22772d).equals(bigInteger);
    }

    /* renamed from: a */
    private BigInteger m22703a(BigInteger bigInteger, byte[] bArr) {
        int bitLength = bigInteger.bitLength();
        int length = bArr.length * 8;
        if (bitLength >= length) {
            return new BigInteger(1, bArr);
        }
        return new BigInteger(1, bArr).shiftRight(length - bitLength);
    }
}
